package com.zebro;

public class IUserServiceImpl implements IUserService {
    @Override
    public User findUserById(int id) {
        return new User(id,"Alice");
    }//直接new模拟数据库查询
}
