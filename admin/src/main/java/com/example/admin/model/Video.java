package com.example.admin.model;

import com.example.admin.enums.VideoFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "video_name")
    private String videoName;

    @Enumerated(EnumType.ORDINAL)
    private VideoFormat format;
}
