package com.daesoo.study.stock.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    private Long productId;

    private Long quantity;

//    @Version
//    private Long version;

    public Stock(final Long id, final Long quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public void decrease(final Long quantity){
        if (this.quantity - quantity < 0){
            throw new RuntimeException("재고부족");
        }
        this.quantity = this.quantity - quantity;
    }
}
