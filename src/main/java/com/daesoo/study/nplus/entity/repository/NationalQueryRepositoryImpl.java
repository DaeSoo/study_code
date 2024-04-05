package com.daesoo.study.nplus.entity.repository;

import com.daesoo.study.nplus.entity.National;
import com.daesoo.study.nplus.entity.QNational;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.daesoo.study.nplus.entity.QNational.national;
import static com.daesoo.study.nplus.entity.QWine.wine;


@Repository
@RequiredArgsConstructor
public class NationalQueryRepositoryImpl implements NationalQueryRepository{
    private final JPQLQueryFactory jpqlQueryFactory;

    @Override
    public List<National> findAllNationalQueryDsl(){
        return jpqlQueryFactory.select(national)
                .from(national).leftJoin(national.wines, wine)
                .fetchJoin().fetch();
    }

}
