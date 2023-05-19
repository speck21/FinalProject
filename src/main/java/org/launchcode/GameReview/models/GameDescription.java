package org.launchcode.GameReview.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

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
