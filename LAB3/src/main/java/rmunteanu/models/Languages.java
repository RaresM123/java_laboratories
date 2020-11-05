package rmunteanu.models;

import java.util.*;

public class Languages {

    private ArrayList<String> languages;

    public Languages() {
        this.languages = new ArrayList<String>();
        languages.add("");
        languages.add("English");
        languages.add("Romana");
        languages.add("Franceza");
    }

    public Languages(Languages languages){
        this.languages = new ArrayList<>(languages.languages);
    }
    public ArrayList<String> getLanguages() {
        return languages;
    }
}
