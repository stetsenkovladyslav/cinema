package com.example.root.model;

import com.example.root.enums.VideoFormat;
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

    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    @ManyToOne
    private Movie movie;


    @Enumerated(EnumType.ORDINAL)
    private VideoFormat format;
}
