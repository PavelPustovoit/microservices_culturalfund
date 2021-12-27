package com.culturalfund.servicestock.repo;

import com.culturalfund.servicestock.repo.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StockRepo extends JpaRepository<Stock, Long> {

}
