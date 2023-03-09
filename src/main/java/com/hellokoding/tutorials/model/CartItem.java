package com.hellokoding.tutorials.model;


import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id") //??
    private Produs productid;

    private int quantity;


    public CartItem(Integer id, Produs productid, int quantity) {
        this.id = id;
        this.productid = productid;
        this.quantity = quantity;
    }
    public CartItem(){
        this.quantity=0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produs getProductid() {
        return productid;
    }

    public void setProductid(Produs productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", productid=" + productid +
                ", quantity=" + quantity +
                '}';
    }
}
