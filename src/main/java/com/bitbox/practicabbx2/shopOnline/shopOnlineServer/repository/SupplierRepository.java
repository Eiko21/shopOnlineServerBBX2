package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
