package org.launchcode.GameReview.models.dto;

import org.launchcode.GameReview.models.GameTitle;
import org.launchcode.GameReview.models.Video;

import javax.validation.constraints.NotNull;

public class GameTitleVideoDTO {

    @NotNull
    private GameTitle gameTitle;

    @NotNull
    private Video video;

    public GameTitleVideoDTO() {}

    public GameTitle getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(GameTitle gameTitle) {
        this.gameTitle = gameTitle;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
