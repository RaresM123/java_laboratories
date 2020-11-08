package rmunteanu.wrappers;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomWrapper extends HttpServletResponseWrapper {

    private CharArrayWriter output;

    public CustomWrapper(HttpServletResponse response) {
        super(response);
        output = new CharArrayWriter();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return output.toString();
    }
}
