package revi1337.stockexample.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import revi1337.stockexample.repository.RedisLockRepository;
import revi1337.stockexample.service.StockService;

@RequiredArgsConstructor
@Component
public class RedisLettuceLockStockFacade {

    private final RedisLockRepository redisLockRepository;
    private final StockService stockService;

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(id)) {
            Thread.sleep(100);
        }
        try {
            stockService.decrease(id, quantity);
        } finally {
            redisLockRepository.unLock(id);
        }
    }

}
