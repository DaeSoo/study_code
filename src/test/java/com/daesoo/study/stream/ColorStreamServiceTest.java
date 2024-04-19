package com.daesoo.study.stream;

import com.daesoo.study.nplus.entity.Color;
import com.daesoo.study.nplus.entity.repository.ColorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
public class ColorStreamServiceTest {
    @Autowired
    private ColorRepository colorRepository;
    @BeforeEach
    public void before() {
        colorRepository.saveAndFlush(new Color("red"));
        colorRepository.saveAndFlush(new Color("green"));
        colorRepository.saveAndFlush(new Color("black"));
    }

    @AfterEach
    public void after() {
        colorRepository.deleteAll();
    }


    @Test
    @DisplayName("이름정렬")
    public void Color_이름_정렬_조회(){
        /* 정렬 이름 조회*/
        List<Color> colorList = colorRepository.findAll().stream()
                .sorted().toList();
        /* 기본 이름 조회*/
        List<Color> colors = colorRepository.findAll();

        /* 역순 이름 조회*/
        List<Color> sortColors = colorRepository.findAll().stream()
                .sorted(Comparator.reverseOrder())
                .toList();

    }

}
