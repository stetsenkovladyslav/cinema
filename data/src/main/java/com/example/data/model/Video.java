package com.example.data.model;

import com.example.data.enums.VideoFormat;
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
