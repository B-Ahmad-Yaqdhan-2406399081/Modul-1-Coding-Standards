package id.ac.ui.cs.advprog.eshop.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class Order {
    private String id;
    private List<Product> products;
    private long orderTime;
    private String author;
    @Setter
    private String status;

    public Order(String id, List<Product> products, long orderTime, String author) {

    }

    public Order(String id, List<Product> products, long orderTime, String author, String status) {

    }
}
