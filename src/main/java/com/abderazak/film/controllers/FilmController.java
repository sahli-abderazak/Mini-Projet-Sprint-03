package com.abderazak.film.controllers;

import org.springframework.stereotype.Controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abderazak.film.entities.Film;
import com.abderazak.film.entities.Genre;
import com.abderazak.film.service.FilmService;

import jakarta.validation.Valid;

@Controller
public class FilmController {
	@Autowired
	FilmService filmService;

	@GetMapping(value = "/")
	public String welcome() {
	 return "index";
	}
	@RequestMapping("/ListeFilms")
	public String listeFilms(ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) 
	{
		Page<Film> film = filmService.getAllFilmsParPage(page, size);
		modelMap.addAttribute("films", film);
		modelMap.addAttribute("pages", new int[film.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeFilms";
	}

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		List<Genre> gens = filmService.getAllGenres();
		modelMap.addAttribute("film", new Film());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("genres", gens);
		return "formFilm";
	}

	@RequestMapping("/saveFilm")
	public String saveFilm(@Valid Film film, BindingResult bindingResult,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size",defaultValue = "2") int size)
	{
		int currentPage;
		boolean isNew = false;
		if (bindingResult.hasErrors()) return "formFilm";
		if (film.getIdFilm()==null) //ajout
			isNew=true;
		filmService.saveFilm(film);
		if (isNew) //ajout
		{
		Page<Film> films = filmService.getAllFilmsParPage(page, size);
		currentPage = films.getTotalPages()-1;
		}
		else //modif
			currentPage=page;
//		return "formFilm";
		return ("redirect:/ListeFilms?page="+currentPage+"&size="+size);
	}
	
	
	@RequestMapping("/supprimerFilm")
	public String supprimerFilm(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size
			) {
		filmService.deleteFilmById(id);
		
		
		Page<Film> film = filmService.getAllFilmsParPage(page,size);
		modelMap.addAttribute("films", film);
		modelMap.addAttribute("pages", new int[film.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeFilms";
	}

	@RequestMapping("/modifierFilm")
	public String editerFilm(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		Film f = filmService.getFilm(id);
		List<Genre> gens = filmService.getAllGenres();
		modelMap.addAttribute("film", f);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("genres", gens);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);
		return "formFilm";
	}

	@RequestMapping("/updateFilm")
	public String updateFilm(@ModelAttribute("film") Film film, @RequestParam("date") String date, ModelMap modelMap)
			throws ParseException {
//conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateSortie = dateformat.parse(String.valueOf(date));
		film.setDateSortie(dateSortie);

		filmService.updateFilm(film);
		List<Film> film1 = filmService.getAllFilms();
		modelMap.addAttribute("films", film1);
//		return "listeProduits";
		return "listeFilms";
	}

}
