package com.example.admin.criteria;


import com.example.root.enums.Country;
import com.example.root.enums.Genre;
import com.example.root.model.Director;
import com.example.root.model.Movie;
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
    private String country;
    private String directorName;
    private String title;


    public Specification<Movie> buildCriteria() {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (genre != null && !genre.isBlank()) {
                Predicate movieNamePredicate = criteriaBuilder.equal(root.get("genre"), genre);
                predicates.add(movieNamePredicate);
            }
            if (country != null && !country.isBlank()) {
                Predicate movieNamePredicate = criteriaBuilder.equal(root.get("country"), country);
                predicates.add(movieNamePredicate);
            }

            if (title != null && !title.isBlank()) {
                Predicate movieNamePredicate = criteriaBuilder.equal(root.get("movies_title"), title);
                predicates.add(movieNamePredicate);
            }

            if (Objects.nonNull(directorName)) {
                ListJoin<Movie, Director> joinedDirectorList = root.joinList("directors");
                predicates.add(criteriaBuilder.equal(joinedDirectorList.get("directorName"), directorName)
                );
            }
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });


    }
}