package com.example.root.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie extends BaseEntity{

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

    @ManyToMany
    @JoinTable(name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @Column(name = "genre_name")
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(name = "movie_countries",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    @Column(name = "country_name")
    private List<Country> countries;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name  = "movie_id")
    @ToString.Exclude
    private List<Video> videos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name  = "movie_id")
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
