//package org.launchcode.GameReview.controllers;
//
//import org.launchcode.GameReview.data.GenreRepository;
//import org.launchcode.GameReview.data.StudioRepository;
//import org.launchcode.GameReview.data.TitleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("titles")
//public class TitleController {
//    @Autowired
//    private TitleRepository titleRepository;
//
//    @Autowired
//    private GenreRepository genreRepository;
//
//    @Autowired
//    private StudioRepository studioRepository;
//
//    // TODO:Add request param to display all titles of a genre
////    @GetMapping
////    public String displayTitles(Model model){
////        model.addAttribute("title", "All Titles");
////        model.addAttribute("titles", titleRepository.findAll());
////        return "titles/index";
////    }
//
//}
