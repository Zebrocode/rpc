package com.zebro.rpc3;

import com.zebro.IUserService;
import com.zebro.IUserServiceImpl;
import com.zebro.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
//unchanged
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
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        int id = dis.readInt();
        IUserService service = new IUserServiceImpl();
        User user = service.findUserById(id);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
