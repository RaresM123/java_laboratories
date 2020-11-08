package rmunteanu.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class definitionTag extends SimpleTagSupport {

    private String word;
    private String definition;
    private String language;

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getLanguage() {
        return language;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        out.print("<tr><td>" + word + "</td><td>" + definition + "</td><td>"+ language + "</td></tr>");
    }

}
