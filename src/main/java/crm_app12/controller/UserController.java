package crm_app12.controller;

import crm_app12.UserEntity;
import crm_app12.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {
    private UserServices userServices = new UserServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserEntity> listUserEntities = userServices.getAllUsers();

        req.setAttribute("listUser", listUserEntities);

        req.getRequestDispatcher("user-table.jsp").forward(req, resp);

    }

}
