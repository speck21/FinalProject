package org.launchcode.GameReview.controllers;

import org.launchcode.GameReview.data.GenreRepository;
import org.launchcode.GameReview.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("genres")
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;



    @GetMapping
    public String displayAllGenres(Model model){
        model.addAttribute("title", "Genre List");
        model.addAttribute("genres", genreRepository.findAll());
        return "genres/index";
    }

    @GetMapping("create")
    public String renderCreateGenreForm(Model model){
        model.addAttribute("title", "Add Genre");
        model.addAttribute(new Genre());
        return "genres/create";
    }

    @PostMapping("create")
    public String processCreateGenreForm(@Valid @ModelAttribute Genre genre, Errors errors, Model model){

        if(errors.hasErrors()){
            model.addAttribute("title", "Add Genre");
            model.addAttribute(new Genre());
            return "genres/create";
        }
        genreRepository.save(genre);
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayGenreDetails(@RequestParam Integer genreId, Model model){
        Optional<Genre> result = genreRepository.findById(genreId);

        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Genre ID: " + genreId);
        }else{
            Genre genre = result.get();
            model.addAttribute("title", genre.getName() + " - Info");
            model.addAttribute("genre", genre);
        }
        return "genres/detail";
    }

}
