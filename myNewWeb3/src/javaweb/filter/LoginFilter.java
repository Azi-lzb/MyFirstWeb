package javaweb.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import javaweb.Servlet.UserServlet;
import javaweb.bean.User;
import javaweb.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Filter;

public class LoginFilter implements jakarta.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        ServletContext servletContext = request.getServletContext();
        HttpSession session = request.getSession(false);

        if( (session!=null && session.getAttribute("user")!=null)
                || "/user/exit".equals(servletContext)
                ||"/user/login".equals(servletContext)){
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
