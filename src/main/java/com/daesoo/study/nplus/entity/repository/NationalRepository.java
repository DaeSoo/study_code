package com.daesoo.study.nplus.entity.repository;

import com.daesoo.study.nplus.entity.National;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationalRepository extends JpaRepository<National, Long> , NationalQueryRepository{

    @Query("select a from National a join fetch a.wines")
    List<National> findAllJoinFetch();

    @Query("select a from National a join fetch a.wines s join fetch s.color")
    List<National> findAllWithColors();

    @EntityGraph(attributePaths = "wines")
    @Query("select a from National a")
    List<National> findAllEntityGraph();



}
