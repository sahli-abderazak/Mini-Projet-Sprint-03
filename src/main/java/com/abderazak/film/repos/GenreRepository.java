package com.abderazak.film.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abderazak.film.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
