package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "supplier_seq")
    @SequenceGenerator(name = "supplier_seq", sequenceName = "supplier_seq", allocationSize = 1)
    private Long idsupplier;

    @Column()
    private String supplierName;

    @Column()
    private String supplierCountry;

    @Column()
    @ManyToMany(mappedBy = "suppliers")
    private Set<Product> products;

}
