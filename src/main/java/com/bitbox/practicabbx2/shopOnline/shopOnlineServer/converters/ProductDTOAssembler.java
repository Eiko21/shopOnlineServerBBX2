package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.ProductDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDTOAssembler {

    private ModelMapper modelMapper = new ModelMapper();

    public ProductDTO convertToDto(Product product){
        if(product == null) return null;

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        if(productDTO.getIdproduct() != null && productDTO.getIdproduct().equals(Long.valueOf(0))) productDTO.setIdproduct(null);
        return productDTO;
    }

    public Product convertToPojo(ProductDTO productDTO){
        if(productDTO == null) return null;

        Product product = modelMapper.map(productDTO, Product.class);
        if(product.getIdproduct() != null && product.getIdproduct().equals(Long.valueOf(0))) product.setIdproduct(null);
        return product;
    }
}
