package com.example.admin.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "directors")
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "director_name")
    private String directorName;

    public Director(String directorName) {
        this.directorName = directorName;
    }
}
