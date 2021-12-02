package com.example.user.page;

import com.example.user.dto.MoviePreviewDTO;
import com.example.user.model.CountValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
public class MoviePage extends PageImpl<MoviePreviewDTO> {
    private final Counts counts;

    public MoviePage(List<MoviePreviewDTO> content, Pageable pageable, long total, Counts counts) {
        super(content, pageable, total);
        this.counts = counts;
    }

    @Getter
    public static class Counts {
        private List<CountValue> genre;
        private List<CountValue> country;
        private List<CountValue> movieTitle;
        private List<CountValue> dateAdded;
        private List<CountValue> dateRelease;

        public Counts(List<CountValue> genre,
                      List<CountValue> country,
                      List<CountValue> movieTitle,
                      List<CountValue> dateAdded,
                      List<CountValue> dateRelease) {
            this.genre = genre;
            this.country = country;
            this.movieTitle = movieTitle;
            this.dateAdded = dateAdded;
            this.dateRelease = dateRelease;

        }
    }
}