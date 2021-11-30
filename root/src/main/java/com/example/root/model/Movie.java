package com.example.root.model;

import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Column(name = "title")
    private String movieTitle;

    @Column(name = "description")
    private String movieDescription;

    @ManyToMany()
    @JoinTable(
            name = "movies_directors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    @Column(name = "director_name")
    private List<Director> directors;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_release")
    private LocalDate dateRelease;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private List<Video> videos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private List<Image> images;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id")
    private Rate rate;

    @OneToMany
    @JoinTable(
            name = "movies_comments",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @ToString.Exclude
    private List<Comment> comments;

    public void addImage(Image image) {
        images.add(image);
    }

    public void deleteImage(Image image) {
        images.remove(image);
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void deleteVideo(Video video) {
        videos.remove(video);
    }

}
