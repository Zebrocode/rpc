package com.zebro.rpc1;

import com.zebro.User;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",8088);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(123); // 注意这里是写死的，缺少灵活性
        //发送出要查询的id
        socket.getOutputStream().write(baos.toByteArray());
        socket.getOutputStream().flush();
        //接收服务端返回的结果
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        int id = dis.readInt();
        String name = dis.readUTF();
        User user = new User(id,name);
        System.out.println(user);
        dos.close();
        socket.close();
    }
}
