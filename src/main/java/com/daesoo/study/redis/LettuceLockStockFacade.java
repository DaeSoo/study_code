package com.daesoo.study.redis;

import com.daesoo.study.stock.entity.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LettuceLockStockFacade {
    private final RedisRepository redisLockRepository;
    private final StockService stockService;

    public void decrease(Long key, Long quantity) throws InterruptedException {

        while(!redisLockRepository.lock(key)){ //계속해서 lock 획득 시도
            Thread.sleep(100); //Spinlock 방식이 redis에게 주는 부하를 줄여주기 위해 sleep
        }

        try{
            stockService.decrease(key,quantity);
        } finally{
            redisLockRepository.unlock(key);
        }

    }
}
