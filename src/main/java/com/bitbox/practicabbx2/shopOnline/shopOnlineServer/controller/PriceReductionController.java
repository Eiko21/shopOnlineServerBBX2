package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.controller;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.PriceReductionDTO;
import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces.IPriceReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class PriceReductionController {

    @Autowired
    private IPriceReductionService priceReductionService;

    @GetMapping("/api/price-reductions")
    public ResponseEntity<List<PriceReductionDTO>> getAllPriceReduction(){
        return priceReductionService.getPriceReductions();
    }

    @GetMapping("/api/price-reductions/{id}")
    public ResponseEntity<PriceReductionDTO> getPriceReductionById(@PathVariable(name = "id") Long priceReductionCode){
        return priceReductionService.getPriceReduction(priceReductionCode);
    }

    @PostMapping("/api/price-reductions")
    public ResponseEntity<PriceReductionDTO> createPriceReduction(@RequestBody PriceReductionDTO priceReductionDTO){
        return priceReductionService.createPriceReduction(priceReductionDTO);
    }

    @DeleteMapping("/api/price-reductions/{id}")
    public ResponseEntity<Void> deletePriceReduction(@PathVariable Long priceReductionCode){
        priceReductionService.deletePriceReduction(priceReductionCode);
        return ResponseEntity.noContent().build();
    }
}
