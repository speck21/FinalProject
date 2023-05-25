package org.launchcode.GameReview.controllers;

import org.launchcode.GameReview.data.StudioRepository;
import org.launchcode.GameReview.models.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("studios")
public class StudioController {

    //TODO: remove create links from nav bar add button to bottom of list page
    @Autowired
    private StudioRepository studioRepository;

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
}
