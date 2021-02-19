package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.controller;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.SupplierDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/api/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers(){
        return supplierService.getSuppliers();
    }

    @GetMapping("/api/suppliers/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable(name = "id") long supplierId){
        return supplierService.getSupplierById(supplierId);
    }
}
