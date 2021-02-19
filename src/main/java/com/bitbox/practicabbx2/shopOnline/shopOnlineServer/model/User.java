package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long userid;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userpassword;

    @Column(nullable = false)
    private RoleEnum role;

    @Column()
    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    public void addProduct(Product product) {
        if(products == null) {
            products = new HashSet<>();
        }
        products.add(product);
        product.setCreator(this);
    }
}
