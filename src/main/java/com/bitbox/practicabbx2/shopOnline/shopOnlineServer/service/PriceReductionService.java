package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.converters.PriceReductionDTOAssembler;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.PriceReductionDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.exception.ResourceNotFoundException;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.model.PriceReduction;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.repository.PriceReductionRepository;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.IPriceReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceReductionService implements IPriceReductionService {

    @Autowired
    private PriceReductionRepository priceReductionRepository;

    @Autowired
    private PriceReductionDTOAssembler priceReductionDTOAssembler;

    @Override
    public ResponseEntity<List<PriceReductionDTO>> getPriceReductions() {
        List<PriceReduction> priceReductions = this.priceReductionRepository.findAll();
        List<PriceReductionDTO> priceReductionsDTO = new ArrayList<>();

        if (!priceReductions.isEmpty()){
            for (PriceReduction price: priceReductions)
                priceReductionsDTO.add(priceReductionDTOAssembler.convertToDto(price));
        }else throw new ResourceNotFoundException("Not found any price reduction");
        return ResponseEntity.ok().body(priceReductionsDTO);
    }

    @Override
    public ResponseEntity<PriceReductionDTO> getPriceReduction(Long priceReductionId) {
        Optional<PriceReduction> priceReductionResult = this.priceReductionRepository.findById(priceReductionId);

        if(priceReductionResult.isPresent()) {
            PriceReduction priceReduction = priceReductionResult.get();
            return ResponseEntity.ok().body(priceReductionDTOAssembler.convertToDto(priceReduction));
        } else throw new ResourceNotFoundException("Price reduction with ID " + priceReductionId + ": Not found");
    }

    @Override
    public ResponseEntity<PriceReductionDTO> createPriceReduction(PriceReductionDTO priceReduction) {
        priceReductionRepository.save(priceReductionDTOAssembler.convertToPojo(priceReduction));
        return ResponseEntity.ok().body(priceReduction);
    }

    @Override
    public ResponseEntity<Void> deletePriceReduction(Long priceReductionId) {
        this.priceReductionRepository.deleteById(priceReductionId);
        return ResponseEntity.ok().build();
    }

}
