package org.launchcode.GameReview.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameTitle extends AbstractEntity{

    //TODO: Add relationships (manymany genre, manyone studio
    @NotBlank(message = "Title name required.")
    private String name;

    @NotNull
    @ManyToOne
    @Valid
    private Studio studio;

    @NotNull
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    private GameDescription gameDescription;

    @NotNull
    @ManyToMany
    private final List<Genre> genreList = new ArrayList<>();

    public GameTitle(String name, Studio studio) {
        this.name = name;
        this.studio = studio;
    }

    public GameTitle() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    //delete studio
    public void deleteStudio(){this.studio = null; }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void addGenre(Genre genre) {
        this.genreList.add(genre);
    }

    //delete genre
    //need to update the form/page to be able to select a specific genre to remove
    public void deleteGenre(Genre genreRemove){
        //iterate through the genreList and remove specified genres
        for(Genre genre : genreList ){
            if(genre == genreRemove){
                genre = null;
            }
        }
    }

    public GameDescription getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(GameDescription gameDescription) {
        this.gameDescription = gameDescription;
    }

}
