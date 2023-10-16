package com.example.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "trx_user_input")
public class TrscUserInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trscid")
    private Integer id;
    private String label;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userinputid")
    private UserInput user_input;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserInput getUser_input() {
        return user_input;
    }

    public void setUser_input(UserInput user_input) {
        this.user_input = user_input;
    }
    
    
}
