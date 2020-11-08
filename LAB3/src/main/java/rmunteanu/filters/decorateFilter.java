package rmunteanu.filters;

import rmunteanu.wrappers.CustomWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.INCLUDE})

public class decorateFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        ServletContext context = servletRequest.getServletContext();

        context.log("Before decorator");
        CustomWrapper responseWrapper = new CustomWrapper(httpResponse);

        filterChain.doFilter(servletRequest, responseWrapper);

        String output = responseWrapper.toString();
        output = output.replace("<body>", "<body><h1>Wrapper Filter</h1>");
        output = output.replace("</body>", "<h3>Have a nice day!</h3></body>");

        servletResponse.getWriter().write(output);
        servletResponse.getWriter().close();
        context.log("After decorator");
    }

    @Override
    public void destroy() {

    }
}
