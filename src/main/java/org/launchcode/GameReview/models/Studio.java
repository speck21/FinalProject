package org.launchcode.GameReview.models;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class Studio extends AbstractEntity{
    //TODO: Add one to many with title
    @NotBlank
    @Size(min = 3, message = "Must be longer than 3 Characters")
    private String name;

    @NotNull
    @Min(1950)
    @Max(2024)
    private int yearEstablished;

    public Studio(String name, int yearEstablished) {
        this.name = name;
        this.yearEstablished = yearEstablished;
    }

    public Studio() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {this.yearEstablished = yearEstablished;}



}
