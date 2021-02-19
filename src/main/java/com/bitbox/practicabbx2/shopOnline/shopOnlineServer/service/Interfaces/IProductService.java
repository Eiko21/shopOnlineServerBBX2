package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    ResponseEntity<List<ProductDTO>> getProducts();
    ResponseEntity<ProductDTO> getProductById(Long productId);
    ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO);
    ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO productDTO);
    ResponseEntity<Void> deleteProduct(Long productId);
    
}
