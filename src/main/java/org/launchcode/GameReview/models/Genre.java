package org.launchcode.GameReview.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre extends AbstractEntity{
    //TODO: Add many to many relationship between title
    //TODO: Add many to one with user
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "genreList")
    private final List<GameTitle> titleList = new ArrayList<>();

    public Genre(String name) {
        this.name = name;
    }

    public Genre() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
