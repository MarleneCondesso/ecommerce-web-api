package com.ecommerce.ecommerceweb.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wishlist")
public class Wishlist {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "product_id")
    private Product product;

    @Column(name = "created_date")
    private Date createdDate;

    public Wishlist(User user, Product product, Date createdDate) {
        this.user = user;
        this.product = product;
        this.createdDate = createdDate;
    }

    public Wishlist(User user, Product product) {
        this.user = user;
        this.product = product;
        this.createdDate = new Date();
    }

    public Wishlist() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date created_date) {
        this.createdDate = created_date;
    }
}
