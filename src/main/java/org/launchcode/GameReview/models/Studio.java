package org.launchcode.GameReview.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Studio extends AbstractEntity{
    //TODO: Add one to many with title
    @NotBlank
    @Size(min = 3, message = "Must be longer than 3 Characters")
    private String name;

    @NotNull
    @Min(value = 1950, message = "The studio predates computers?")
    @Max(value = 2024, message = "Year hasn't come to pass yet...")
    private Integer yearEstablished;

    @OneToMany(mappedBy = "studio")
    List<GameTitle> gameTitleList = new ArrayList<>();


    public Studio(String name, Integer yearEstablished) {
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

    public Integer getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(Integer yearEstablished) {this.yearEstablished = yearEstablished;}


}
