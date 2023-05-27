package org.launchcode.GameReview.models;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Video extends AbstractEntity{

    @Column(name = "embed_video_url", length = 500)
    private String embedVideoUrl;

    public Video(String embedVideoUrl) {
        this.embedVideoUrl = embedVideoUrl;
    }

    public Video() {}

    public String getEmbedVideoUrl() {
        return embedVideoUrl;
    }

    public void setEmbedVideoUrl(String embedVideoUrl) {
        this.embedVideoUrl = embedVideoUrl;
    }
}
