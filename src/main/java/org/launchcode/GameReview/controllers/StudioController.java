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

    @GetMapping("delete")
    public String deleteStudioForm(Model model){
        model.addAttribute("title", "Delete Studio");
        model.addAttribute("studios", studioRepository.findAll());
        return "studios/delete";
    }

    @PostMapping("delete")
    public String processDeleteStudioForm(@RequestParam(required = false) int[] studioIds, Model model){
        if(studioIds != null){
            for(int id:studioIds){
                Optional<Studio> result = studioRepository.findById(id);
                Studio studio = result.get();
                if(studio.getGameTitleList().isEmpty()){
                    studioRepository.deleteById(id);
                }else{
                    model.addAttribute("title", "Error Removing Studio");
                    model.addAttribute("errorMessage", "Cannot remove a Studio that still has game titles associated with it. Remove the game, or update the title, before deleting the defunct studio.");
                    return "/titles/error";
                }
            }
        }
        return "redirect:";
    }
}



