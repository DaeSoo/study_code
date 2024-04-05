package com.daesoo.study.nplus.entity.service;

import com.daesoo.study.nplus.entity.National;
import com.daesoo.study.nplus.entity.repository.NationalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NationalServiceImpl implements NationalService{

    private final NationalRepository nationalRepository;

    @Transactional
    public List<String> findAllNationals(List<National> nationals){
        return extractNationalNames(nationals);
    }

    private List<String> extractNationalNames(List<National> nationals){
        return nationals.stream().map(a -> a.getWines().get(0).getName())
                .collect(Collectors.toList());
    }


}
