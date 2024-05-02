package com.abderazak.film.service;

import java.util.List;


import org.springframework.data.domain.Page;

import com.abderazak.film.entities.Film;
import com.abderazak.film.entities.Genre;

public interface FilmService {
	Film saveFilm(Film f);
	Film updateFilm(Film f);
	void deleteFilm(Film f);
	 void deleteFilmById(Long id);
	 Film getFilm(Long id);
	List<Film> getAllFilms();
	
	List<Film> findByNomFilm(String nom);
	List<Film> findByNomFilmContains(String nom);
	List<Film> findByNomRate (String nom, Double rate);
	List<Film> findByGenre (Genre genre);
	List<Film> findByGenreIdGen(Long id);
	List<Film> findByOrderByNomFilmAsc();
	List<Film> trierFilmsNomsRate();

	List<Genre> getAllGenres();

	
	
	
	Page<Film> getAllFilmsParPage(int page, int size);
}
