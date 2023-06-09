package org.launchcode.GameReview.controllers;

import org.launchcode.GameReview.data.GameTitleRepository;
import org.launchcode.GameReview.data.GenreRepository;
import org.launchcode.GameReview.data.StudioRepository;
import org.launchcode.GameReview.models.GameTitle;
import org.launchcode.GameReview.models.Genre;
import org.launchcode.GameReview.models.dto.GameTitleGenreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("titles")
public class GameTitleController {
    @Autowired
    private GameTitleRepository gameTitleRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private StudioRepository studioRepository;

    // TODO:Add request param to display all titles of a genre
    @GetMapping
    public String displayGameTitles(Model model){
        model.addAttribute("title", "All Titles");
        model.addAttribute("gameTitles", gameTitleRepository.findAll());
        return "titles/index";
    }

    @GetMapping("create")
    public String renderGameTitleForm(Model model){
        model.addAttribute("title", "Game Title");
        model.addAttribute("gameTitle", new GameTitle());
        model.addAttribute("studios", studioRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        return "titles/create";
    }

    @PostMapping("create")
    public String processGameTitleForm(@Valid @ModelAttribute GameTitle gameTitle, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Game Titles");
            model.addAttribute("gameTitle", new GameTitle());
            model.addAttribute("studios", studioRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            return "titles/create";
        }

        gameTitleRepository.save(gameTitle);
        return "redirect:";
    }

    //details
    @GetMapping("detail")
    public String displayGameTitleDetails(@RequestParam Integer gameTitleId, Model model){
        Optional<GameTitle> result = gameTitleRepository.findById(gameTitleId);

        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Game ID: " + gameTitleId);
        }else{
            GameTitle gameTitle = result.get();
            model.addAttribute("title", gameTitle.getName() + " - Info");
            model.addAttribute("gameTitle", gameTitle);
            model.addAttribute("genres", gameTitle.getGenreList());
        }
        return "titles/detail";
    }


    @GetMapping("add-genre")
    public String renderAddGenreForm(@RequestParam Integer gameTitleId, Model model){
        Optional<GameTitle> result = gameTitleRepository.findById(gameTitleId);
        GameTitle gameTitle = result.get();
        model.addAttribute("title", "Add Genre to: " + gameTitle.getName());
        model.addAttribute("genres", genreRepository.findAll());
        GameTitleGenreDTO gameTitleGenreDTO = new GameTitleGenreDTO();
        gameTitleGenreDTO.setGameTitle(gameTitle);
        model.addAttribute("gameTitleGenreDTO", gameTitleGenreDTO);
        return "titles/add-genre";
    }

    @PostMapping("add-genre")
    public String processAddGenreForm(@ModelAttribute @Valid GameTitleGenreDTO gameTitleGenreDTO, Errors errors, Model model){
        if(!errors.hasErrors()){
            GameTitle gameTitle = gameTitleGenreDTO.getGameTitle();
            Genre genre = gameTitleGenreDTO.getGenre();
            if(!gameTitle.getGenreList().contains(genre)){
                gameTitle.addGenre(genre);
                gameTitleRepository.save(gameTitle);
            }
            return "redirect:detail?gameTitleId=" + gameTitle.getId();
        }
        return "redirect:add-genre";
    }

    @GetMapping("delete")
    public String deleteTitleForm(Model model){
        model.addAttribute("title", "Delete Titles");
        model.addAttribute("titles", gameTitleRepository.findAll());
        return "titles/delete";
    }

    @PostMapping("delete")
    public String processDeleteTitleForm(@RequestParam(required = false) int[] titleIds){
        if(titleIds != null){
            for(int id:titleIds){
                gameTitleRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

}


//add game description to a details page accessible by clicking on the specific game title on the main page