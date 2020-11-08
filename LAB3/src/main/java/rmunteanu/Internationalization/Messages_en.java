package rmunteanu.Internationalization;

import java.util.ListResourceBundle;

public class Messages_en extends ListResourceBundle {
    static final Object[][] contents = {
            {"title", "Words Dictionary"},
            {"word", "word"},
            {"def", "definition"},
            {"lang", "language"}
    };

    public Object[][] getContents() {
        return contents;
    }
}