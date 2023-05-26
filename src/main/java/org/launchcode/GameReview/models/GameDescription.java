package org.launchcode.GameReview.models;

import javax.persistence.Entity;

@Entity
public class GameDescription extends AbstractEntity{

    private String description;

    public GameDescription(String description) {
        this.description = description;
    }

    public GameDescription() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
