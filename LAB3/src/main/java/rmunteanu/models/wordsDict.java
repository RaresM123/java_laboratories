package rmunteanu.models;

import java.io.Serializable;

public class wordsDict implements Serializable {
    private String language;
    private String word;
    private String definition;
    private String currentDate;
    public wordsDict(){}

    public wordsDict(String language, String word, String definition, String currentDate){
        this.language = language;
        this.word = word;
        this.definition = definition;
        this.currentDate = currentDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
