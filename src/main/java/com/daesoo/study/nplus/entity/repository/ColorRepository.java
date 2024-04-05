package com.daesoo.study.nplus.entity.repository;

import com.daesoo.study.nplus.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{
}
