package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.SupplierDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierDTOAssembler {

    private ModelMapper modelMapper = new ModelMapper();

    public SupplierDTO convertToDto(Supplier supplier){
        if(supplier == null) return null;

        SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);
        if(supplierDTO.getIdsupplier() != null && supplierDTO.getIdsupplier().equals(Long.valueOf(0))) supplierDTO.setIdsupplier(null);
        return supplierDTO;
    }

    public Supplier convertToPojo(SupplierDTO supplierDTO){
        if(supplierDTO == null) return null;

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);
        if(supplier.getIdsupplier() != null && supplier.getIdsupplier().equals(Long.valueOf(0))) supplier.setIdsupplier(null);

        return supplier;
    }
}
