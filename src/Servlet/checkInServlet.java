package Servlet;

import BEAN.Checkin;
import DAO.CheckinDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "checkInServlet")
public class checkInServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Checkin checkin = new Checkin();
        CheckinDAO checkinDao = new CheckinDAO();

        String client_no = request.getParameter("clientNo");
        String client_id = request.getParameter("client_idno");
        String phone_no = request.getParameter("phone_no");
        String room_no = request.getParameter("roomNo");
        int duration = Integer.parseInt(request.getParameter("duration"));
        String isReserved = request.getParameter("isReserved");

        ResultSet rs1 =checkinDao.SearchIsRoom(room_no);
        try {
            if(!rs1.next())
            {
                //如果房间不存在错误信息
                request.getRequestDispatcher("login.jsp").forward(request, response);

            }else
            {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }




        checkin.setClient_no(client_no);
        checkin.setRoom_no(room_no);
        checkinDao.addCheckin(checkin);
        checkinDao.updateLeaveDate(checkin,duration);
        checkinDao.updateCost(checkin);
        checkinDao.updateRoomState_arrive(room_no); //更新房间状态

        request.getRequestDispatcher("employee.jsp?idr=5").forward(request, response);
    }
}
