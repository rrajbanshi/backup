package com.labs.sboot.rest.model;

import java.time.LocalDate;
import java.util.List;

//@Component
public class Order {
    private int id;
    private String status;
    private double totalAmount;
    private String orderBy;
    private LocalDate orderDate = LocalDate.now();
//    @Autowired
//    @Qualifier("samosa")
    private List<Item> items;

    // default Constructor
    public Order() {
        System.out.println("Order default constructor called...");
    }

    // overloaded constructor
//    public Order(List<Item> items) {
//        System.out.println("Order overloaded constructor called...");
//        this.items = items;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        System.out.println("Set Items method called..");
        this.items = items;
    }

    public void init() {
        System.out.println("Initializing Order bean...");
    }

    public void destroy() {
        System.out.println("Cleaning up Order bean...");
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderBy='" + orderBy + '\'' +
                ", orderDate=" + orderDate +
                ", items=" + items +
                '}';
    }
}
