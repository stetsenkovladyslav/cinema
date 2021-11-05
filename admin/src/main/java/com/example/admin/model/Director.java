package com.example.admin.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "directors")
@NoArgsConstructor
@AllArgsConstructor
public class Director extends BaseEntity{

    @Column(name = "director_name")
    private String directorName;
}
