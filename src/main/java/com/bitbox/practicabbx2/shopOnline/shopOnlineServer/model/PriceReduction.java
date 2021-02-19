package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "priceReductions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceReduction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator= "pricereduction_seq")
    @SequenceGenerator(name = "pricereduction_seq", sequenceName = "pricereduction_seq", allocationSize = 1)
    private Long idpricereduction;

    @Column()
    private int discount;

    @Column()
    private Date startDate;

    @Column()
    private Date endDate;

    @Column()
    @ManyToMany(mappedBy = "priceReductions")
    private Set<Product> products;

}
