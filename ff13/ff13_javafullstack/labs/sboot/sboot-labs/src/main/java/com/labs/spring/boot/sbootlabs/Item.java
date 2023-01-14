package com.labs.spring.boot.sbootlabs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("samosa")
public class Item {
    @Value("100")
    private int id;
    @Value("Samosa")
    private String name;
    @Value("20")
    private double price;
    @Value("20")
    private int quantity;
    @Value("indian")
    private String cusine;
    @Value("true")
    private boolean veg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCusine() {
        return cusine;
    }

    public void setCusine(String cusine) {
        this.cusine = cusine;
    }

    public boolean isVeg() {
        return veg;
    }

    public void setVeg(boolean veg) {
        this.veg = veg;
    }

    public void init() {
        System.out.println("Initialzing Item bean...");
    }

    public void destroy() {
        System.out.println("Cleaning up Item bean..");
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", cusine='" + cusine + '\'' +
                ", veg=" + veg +
                '}';
    }
}
