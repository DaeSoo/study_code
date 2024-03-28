package com.daesoo.study.redis;

import com.daesoo.study.stock.entity.StockService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonLockStockFacade {

    private RedissonClient redissonClient;
    private StockService stockService;

    public void decrease(Long key, Long quantity){
        RLock lock = redissonClient.getLock(key.toString());

        try{
            boolean available = lock.tryLock(5,1, TimeUnit.SECONDS); // lock 획득
            if(!available){
                System.out.println("lock 획득 실패");
                return;
            }
            stockService.decrease(key,quantity); //서비스 로직 실

        } catch(InterruptedException e){
            throw new RuntimeException(e);

        } finally{
            lock.unlock(); //lock 해제
        }
    }
}
