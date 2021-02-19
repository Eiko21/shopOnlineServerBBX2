package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO implements Serializable {

    @JsonProperty("idsupplier")
    private Long idsupplier;

    @JsonProperty("supplierName")
    private String supplierName;

    @JsonProperty("supplierCountry")
    private String supplierCountry;

    @JsonIgnore
    @JsonProperty("products")
    private Set<ProductDTO> products;

}
