package com.example.root.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "countries")
public class Country extends BaseEntity{

    @Column(name = "country_name")
    private String countryName;

}
