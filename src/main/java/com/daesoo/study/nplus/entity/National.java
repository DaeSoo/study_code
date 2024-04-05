package com.daesoo.study.nplus.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class National {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="wine_id")
    private List<Wine> wines = new ArrayList<>();

    @Builder
    public National(String name, List<Wine> wines) {
        this.name = name;
        if(wines != null){
            this.wines = wines;
        }
    }

    public void addWine(Wine wine){
        this.wines.add(wine);

    }
}
