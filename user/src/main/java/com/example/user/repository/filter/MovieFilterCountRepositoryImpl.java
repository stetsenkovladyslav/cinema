package com.example.user.repository.filter;

import com.example.user.criteria.MovieSearchQuery;
import com.example.user.model.CountValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieFilterCountRepositoryImpl implements MovieFilterCountRepository {
    private final EntityManager entityManager;

    public List<CountValue> countGenre(MovieSearchQuery searchQuery){
        StringBuilder query =
                new StringBuilder("SELECT new com.example.user.model.CountValue(movie.genre, COUNT(DISTINCT movie)) FROM Movie movie ");
        TypedQuery<CountValue> typedQuery =
                entityManager.createQuery(
                        query.append(buildQuery(searchQuery, query, Criteria.GENRE))
                                .append("GROUP BY movie.genre")
                                .toString(), CountValue.class);
        setParameters(typedQuery, searchQuery, Criteria.GENRE);
        return typedQuery.getResultList();
    }

    public List<CountValue> countCountry(MovieSearchQuery searchQuery){
        StringBuilder query =
                new StringBuilder("SELECT new com.example.user.model.CountValue(movie.country, COUNT(DISTINCT movie)) FROM Movie movie ");
        TypedQuery<CountValue> typedQuery =
                entityManager.createQuery(
                        query.append(buildQuery(searchQuery, query, Criteria.COUNTRY))
                                .append("GROUP BY movie.country")
                                .toString(), CountValue.class);
        setParameters(typedQuery, searchQuery, Criteria.COUNTRY);
        return typedQuery.getResultList();
    }

    public List<CountValue> countMovieTitle(MovieSearchQuery searchQuery){
        StringBuilder query =
                new StringBuilder("SELECT new com.example.user.model.CountValue(movie.movieTitle, COUNT(DISTINCT movie)) FROM Movie movie ");
        TypedQuery<CountValue> typedQuery =
                entityManager.createQuery(
                        query.append(buildQuery(searchQuery, query, Criteria.MOVIE_TITLE))
                                .append("GROUP BY movie.movieTitle")
                                .toString(), CountValue.class);
        setParameters(typedQuery, searchQuery, Criteria.MOVIE_TITLE);
        return typedQuery.getResultList();
    }

    public List<CountValue> countDateAdded(MovieSearchQuery searchQuery){
        StringBuilder query =
                new StringBuilder("SELECT new com.example.user.model.CountValue(movie.dateAdded, COUNT(DISTINCT movie)) FROM Movie movie ");
        TypedQuery<CountValue> typedQuery =
                entityManager.createQuery(
                        query.append(buildQuery(searchQuery, query, Criteria.DATE_ADDED))
                                .append("GROUP BY movie.dateAdded")
                                .toString(), CountValue.class);
        setParameters(typedQuery, searchQuery, Criteria.DATE_ADDED);
        return typedQuery.getResultList();
    }

    public List<CountValue> countDateRelease(MovieSearchQuery searchQuery){
        StringBuilder query =
                new StringBuilder("SELECT new com.example.user.model.CountValue(movie.dateRelease, COUNT(DISTINCT movie)) FROM Movie movie ");
        TypedQuery<CountValue> typedQuery =
                entityManager.createQuery(
                        query.append(buildQuery(searchQuery, query, Criteria.DATE_RELEASE))
                                .append("GROUP BY movie.dateRelease")
                                .toString(), CountValue.class);
        setParameters(typedQuery, searchQuery, Criteria.DATE_RELEASE);
        return typedQuery.getResultList();
    }

    private StringBuilder buildQuery(MovieSearchQuery searchQuery, StringBuilder query, Criteria ignoreCriteria){
        StringBuilder where = new StringBuilder("WHERE 1=1");

        if (!CollectionUtils.isEmpty(searchQuery.getGenre()) && !ignoreCriteria.equals(Criteria.GENRE)) {
            where.append("AND movie.genre in (:genre) ");
        }
        if (!CollectionUtils.isEmpty(searchQuery.getCountry()) && !ignoreCriteria.equals(Criteria.COUNTRY)) {
            where.append("AND movie.country in (:country) ");
        }
        if (!CollectionUtils.isEmpty(searchQuery.getMovieTitle()) && !ignoreCriteria.equals(Criteria.MOVIE_TITLE)) {
            where.append("AND movie.movieTitle in (:movieTitle) ");
        }
        if (!CollectionUtils.isEmpty(searchQuery.getDateAdded()) && !ignoreCriteria.equals(Criteria.DATE_ADDED)) {
            where.append("AND movie.dateAdded in (:dateAdded) ");
        }
        if (!CollectionUtils.isEmpty(searchQuery.getDateRelease()) && !ignoreCriteria.equals(Criteria.DATE_RELEASE)) {
            where.append("AND movie.dateRelease in (:dateRelease) ");
        }
        return  where;
    }

    private void setParameters(TypedQuery<CountValue> typedQuery, MovieSearchQuery searchQuery, Criteria ignoreCriteria){

        if (!CollectionUtils.isEmpty(searchQuery.getGenre()) && !ignoreCriteria.equals(Criteria.GENRE)) {
            typedQuery.setParameter("genre", searchQuery.getGenre());
        }
        if (!CollectionUtils.isEmpty(searchQuery.getCountry()) && !ignoreCriteria.equals(Criteria.COUNTRY)) {
            typedQuery.setParameter("country", searchQuery.getCountry());
        }
        if (!CollectionUtils.isEmpty(searchQuery.getMovieTitle()) && !ignoreCriteria.equals(Criteria.MOVIE_TITLE)) {
            typedQuery.setParameter("movieTitle", searchQuery.getMovieTitle());
        }
        if (!CollectionUtils.isEmpty(searchQuery.getDateAdded()) && !ignoreCriteria.equals(Criteria.DATE_ADDED)) {
            typedQuery.setParameter("dateAdded", searchQuery.getDateAdded());
        }
        if (!CollectionUtils.isEmpty(searchQuery.getDateRelease()) && !ignoreCriteria.equals(Criteria.DATE_RELEASE)) {
            typedQuery.setParameter("dateRelease", searchQuery.getDateRelease());
        }

    }

    private enum Criteria{
        GENRE,
        COUNTRY,
        MOVIE_TITLE,
        DATE_ADDED,
        DATE_RELEASE
    }
}
