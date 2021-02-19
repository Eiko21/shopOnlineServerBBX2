package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceReductionDTO implements Serializable {

    @JsonProperty("idpricereduction")
    private Long idpricereduction;

    @JsonProperty("discount")
    private int discount;

    @JsonProperty("startDate")
    private Date startDate;

    @JsonProperty("endDate")
    private Date endDate;

    @JsonIgnore
    @JsonProperty("products")
    private Set<ProductDTO> products;

}
