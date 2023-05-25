package org.launchcode.GameReview.controllers;

import org.launchcode.GameReview.data.GameTitleRepository;
import org.launchcode.GameReview.models.GameTitle;
import org.launchcode.GameReview.models.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("search")
public class SearchController {
    @Autowired
    GameTitleRepository gameTitleRepository;

    @RequestMapping
    public String search(Model model){
        model.addAttribute("title", "Search");
        return "search";
    }

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchTerm){
        Iterable<GameTitle> gameTitles;
        gameTitles = Search.searchTitleByTerm(searchTerm, gameTitleRepository.findAll());


        model.addAttribute("title", "Games with: " + searchTerm);
        model.addAttribute("gameTitles", gameTitles);

        return "search";
    }
}
