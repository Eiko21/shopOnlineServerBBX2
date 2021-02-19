package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces;


import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.SupplierDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISupplierService {
    ResponseEntity<List<SupplierDTO>> getSuppliers();
    ResponseEntity<SupplierDTO> getSupplierById(long supplierId);
}
