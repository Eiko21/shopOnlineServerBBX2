package com.bitbox.practicabbx2.shopOnline.shopOnlineServer.service.Interfaces;

import com.bitbox.practicabbx2.shopOnline.shopOnlineServer.dto.PriceReductionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface IPriceReductionService {
    ResponseEntity<List<PriceReductionDTO>> getPriceReductions();
    ResponseEntity<PriceReductionDTO> getPriceReduction(Long priceReductionId);
    ResponseEntity<PriceReductionDTO> createPriceReduction(PriceReductionDTO priceReduction);
    ResponseEntity<Void> deletePriceReduction(Long priceReductionId);
}
