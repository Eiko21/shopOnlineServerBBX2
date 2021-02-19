package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
