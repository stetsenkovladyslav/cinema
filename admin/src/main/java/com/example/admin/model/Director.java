package com.example.admin.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


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
