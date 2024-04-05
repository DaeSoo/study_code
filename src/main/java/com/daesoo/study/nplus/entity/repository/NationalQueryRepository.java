package com.daesoo.study.nplus.entity.repository;


import com.daesoo.study.nplus.entity.National;

import java.util.List;

public interface NationalQueryRepository {

    List<National> findAllNationalQueryDsl();
}
