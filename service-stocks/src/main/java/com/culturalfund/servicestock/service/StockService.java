package com.culturalfund.servicestock.service;

import com.culturalfund.servicestock.repo.StockRepo;
import com.culturalfund.servicestock.repo.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepo stockRepo;

    public List<Stock> fetchAll() {
        return stockRepo.findAll();
    }

    public Stock fetchById(long id) throws IllegalArgumentException {
        final Optional<Stock> maybeStock = stockRepo.findById(id);

        if (maybeStock.isEmpty()) throw new IllegalArgumentException("Material not found");
        else return maybeStock.get();
    }

    public long create(String name, Boolean availability, Long quantity) {

        final Stock stock = new Stock(name, availability, quantity);
        final Stock savedStock = stockRepo.save(stock);

        return savedStock.getId();
    }

    public void update(Long id, String name, Boolean availability, Long quantity) throws IllegalArgumentException {

        final Optional<Stock> maybeStock = stockRepo.findById(id);
        if (maybeStock.isEmpty()) throw new IllegalArgumentException("Material not found");

        final Stock stock = maybeStock.get();
        if (name != null && !name.isBlank()) stock.setName(name);
        if (availability != null) stock.setAvailability(availability);
        if (quantity != null) stock.setQuantity(quantity);
        stockRepo.save(stock);
    }

    public void delete(Long id) {
        stockRepo.deleteById(id);
    }
}
