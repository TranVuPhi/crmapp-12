package crm_app12.controller;

import crm_app12.Entity.RoleEntity;
import crm_app12.UserEntity;
import crm_app12.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user-add"})
public class UserController extends HttpServlet {
    private UserServices userServices = new UserServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletPath();

        if (path.equals("/user")) {
            List<UserEntity> listUserEntities = userServices.getAllUsers();

            req.setAttribute("listUser", listUserEntities);

            req.getRequestDispatcher("user-table.jsp").forward(req, resp);
        } else if (path.equals("/user-add")) {
            List<RoleEntity> listRoleEntities = userServices.getAllRoles();

            req.setAttribute("listRole", listRoleEntities);

            req.getRequestDispatcher("user-add.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/user")) {

        } else if (path.equals("/user-add")) {
            String fullName = req.getParameter("fullName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String phone = req.getParameter("phone");
            String roleId = req.getParameter("role");

            String[] arr = fullName.trim().split("\\s+");

            String lastName = arr[arr.length - 1];

            StringBuilder firstName = new StringBuilder();

            for (int i = 0; i < arr.length - 1; i++) {
                firstName.append(arr[i]).append(" ");
            }

            UserEntity userEntity = new UserEntity();

            userEntity.setFullName(fullName);
            userEntity.setFirstName(firstName.toString().trim());
            userEntity.setLastName(lastName);
            userEntity.setEmail(email);
            userEntity.setPassword(password);
            userEntity.setPhone(phone);
            userEntity.setRoleId(Integer.parseInt(roleId));

            boolean success = userServices.addUser(userEntity);

            if (success) {
                resp.sendRedirect(req.getContextPath() + "/user");
            } else {
                req.setAttribute("message", "Add user failed");
                req.getRequestDispatcher("user-add.jsp")
                        .forward(req, resp);
            }
        }
    }
}
