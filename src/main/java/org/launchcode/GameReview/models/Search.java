package org.launchcode.GameReview.models;

import java.util.ArrayList;

public class Search {

    public static ArrayList<GameTitle> searchTitleByTerm(String searchTerm, Iterable<GameTitle> allGameTitles){
        ArrayList<GameTitle> results = new ArrayList<>();

        //search through all fields of a gametitle
        //add to list if titles have a full or partial match
        //Don't add the same title twice

        for(GameTitle title:allGameTitles){
            if(title.getName().toLowerCase().contains(searchTerm.toLowerCase())){
                if(!results.contains(title.getId())){
                    results.add(title);
                }
            }
        }

        return results;
    }
    //add functionality to search by all fields specifically, and hyperlink each field to view all of that type
}
