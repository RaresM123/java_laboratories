package rmunteanu.models;

import java.io.Serializable;

public class wordsDict implements Serializable {
    private String language;
    private String word;
    private String definition;

    public wordsDict(){}

    public wordsDict(String language, String word, String definition){
        this.language = language;
        this.word = word;
        this.definition = definition;
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
}
