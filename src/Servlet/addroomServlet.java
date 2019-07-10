package Servlet;

import java.io.IOException;
import DAO.*;

import javax.jws.WebService;

@WebService(name="addroomServlet")
public class addroomServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String id = request.getParameter("room_id");

        String price=request.getParameter("room_price");
        String type=request.getParameter("room_type");
        String location=request.getParameter("room_location");



        String flag=RoomDao.addroom(id,price,type,location);
             System.out.println(location);

        if(flag=="0") {

            request.setAttribute("add","0");
        }else if(flag=="1"){

            request.setAttribute("add", "1");
        }


        request.getRequestDispatcher("admin.jsp").forward(request, response);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
