package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters.SupplierDTOAssembler;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.SupplierDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.exception.ResourceNotFoundException;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Supplier;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.SupplierRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierDTOAssembler supplierDTOAssembler;

    @Override
    public ResponseEntity<List<SupplierDTO>> getSuppliers() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierDTO> suppliersDTO = new ArrayList<>();
        
        if(!suppliers.isEmpty()){
            for (Supplier supplier: suppliers) {
                suppliersDTO.add(supplierDTOAssembler.convertToDto(supplier));
            }
        }
        return ResponseEntity.ok().body(suppliersDTO);
    }

    @Override
    public ResponseEntity<SupplierDTO> getSupplierById(long supplierId) {
        Optional<Supplier> supplierResult = this.supplierRepository.findById(supplierId);

        if (supplierResult.isPresent()) {
            Supplier supplier = supplierResult.get();
            return ResponseEntity.ok().body(supplierDTOAssembler.convertToDto(supplier));
        } else throw new ResourceNotFoundException("Supplier with ID " + supplierId + ": Not found");
    }
}
