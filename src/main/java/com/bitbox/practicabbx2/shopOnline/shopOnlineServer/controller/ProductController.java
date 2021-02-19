package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.controller;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.ProductDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private IProductService productService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/api/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping(value = "/api/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable(name = "id") Long productId){
        return productService.getProductById(productId);
    }

    @PostMapping(value = "/api/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> createProduct(@RequestBody String product) throws JsonProcessingException {
        ProductDTO productDTO = objectMapper.readValue(product, ProductDTO.class);
        return productService.createProduct(productDTO);
    }

    @PutMapping(value = "/api/products/{id}/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable(name = "id") Long id, @RequestBody String product) throws JsonProcessingException {
        ProductDTO productDTO = objectMapper.readValue(product, ProductDTO.class);
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping(value = "/api/products/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long productId){
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
