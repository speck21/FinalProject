package org.launchcode.GameReview.models.dto;

import org.launchcode.GameReview.models.GameTitle;
import org.launchcode.GameReview.models.Genre;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

public class GameTitleGenreDTO {

    @NotNull
    private GameTitle gameTitle;

    @NotNull
    private Genre genre;

    public GameTitleGenreDTO() {}

    public GameTitle getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(GameTitle gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
