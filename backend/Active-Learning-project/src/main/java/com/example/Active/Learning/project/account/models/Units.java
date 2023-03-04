package com.example.Active.Learning.project.account.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "units")
@NoArgsConstructor
public class Units {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20)
    private String name;
    private String contentUrl;

    public Units(Integer id, String name, String contentUrl) {
        this.id = id;
        this.name = name;
        this.contentUrl = contentUrl;
    }
}
