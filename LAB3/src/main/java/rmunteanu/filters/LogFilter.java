package rmunteanu.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/jsp/input.jsp", "/jsp/result.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.INCLUDE})
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        ServletContext context = servletRequest.getServletContext();
        context.log(String.format("Method: %s --- IP: %s --- User-Agent: %s --- Language: %s --- Parameters: %s",
                request.getMethod(), request.getRemoteAddr(), request.getHeader("User-Agent"),
                request.getHeader("Accept-Language"), request.getParameterMap().toString()));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
