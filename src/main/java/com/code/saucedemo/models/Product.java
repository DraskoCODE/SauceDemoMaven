package com.code.saucedemo.models;

public class Product {
    private String name;
    private Double price;
    private String imgSource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgSource() {
        return imgSource;
    }

    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    public Product(String name, Double price, String imgSource) {
        this.name = name;
        this.price = price;
        this.imgSource = imgSource;
    }



}
