package com.culturalfund.servicestock.api;

import com.culturalfund.servicestock.api.dto.StockDto;
import com.culturalfund.servicestock.repo.model.Stock;
import com.culturalfund.servicestock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> index() {
        final List<Stock> stocks = stockService.fetchAll();

        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> show(@PathVariable long id) {
        try {
            final Stock stock = stockService.fetchById(id);
            return ResponseEntity.ok(stock);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody StockDto stockDto) {
        final String name = stockDto.getName();
        final Boolean availability = stockDto.getAvailability();
        final Long quantity = stockDto.getQuantity();
        final long id = stockService.create(name, availability, quantity);

        final String location = String.format("/stocks/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody StockDto stockDto) {
        final String name = stockDto.getName();
        final Boolean availability = stockDto.getAvailability();
        final Long quantity = stockDto.getQuantity();

        try {
            stockService.update(id, name, availability, quantity);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
