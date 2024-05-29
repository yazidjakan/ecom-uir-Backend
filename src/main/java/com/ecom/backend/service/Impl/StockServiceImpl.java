package com.ecom.backend.service.Impl;

import com.ecom.backend.dto.StockDto.StockGetDto;
import com.ecom.backend.dto.StockDto.StockPostDto;
import com.ecom.backend.entity.Stock;
import com.ecom.backend.enums.StatutStock;
import com.ecom.backend.repository.StockRepository;
import com.ecom.backend.service.facade.StockService;
import com.ecom.backend.transformer.StockTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    private final StockRepository stockDao;
    private final StockTransformer stockTransformer;
    @Override
    public StockGetDto findById(Long id) {
        log.info("Fetching stock by ID: {}", id);
        return stockDao.findById(id)
                .map(stockTransformer::toDto)
                .orElseThrow(() -> {
                    log.error("Stock not found with ID: {}", id);
                    return new RuntimeException("Unable to find a Stock with the given Id : "+id);
                });
    }

    @Override
    public List<StockGetDto> findAll() {
        log.info("Fetching all stocks");
        List<Stock> stocks=stockDao.findAll();
        if(stocks.isEmpty()){
            throw new RuntimeException("List of users is Empty");
        }
        return stockTransformer.toDto(stocks);
    }

    public StockPostDto save(StockPostDto dto) {

        log.info("Creating new stock with name: {}", dto.nomStock());

        if(stockDao.findByNomStock(dto.nomStock()).isPresent())
        {
            log.warn("Attempted to create a duplicate stock with name: {}", dto.nomStock());
            throw new RuntimeException("This stock name already exists");
        }
        try {
            Stock stock = stockTransformer.toEntityPost(dto);
            stock.setStatutStock(StatutStock.ENVOYE);
            log.info("Successfully created stock with name: {}", dto.nomStock());
            return stockTransformer.toDtoPost(stockDao.save(stock));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating stock with name: {}", dto.nomStock(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the stock."+ ex);
        }
    }

    @Override
    public StockGetDto save(StockGetDto dto) {

        log.info("Creating new stock with name: {}", dto.nomStock());

        if(stockDao.findByNomStock(dto.nomStock()).isPresent())
        {
            log.warn("Attempted to create a duplicate stock with name: {}", dto.nomStock());
            throw new RuntimeException("This stock name already exists");
        }
        try {
            Stock stock = stockTransformer.toEntity(dto);
            stock.setStatutStock(StatutStock.ENVOYE);
            log.info("Successfully created stock with name: {}", dto.nomStock());
            return stockTransformer.toDto(stockDao.save(stock));

        }catch (Exception ex)
        {
            log.error("Error occurred while creating stock with name: {}", dto.nomStock(), ex);
            throw new RuntimeException("An unexpected error occurred while creating the stock."+ ex);
        }
    }

    @Override
    public StockGetDto update(StockGetDto dto, Long id) {
        id = dto.id();
        StockGetDto existingStockDto = findById(id);
        Stock existingStock = stockTransformer.toEntity(dto);
        existingStock.setId(dto.id());
        existingStock.setNomStock(dto.nomStock());
        existingStock.setStatutStock(dto.statutStock());
        existingStock.setProduits(dto.produits());


        log.info("Successfully updated stock with ID: {}", dto.id());
        return stockTransformer.toDto(stockDao.save(existingStock));
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting stock with ID: {}", id);
        StockGetDto foundStock = findById(id);
        stockDao.deleteById(foundStock.id());

        log.info("Successfully deleted stock with ID: {}", id);
    }
}
