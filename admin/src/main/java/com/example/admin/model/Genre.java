package com.example.admin.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "genres")
public class Genre extends BaseEntity {


    @Column(name = "genre_name")
    private String genreName;

}
