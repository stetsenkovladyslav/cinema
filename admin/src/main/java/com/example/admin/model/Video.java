package com.example.admin.model;

import com.example.admin.enums.VideoFormat;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "videos")
public class Video extends BaseEntity {

    @Column(name = "video_name")
    private String videoName;

    @Enumerated(EnumType.ORDINAL)
    private VideoFormat format;
}
