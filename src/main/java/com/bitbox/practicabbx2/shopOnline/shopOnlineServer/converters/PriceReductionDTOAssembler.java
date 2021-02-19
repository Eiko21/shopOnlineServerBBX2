package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.PriceReductionDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.SupplierDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.PriceReduction;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.Supplier;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriceReductionDTOAssembler {

    private ModelMapper modelMapper = new ModelMapper();

    public PriceReductionDTO convertToDto(PriceReduction priceReduction){
        if(priceReduction == null) return null;

        PriceReductionDTO priceReductionDTO = modelMapper.map(priceReduction, PriceReductionDTO.class);
        if(priceReductionDTO.getIdpricereduction() != null && priceReductionDTO.getIdpricereduction().equals(Long.valueOf(0)))
            priceReductionDTO.setIdpricereduction(null);

        return priceReductionDTO;
    }

    public PriceReduction convertToPojo(PriceReductionDTO priceReductionDTO){
        if(priceReductionDTO == null) return null;

        PriceReduction priceReduction = modelMapper.map(priceReductionDTO, PriceReduction.class);
        if(priceReduction.getIdpricereduction() != null && priceReduction.getIdpricereduction().equals(Long.valueOf(0)))
            priceReduction.setIdpricereduction(null);

        return priceReduction;
    }
}
