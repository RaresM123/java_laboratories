package rmunteanu.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"}, initParams = {@WebInitParam(name="defaultLanguage", value="English")})

public class ValidateFilter implements Filter {

    private String defaultLanguage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultLanguage = filterConfig.getInitParameter("defaultLanguage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        ServletContext context = servletRequest.getServletContext();

        ServletRequestWrapper requestWrapper;

        String word = servletRequest.getParameter("word");
        String definition = servletRequest.getParameter("definition");
        String language = servletRequest.getParameter("language");

        if (httpRequest.getMethod().equals("POST")
                && (word == null || word.equals("") || definition == null || definition.equals("")))
        {
            context.log("Will redirect request");
            context.log("Default Language is " + this.defaultLanguage);
            httpResponse.sendRedirect("controller");
            return;
        }

        if (httpRequest.getMethod().equals("POST") && (language == null || language.equals("") || language.equals("none")))
        {
            requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) servletRequest)
            {
                @Override
                public String getParameter(String language) {
                    if (language.equals("language"))
                    {
                        return defaultLanguage;
                    }
                    else
                        return super.getParameter(language); //To change body of generated methods, choose Tools | Templates.
                }

            };
        }
        else
        {
            requestWrapper = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }

        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
