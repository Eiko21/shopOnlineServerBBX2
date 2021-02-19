package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters.*;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.*;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.exception.ResourceNotFoundException;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.*;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.PriceReductionRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.ProductRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.SupplierRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.UserRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Configurable
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private PriceReductionRepository priceReductionRepository;

    private ProductDTOAssembler productDTOAssembler = new ProductDTOAssembler();
    private SupplierDTOAssembler supplierDTOAssembler = new SupplierDTOAssembler();
    private PriceReductionDTOAssembler priceReductionDTOAssembler = new PriceReductionDTOAssembler();

    @Override
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productsDTO = new ArrayList<>();

        if (!products.isEmpty()){
            for (Product product: products) {
                Optional<User> user = userRepository.findById(product.getCreator().getUserid());
                if(!product.getCreator().equals(user.get())) user.get().addProduct(product);
                productsDTO.add(productDTOAssembler.convertToDto(product));
            }
        }
        return ResponseEntity.ok().body(productsDTO);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Long productId) {
        Optional<Product> productResult = productRepository.findById(productId);

        if (productResult.isPresent()) {
            Optional<User> user = userRepository.findById(productResult.get().getCreator().getUserid());
            if(!productResult.get().getCreator().equals(user.get())) user.get().addProduct(productResult.get());
            return ResponseEntity.ok().body(productDTOAssembler.convertToDto(productResult.get()));
        } else throw new ResourceNotFoundException("Product with code " + productId + ": Not found");
    }

    @Override
    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
        productRepository.save(productDTOAssembler.convertToPojo(productDTO));
        return ResponseEntity.ok().body(productDTO);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
        Optional<Product> productResult = productRepository.findById(id);
        Set<Supplier> suppliers = new HashSet<>(){};
        Set<PriceReduction> priceReductions = new HashSet<>();

        if(productResult.isPresent()) {
            Product product = productResult.get();
            Optional<User> user = userRepository.findById(product.getCreator().getUserid());
            if (!productDTO.getCreator().equals(user.get())) user.get().addProduct(product);

            product.setIdproduct(productDTO.getIdproduct());
            product.setCode(productDTO.getCode());
            product.setPrice(productDTO.getPrice());
            product.setState(productDTO.getState());

            if (productDTO.getSuppliers() != null){
                for (SupplierDTO supplierDTO : productDTO.getSuppliers())
                    suppliers.add(supplierDTOAssembler.convertToPojo(supplierDTO));
                product.setSuppliers(suppliers);
            }else product.setPriceReductions(new HashSet<>());

            if (productDTO.getPriceReductions() != null){
                for (PriceReductionDTO priceReductionDTO : productDTO.getPriceReductions())
                    priceReductions.add(priceReductionDTOAssembler.convertToPojo(priceReductionDTO));
                product.setPriceReductions(priceReductions);
            }else product.setPriceReductions(new HashSet<>());

            product.setCreationDate(productDTO.getCreationDate());
            product.setComment(productDTO.getComment());
            productRepository.save(product);
            return ResponseEntity.ok().body(productDTOAssembler.convertToDto(product));
        } else return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return ResponseEntity.ok().build();
    }

}
