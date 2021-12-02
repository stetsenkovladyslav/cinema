package com.example.user.repository;


import com.example.root.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO favorite_films (user_id, movie_id) VALUES (?1, ?2 )", nativeQuery = true)
    void addToFavorite(Long userId, Long movieId);


    @Query(value = "SELECT m.* FROM favorite_films ff LEFT OUTER JOIN movies m on ff.movie_id = m.id WHERE ff.user_id = ?1",nativeQuery = true)
    Page<Movie> findAllFavoriteByUserId(Long userId, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO history (user_id, movie_id) VALUES (?1, ?2 ) ON CONFLICT DO NOTHING", nativeQuery = true)
    void addToHistory(Long userId, Long movieId);

    @Query(value = "SELECT m.* FROM history h LEFT OUTER JOIN movies m on h.movie_id = m.id WHERE h.user_id = ?1",nativeQuery = true)
    Page<Movie> findAllMoviesInHistoryByUserId(Long userId, Pageable pageable);


    @Query("SELECT DISTINCT movie.dateRelease FROM Movie movie")
    List<LocalDate> findAllDateRelease();

    @Query("SELECT DISTINCT movie.dateAdded FROM Movie movie")
    List<LocalDate>  findAllDateAdded();

    @Query("SELECT DISTINCT movie.movieTitle FROM Movie movie")
    List<String>  findAllMovieTitle();
}
