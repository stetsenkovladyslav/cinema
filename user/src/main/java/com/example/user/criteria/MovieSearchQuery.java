package com.example.user.criteria;

import com.amazonaws.util.CollectionUtils;
import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import com.example.user.persistance.Criterion;
import com.example.user.persistance.SearchQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class MovieSearchQuery implements SearchQuery {
    private List<Genre> genre;
    private List<Country> country;
    private List<String> movieTitle;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> dateAdded;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private List<LocalDate> dateRelease;

    public MovieSearchQuery(MovieSearchQuery searchQuery) {
        this.genre = searchQuery.genre;
        this.country = searchQuery.country;
        this.movieTitle = searchQuery.movieTitle;
        this.dateAdded = searchQuery.dateAdded;
        this.dateRelease = searchQuery.dateRelease;
    }

    @Override
    public Criterion buildCriterion() {
        return ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!CollectionUtils.isNullOrEmpty(genre)) {
                Predicate genrePredicate = cb.equal(root.get("genre"), genre);
                predicates.add(genrePredicate);
            }
            if (!CollectionUtils.isNullOrEmpty(genre)) {
                Predicate countryPredicate = cb.equal(root.get("country"), country);
                predicates.add(countryPredicate);
            }

            if (!CollectionUtils.isNullOrEmpty(movieTitle)) {
                Predicate titlePredicate = cb.equal(root.get("movieTitle"), movieTitle);
                predicates.add(titlePredicate);
            }
            if (!CollectionUtils.isNullOrEmpty(dateAdded)) {
                Predicate dateAddedPredicate = cb.equal(root.get("dateAdded"), dateAdded);
                predicates.add(dateAddedPredicate);
            }
            if (!CollectionUtils.isNullOrEmpty(dateRelease)) {
                Predicate dateReleasePredicate = cb.equal(root.get("dateRelease"), dateRelease);
                predicates.add(dateReleasePredicate);
            }

            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        });

    }
}
