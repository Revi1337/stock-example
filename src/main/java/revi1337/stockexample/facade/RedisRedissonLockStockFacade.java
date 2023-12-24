package revi1337.stockexample.facade;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;
import revi1337.stockexample.service.StockService;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisRedissonLockStockFacade {

    private final RedissonClient redissonClient;
    private final StockService stockService;

    public void decrease(Long id, Long quantity) {
        RLock lock = redissonClient.getLock(id.toString());
        try {
            boolean availiable = lock.tryLock(20, 1, TimeUnit.SECONDS);

            if (!availiable) {
                System.out.println("lock 획득 실패");
                return;
            }

            stockService.decrease(id, quantity);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

}
