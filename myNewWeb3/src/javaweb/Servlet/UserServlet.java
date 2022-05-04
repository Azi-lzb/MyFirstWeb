package javaweb.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import javaweb.util.DBUtil;
import javaweb.bean.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@WebServlet({"/user/login","/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if("/user/login".equals(servletPath)){
            doLogin(request,response);
        }else if("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(false);
        if (session !=null){
            session.removeAttribute("user");
            session.invalidate();
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for (Cookie cookie:cookies) {
                    cookie.setMaxAge(0);
                    cookie.setPath(request.getContextPath());
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect(request.getContextPath());
        }
    }


    private void doLogin(HttpServletRequest request, HttpServletResponse response)
    throws IOException,ServletException{
        boolean success = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            int realPassword =parseInt(password);
            ps.setInt(2,realPassword);
            rs=ps.executeQuery();
            if (rs.next()){
                success=true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        if(success){
            HttpSession session = request.getSession();
            User user = new User(username,password);
            session.setAttribute("user",user);
            String check = request.getParameter("check");
            if("yes".equals(check)){
                Cookie cookie1= new Cookie("username",username);
                Cookie cookie2 = new Cookie("password", password);
                cookie1.setMaxAge(60*60*24*10);
                cookie2.setMaxAge(60*60*24*10);
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }

            response.sendRedirect(request.getContextPath());
        }else {
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }

    }
}
