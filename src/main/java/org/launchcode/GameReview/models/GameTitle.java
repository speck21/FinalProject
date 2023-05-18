//package org.launchcode.GameReview.models;
//
//import javax.persistence.Entity;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//@Entity
//public class GameTitle extends AbstractEntity{
//
//    //TODO: Add relationships (manymany genre, manyone studio
//    @NotBlank(message = "Title name required.")
//    private String name;
//
//    @NotNull
//    private Studio studio;
//
////    @NotNull
////    @ManyToMany
////    private final List<Genre> genreList = new ArrayList<>();
//
//    public GameTitle(String name, Studio studio) {
//        this.name = name;
//        this.studio = studio;
//    }
//
//    public GameTitle() {}
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Studio getStudio() {
//        return studio;
//    }
//
//    public void setStudio(Studio studio) {
//        this.studio = studio;
//    }
//
////    public List<Genre> getGenreList() {
////        return genreList;
////    }
////
////    public void AddGenre(Genre genre) {
////        this.genreList.add(genre);
////    }
//}
