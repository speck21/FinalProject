package org.launchcode.GameReview.service;
import javax.annotation.PostConstruct;

import org.launchcode.GameReview.data.GameTitleRepository;
import org.launchcode.GameReview.data.StudioRepository;
import org.launchcode.GameReview.models.Studio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseInitializerService {
    @Autowired
    GameTitleRepository gameTitleRepository;
    @Autowired
    StudioRepository studioRepository;

    @PostConstruct
    public void initializeDatabase(){
        Optional<Studio> unlistedStudioOptional = studioRepository.findByName("unlisted");
        if(!unlistedStudioOptional.isPresent()){
            Studio unlistedStudio = new Studio();
            unlistedStudio.setName("unlisted");
            unlistedStudio.setYearEstablished(1960);
            studioRepository.save(unlistedStudio);
        }
    }
}
