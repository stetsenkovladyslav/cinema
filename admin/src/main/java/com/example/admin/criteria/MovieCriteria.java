package com.example.admin.criteria;


import com.example.admin.model.Country;
import com.example.admin.model.Director;
import com.example.admin.model.Genre;
import com.example.admin.model.Movie;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@RequiredArgsConstructor
public class MovieCriteria {
    private String genre;
    private String countryName;
    private String directorName;

    public Specification<Movie> buildCriteria() {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(genre)) {
                ListJoin<Movie, Genre> joinedGenreList = root.joinList("genres");
                predicates.add(criteriaBuilder.equal(joinedGenreList.get("genreName"), genre)
                );
            }
            if (Objects.nonNull(directorName)) {
                ListJoin<Movie, Director> joinedDirectorList = root.joinList("directors");
                predicates.add(criteriaBuilder.equal(joinedDirectorList.get("directorName"), directorName)
                );
            }
            if (Objects.nonNull(countryName)) {
                ListJoin<Movie, Country> joinedCountryList = root.joinList("countries");
                predicates.add(criteriaBuilder.equal(joinedCountryList.get("countryName"), countryName)
                );
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });


    }
}