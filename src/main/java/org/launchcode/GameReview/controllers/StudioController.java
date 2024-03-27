package org.launchcode.GameReview.controllers;

import org.launchcode.GameReview.data.GameTitleRepository;
import org.launchcode.GameReview.data.StudioRepository;
import org.launchcode.GameReview.models.GameTitle;
import org.launchcode.GameReview.models.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.Optional;

@Controller
@RequestMapping("studios")
public class StudioController {

    @Autowired
    private StudioRepository studioRepository;
    @Autowired
    private GameTitleRepository gameTitleRepository;

    @GetMapping
    public String displayAllStudios(Model model){
        model.addAttribute("title", "Studio List");
        model.addAttribute("studios", studioRepository.findAll());
        return "studios/index";
    }
    @GetMapping("create")
    public String renderCreateStudioForm(Model model){
        model.addAttribute("title", "Add Studio");
        model.addAttribute(new Studio());
        return "studios/create";
    }

    @PostMapping("create")
    public String processCreateStudioForm(@Valid @ModelAttribute Studio studio, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Studio");
            model.addAttribute(new Studio());
            return "studios/create";
        }
        studioRepository.save(studio);
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayStudioDetails(@RequestParam Integer studioId, Model model){
        Optional<Studio> result = studioRepository.findById(studioId);

        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Studio ID: " + studioId);
        }else{
            Studio studio = result.get();
            model.addAttribute("title", studio.getName() + " - Info");
            model.addAttribute("studio", studio);
        }
        return "studios/detail";
    }

    @PostMapping("delete-studio")
    public String deleteStudio(@RequestParam Integer studioId){
        //i dont think model is needed cause we'll redirect to index upon deletion, if the JS doesn't just
        //reload the page

        //search through all gametitles and null the studio, then delete the studio
        Iterable<GameTitle> games = gameTitleRepository.findAll();
        for(GameTitle game: games){
            if(game.getStudio().getId() == studioId){
                game.setStudio(studioRepository.findByName("unlisted").get());
            }
        }
        studioRepository.deleteById(studioId);
        return "studios/index";
    }
}
