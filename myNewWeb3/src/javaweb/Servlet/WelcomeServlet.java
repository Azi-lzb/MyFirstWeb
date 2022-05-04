package javaweb.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import javaweb.bean.User;
import javaweb.util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException,IOException{
        String username = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie:cookies) {
                String name = cookie.getName();
                if("username".equals(name)){
                    username=cookie.getValue();
                }else if("password".equals(name)){
                    password=cookie.getValue();
                }
            }
        }

        if (username!=null && password!=null){
            Connection conn =null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean success=false;
            try{
                conn=DBUtil.getConnection();
                String sql = "select * from user where username =? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,username);
                int realPassword = parseInt(password);
                ps.setInt(2,realPassword);
                rs= ps.executeQuery();
                if (rs.next()){
                    success=true;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtil.close(conn,ps,rs);
            }
            if (success){
                HttpSession session = request.getSession();
                User user = new User(username, password);
                session.setAttribute("user",user);
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                response.sendRedirect(request.getContextPath()+"error.jsp");
            }

        }else{
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }
}
