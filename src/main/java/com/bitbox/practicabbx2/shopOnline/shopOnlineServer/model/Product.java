package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long idproduct;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private StateEnum state;

    @Column()
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "suppliers_products")
    private Set<Supplier> suppliers;

    @Column()
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "priceReductions_products")
    private Set<PriceReduction> priceReductions;

    @CreationTimestamp
    @Column(nullable = false)
    private Date creationDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_ID")
    private User creator;

    @Column()
    @CollectionTable(name = "comments")
    private CommentEnum comment;

}
