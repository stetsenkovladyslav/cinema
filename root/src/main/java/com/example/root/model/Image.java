package com.example.root.model;


import com.example.root.enums.ImageFormat;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "images")
public class Image extends BaseEntity {

    @Column(name = "image_name")
    private String imageName;

    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @ManyToOne
    private Movie movie;

    @Enumerated(EnumType.ORDINAL)
    private ImageFormat format;

}
