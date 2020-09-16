package com.zebro.rpc4;

import com.zebro.IUserService;
import com.zebro.IUserServiceImpl;
import com.zebro.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8088);
        while(running){
            Socket client = server.accept();
            process(client);
            client.close();
        }
        server.close();
    }
    public static void process(Socket socket) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        //为了适应客户端通用化而做的改动
        String methodName = ois.readUTF();
        Class[] parameterTypes = (Class[]) ois.readObject();
        Object[] parameters = (Object[]) ois.readObject();

        IUserService service = new IUserServiceImpl();//服务类型暂时还是写死的，不够灵活
        Method method = service.getClass().getMethod(methodName, parameterTypes);
        User user = (User)method.invoke(service, parameters);
        oos.writeObject(user);
        oos.flush();
    }
}
