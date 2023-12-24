package revi1337.stockexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revi1337.stockexample.domain.Stock;


public interface StockRepository extends JpaRepository<Stock, Long> {
}
