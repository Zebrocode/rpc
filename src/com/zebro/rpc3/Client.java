package com.zebro.rpc3;

import com.zebro.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService stub = Stub.getStub();
        System.out.println(stub.findUserById(123));
    }
}
