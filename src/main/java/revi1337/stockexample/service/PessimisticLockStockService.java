package revi1337.stockexample.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import revi1337.stockexample.domain.Stock;
import revi1337.stockexample.repository.StockRepository;

@RequiredArgsConstructor
@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    @Transactional
    public void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decrease(quantity);
    }

}
