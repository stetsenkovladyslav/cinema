package com.example.admin.model;


import com.example.admin.enums.ImageFormat;
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

    @Enumerated(EnumType.ORDINAL)
    private ImageFormat format;

}
