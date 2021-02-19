package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.RoleEnum;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {

    @JsonProperty("userid")
    private Long userid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("userpassword")
    private String userpassword;

    @JsonProperty("role")
    private RoleEnum role;

    @JsonProperty("products")
    @JsonBackReference
    private Set<ProductDTO> products;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        //Extract the list of roles
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+this.role.getRolename());
        authorities.add(authority);

        //If the user have permissions, we have to extract the list of permissions also

        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.userpassword;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() { return true; }

    public void addProduct(ProductDTO product){
        if(products == null) products = new HashSet<ProductDTO>();

        products.add(product);
        product.addCreator(this);
    }
}
