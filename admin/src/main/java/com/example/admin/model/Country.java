package com.example.admin.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "countries")
public class Country extends BaseEntity{

    @Column(name = "country_name")
    private String countryName;

}
