package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class ProductDTO implements Serializable {

    @JsonProperty("idproduct")
    private Long idproduct;

    @JsonProperty("code")
    private int code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private float price;

    @JsonProperty("state")
    private StateEnum state;

    @JsonIgnore
    @JsonProperty("suppliers")
    private Set<SupplierDTO> suppliers;

    @JsonIgnore
    @JsonProperty("priceReductions")
    private Set<PriceReductionDTO> priceReductions;

    @JsonProperty("creationDate")
    private Date creationDate;

    @JsonProperty("creator")
    private UserDTO creator;

    @JsonProperty("comment")
    private CommentEnum comment;

    public void addCreator(UserDTO user){
        if(user == null) user = new UserDTO();

        creator.setUserid(user.getUserid());
        creator.setUsername(user.getUsername());
        creator.setUserpassword(user.getUserpassword());
        creator.setRole(user.getRole());
        creator.setProducts(user.getProducts());

        user.addProduct(this);
    }

}
