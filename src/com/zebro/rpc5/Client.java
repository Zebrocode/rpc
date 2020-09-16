package com.zebro.rpc5;

import com.zebro.IProductService;
import com.zebro.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService service = (IUserService) Stub.getStub(IUserService.class);
        IProductService service2 = (IProductService)Stub.getStub(IProductService.class);
        System.out.println(service.findUserById(123));
        System.out.println(service2.findProductByName("Bob"));
    }
}
