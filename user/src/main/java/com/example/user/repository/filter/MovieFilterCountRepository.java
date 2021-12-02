package com.example.user.repository.filter;

import com.example.user.criteria.MovieSearchQuery;
import com.example.user.model.CountValue;

import java.util.List;

public interface MovieFilterCountRepository {
    List<CountValue> countGenre(MovieSearchQuery searchQuery);
    List<CountValue> countCountry(MovieSearchQuery searchQuery);
    List<CountValue> countMovieTitle(MovieSearchQuery searchQuery);
    List<CountValue> countDateAdded(MovieSearchQuery searchQuery);
    List<CountValue> countDateRelease(MovieSearchQuery searchQuery);
}
