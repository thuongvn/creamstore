package com.example.demo.entity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name="product_name")
    private String product_name;


    @Column(name="description")
    private String description;

    @Column(name="images")
    private String images;

    @Column(name="create_at")
    private Date create_at;

    @Column(name="update_at")
    private Date update_at;

    @Column(name="price")
    private float price;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;
}
