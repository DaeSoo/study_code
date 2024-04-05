package com.daesoo.study.nplus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Color {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Color(String name) {
        this.name = name;
    }
}
