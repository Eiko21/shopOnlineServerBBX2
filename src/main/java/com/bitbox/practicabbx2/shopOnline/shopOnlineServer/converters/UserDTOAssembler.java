package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.ProductDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.UserDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Product;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto(User user){
        if(user == null) return null;

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
            @Override
            protected void configure() {
                map().addCreator(userDTO);
            }
        });
        if(userDTO.getUserid() != null && userDTO.getUserid().equals(Long.valueOf(0))) userDTO.setUserid(null);

        return userDTO;
    }

    public User convertToPojo(UserDTO userDTO){
        if(userDTO == null) return null;

        User user = modelMapper.map(userDTO, User.class);
        if(user.getUserid() != null && user.getUserid().equals(Long.valueOf(0))) user.setUserid(null);

        return user;
    }
}
