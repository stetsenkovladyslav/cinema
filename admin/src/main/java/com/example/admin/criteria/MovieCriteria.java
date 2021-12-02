package com.example.admin.criteria;


import com.amazonaws.util.CollectionUtils;
import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import com.example.root.model.Director;
import com.example.root.model.Movie;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class MovieCriteria {
    private List<Genre> genre;
    private List<Country> country;
    private List<String> directorName;
    private List<String> movieTitle;


    public Specification<Movie> buildCriteria() {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!CollectionUtils.isNullOrEmpty(genre)) {
                Predicate genrePredicate = criteriaBuilder.equal(root.get("genre"), genre);
                predicates.add(genrePredicate);
            }
            if (!CollectionUtils.isNullOrEmpty(country)) {
                Predicate countryPredicate = criteriaBuilder.equal(root.get("country"), country);
                predicates.add(countryPredicate);
            }

            if (!CollectionUtils.isNullOrEmpty(movieTitle)) {
                Predicate titlePredicate = criteriaBuilder.equal(root.get("movieTitle"), movieTitle);
                predicates.add(titlePredicate);
            }

            if (!CollectionUtils.isNullOrEmpty(directorName)) {
                ListJoin<Movie, Director> joinedDirectorList = root.joinList("directors");
                predicates.add(criteriaBuilder.equal(joinedDirectorList.get("directorName"), directorName)
                );
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });


    }
}