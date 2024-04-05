package com.daesoo.study.nplus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Wine {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_COLOR"))
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "national_id", foreignKey = @ForeignKey(name = "FK_SUBJECT_NATIONAL"))
    private National national;

    @Builder
    public Wine(String name, Color color, National national) {
        this.name = name;
        this.color = color;
        this.national = national;
    }

    public void updateNational(National national){
        this.national = national;
    }
}
