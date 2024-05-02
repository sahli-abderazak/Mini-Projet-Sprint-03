package com.abderazak.film;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.abderazak.film.entities.Film;
import com.abderazak.film.service.FilmService;

@SpringBootApplication
public class FilmApplication implements CommandLineRunner{

//	@Autowired
//	FilmService filmService;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(FilmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		filmService.saveFilm(new Film("aka", 4.0, new Date()));
//		filmService.saveFilm(new Film("that's my boy", 2.0, new Date()));
//		filmService.saveFilm(new Film("the body", 3.0, new Date()));
		
		repositoryRestConfiguration.exposeIdsFor(Film.class);
	}

}
