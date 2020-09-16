package com.zebro;

public class IProductServiceImpl implements IProductService{
    @Override
    public Product findProductByName(String name) {
        return new Product(1,name,1);
    }
}
