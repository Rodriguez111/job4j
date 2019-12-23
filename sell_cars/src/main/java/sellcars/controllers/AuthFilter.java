package sellcars.controllers;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (!request.getRequestURI().contains("/login")) {
            if (session.getAttribute("userLogin") == null
                    && !request.getRequestURI().contains("/json")
                    && !request.getRequestURI().contains("/picture")
                    && !request.getRequestURI().contains("/forward")
                    && !request.getRequestURI().contains("/register")) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        }
        filterChain.doFilter(request, response);

    }
}
