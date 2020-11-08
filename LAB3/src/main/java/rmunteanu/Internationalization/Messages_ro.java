package rmunteanu.Internationalization;

import java.util.ListResourceBundle;

public class Messages_ro extends ListResourceBundle {
    static final Object[][] contents = {
            {"title", "Dictionar de cuvinte"},
            {"word", "cuvant"},
            {"def", "definitie"},
            {"lang", "limba"}
    };
    public Object[][] getContents() {
        return contents;
    }
}
