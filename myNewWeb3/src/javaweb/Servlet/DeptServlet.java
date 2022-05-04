package javaweb.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javaweb.bean.Dept;
import javaweb.util.DBUtil;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


@WebServlet({"/dept/list","/dept/detail","/dept/modify","/dept/delete","/dept/update","/dept/new"})
public class DeptServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException ,ServletException{
        String servletPath = request.getServletPath();

        if ("/dept/list".equals(servletPath)) {
             doList(request,response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDeletee(request, response);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(request, response);
        }else if ("/dept/update".equals(servletPath)){
            doUpdate(request,response);
        }else if("/dept/new".equals(servletPath)){
            doNew(request,response);
        }


    }

    private void doNew(HttpServletRequest request, HttpServletResponse response)
    throws  IOException{
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");
        String num = request.getParameter("num");
        Connection conn =null;
        PreparedStatement ps = null;
        try{
            conn=DBUtil.getConnection();
            String sql = "insert into deptdetail(no,name,loc,num)values(?,?,?,?)";
            ps=conn.prepareStatement(sql);
            int no1 = parseInt(no);
            int num1 = parseInt(num);
            ps.setInt(1,no1);
            ps.setString(2,name);
            ps.setString(3,loc);
            ps.setInt(4,num1);
            int i = ps.executeUpdate();
            if (i==1){
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String loc = request.getParameter("loc");
        String num = request.getParameter("num");

        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn=DBUtil.getConnection();
            String sql = "update deptdetail set name=?,loc=?,num=? where no=?";
            ps = conn.prepareStatement(sql);
            int no1 = parseInt(no);
            int num1 = parseInt(num);
            ps.setString(1,name);
            ps.setString(2,loc);
            ps.setInt(3,num1);
            ps.setInt(4,no1);
            int i = ps.executeUpdate();
            if (i==1){
                response.sendRedirect("/myweb/dept/list");
            }else {
                response.sendRedirect("/myweb/error.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException{
        String no = request.getParameter("no");
        Connection conn = null ;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dept dept = new Dept();
        try {
            conn=DBUtil.getConnection();
            String sql = "select no,name,loc,num from deptdetail where no = ?";
            ps = conn.prepareStatement(sql);
            int no1 = parseInt(no);
            ps.setInt(1,no1);
            rs=ps.executeQuery();
            if (rs.next()){
                dept.setNo(rs.getInt("no"));
                dept.setName(rs.getString("name"));
                dept.setLoc(rs.getString("loc"));
                dept.setNum(rs.getInt("num"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("dept",dept);
        request.getRequestDispatcher("/modify.jsp").forward(request,response);
    }

    private void doDeletee(HttpServletRequest request, HttpServletResponse response)
    throws IOException{
        String no = request.getParameter("no");
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn=DBUtil.getConnection();
            String sql = "delete from deptdetail where no = ?";
            ps = conn.prepareStatement(sql);
            int no1 = parseInt(no);
            ps.setInt(1,no1);
            int i = ps.executeUpdate();
            if (i==1){
                response.sendRedirect(request.getContextPath()+"/dept/list");
            }else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
    throws ServletException,IOException {
        String servletPath = request.getServletPath();
        Connection conn = null ;
        PreparedStatement ps = null;
        ResultSet rs =null;
        List<Dept> depts = new ArrayList<>();
       try {
           conn = DBUtil.getConnection();
           String sql = "select no,name,loc,num from deptdetail";
           ps = conn.prepareStatement(sql);
           rs = ps.executeQuery();
           while (rs.next()){
               int no = rs.getInt("no");
               String name = rs.getString("name");
               String loc = rs.getString("loc");
               int num = rs.getInt("num");
               Dept dept = new Dept();
               dept.setNo(no);
               dept.setLoc(loc);
               dept.setName(name);
               dept.setNum(num);
               depts.add(dept);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }finally {
           DBUtil.close(conn,ps,rs);
       }
       request.setAttribute("deptList",depts);
       request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException {
        String no = request.getParameter("no");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Dept dept = new Dept();
        try{
            conn=DBUtil.getConnection();
            String sql = "select no,name,loc,num from deptdetail where no = ?";
            ps=conn.prepareStatement(sql);
            int no1 = parseInt(no);
            ps.setInt(1,no1);
            rs=ps.executeQuery();
            if (rs.next()){
                int no2 = rs.getInt("no");
                String name = rs.getString("name");
                String loc = rs.getString("loc");
                int num = rs.getInt("num");
                dept.setNo(no2);
                dept.setName(name);
                dept.setLoc(loc);
                dept.setNum(num);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("dept",dept);
        request.getRequestDispatcher("/detail.jsp").forward(request,response);

    }
}