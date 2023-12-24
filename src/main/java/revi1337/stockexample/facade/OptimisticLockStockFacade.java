package revi1337.stockexample.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import revi1337.stockexample.service.OptimisticLockStockService;

@RequiredArgsConstructor
@Component
public class OptimisticLockStockFacade {

    private final OptimisticLockStockService optimisticLockStockService;

    public void decrease(Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                optimisticLockStockService.decrease(id, quantity);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }

}
