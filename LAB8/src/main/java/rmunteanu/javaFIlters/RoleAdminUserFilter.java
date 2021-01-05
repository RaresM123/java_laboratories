package rmunteanu.javaFIlters;

import rmunteanu.javaEntities.UserEntity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleAdminUserFilter implements Filter {
    @Override
    public void init(FilterConfig configFilter) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        UserEntity thisUser = (UserEntity) request.getSession().getAttribute("user");
        if(thisUser==null || !thisUser.getType().equals("admin"))
        {
            HttpServletResponse r = (HttpServletResponse) servletResponse;
            r.sendRedirect(request.getContextPath() + "/login.xhtml");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
