package com.daesoo.study.nplus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Color implements Comparable<Color>{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Color(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Color o) {
        return this.name.compareTo(o.name);
    }
}
