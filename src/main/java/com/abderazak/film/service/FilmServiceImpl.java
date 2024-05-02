package com.abderazak.film.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.abderazak.film.entities.Film;
import com.abderazak.film.entities.Genre;
import com.abderazak.film.repos.FilmRepository;
import com.abderazak.film.repos.GenreRepository;

@Service
public class FilmServiceImpl  implements FilmService{
	
	@Autowired
	FilmRepository filmRepository;
	
	@Autowired
	GenreRepository genreRepository;

	
	@Override
	public Film saveFilm(Film f) {
		return filmRepository.save(f);
	}

	@Override
	public Film updateFilm(Film f) {
		return filmRepository.save(f);
	}

	@Override
	public void deleteFilm(Film f) {
		filmRepository.delete(f);
		
	}

	@Override
	public void deleteFilmById(Long id) {
		filmRepository.deleteById(id);
		
	}

	@Override
	public Film getFilm(Long id) {
		return filmRepository.findById(id).get();
	}

	@Override
	public List<Film> getAllFilms() {
		return filmRepository.findAll();
	}

	@Override
	public Page<Film> getAllFilmsParPage(int page, int size) {
		return filmRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Film> findByNomFilm(String nom) {
		return filmRepository.findByNomFilm(nom);

	}

	@Override
	public List<Film> findByNomFilmContains(String nom) {
		return filmRepository.findByNomFilmContains(nom);
	}

	@Override
	public List<Film> findByNomRate(String nom, Double rate) {
		return filmRepository.findByNomRate(nom, rate);

	}

	@Override
	public List<Film> findByGenre(Genre genre) {
		return filmRepository.findByGenre(genre);
	}

	@Override
	public List<Film> findByGenreIdGen(Long id) {
		return filmRepository.findByGenreIdGen(id);
	}

	@Override
	public List<Film> findByOrderByNomFilmAsc() {
		return filmRepository.findByOrderByNomFilmAsc();

	}

	@Override
	public List<Film> trierFilmsNomsRate() {
		return filmRepository.trierFilmsNomsRate();

	}

	@Override
	public List<Genre> getAllGenres() {
		return genreRepository.findAll();
	}

}
