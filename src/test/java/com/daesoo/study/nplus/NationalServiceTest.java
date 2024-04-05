package com.daesoo.study.nplus;

import com.daesoo.study.nplus.entity.Color;
import com.daesoo.study.nplus.entity.National;
import com.daesoo.study.nplus.entity.Wine;
import com.daesoo.study.nplus.entity.repository.ColorRepository;
import com.daesoo.study.nplus.entity.repository.NationalRepository;
import com.daesoo.study.nplus.entity.repository.WineRepository;
import com.daesoo.study.nplus.entity.service.NationalService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NationalServiceTest {

    @Autowired
    private NationalService nationalService;

    @Autowired
    private NationalRepository nationalRepository;

    @Autowired
    private ColorRepository colorRepository;

    @BeforeEach
    public void before() {
        List<National> nationals = new ArrayList<>();
        Color color = colorRepository.saveAndFlush(new Color("red"));

        for(int i=0; i<10; i++){
            National national = National.builder()
                    .name("국가"+i)
                    .build();

            national.addWine(Wine.builder()
                    .name("포도_와인"+i)
                    .color(color)
                    .build());
            national.addWine(Wine.builder()
                    .name("복숭아_와인"+i)
                    .color(color)
                    .build());
            nationals.add(national);
        }

        nationalRepository.saveAllAndFlush(nationals);
    }
    @AfterEach
    public void after() {
        nationalRepository.deleteAll();
    }

    @Test
    public void National_조회시_Wine_NPLUS1_발생() {
        List<String> wineNames = nationalService.findAllNationals(nationalRepository.findAll());

        assertEquals(wineNames.size(), 10);
    }

    /**
     * @Query를 사용하여 Fetch 조인을 통한 조회
     */
    @Test
    public void Query를_이용한_Fetch_join_조회(){
        List<String> wineNames = nationalService.findAllNationals(nationalRepository.findAllJoinFetch());

        assertEquals(wineNames.size(), 10);
    }

    /**
     * @Query를 사용하여 Fetch 조인을 통한 조회
     */
    @Test
    public void Wine_Color_National_Query를_이용한_Fetch_join_한번에조회(){
        List<String> wineNames = nationalService.findAllNationals(nationalRepository.findAllWithColors());

        assertEquals(wineNames.size(), 10);
    }

    /**
     * @EntityGraph 사용
     */
    @Test
    public void EntityGraph를_이용한_조회(){
        List<National> nationals = nationalRepository.findAllEntityGraph();
        List<String> wines = nationalService.findAllNationals(nationals);

        assertEquals(nationals.size(), 10);
        assertEquals(wines.size(), 10);

    }

    /**
     * QueryDsl 사용
     */
    @Test
    public void queryDsl를_이용한_조회(){
        List<National> nationals = nationalRepository.findAllNationalQueryDsl();
        List<String> wines = nationalService.findAllNationals(nationals);

        assertEquals(nationals.size(), 10);
        assertEquals(wines.size(), 10);
    }

}
