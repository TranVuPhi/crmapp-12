package crm_app12.services;

import crm_app12.UserEntity;
import crm_app12.repository.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LoginServices {
    private UserRepository userRepository = new UserRepository();

    public String checkLogin(String email, String password, String remember, HttpServletResponse resp) {
        String message = "Đăng nhập thất bại!";
        List<UserEntity> listUserEntities = userRepository.findByEmailAndPassword(email, password);
        if (listUserEntities.size() > 0) {
            // Kiểm tra checkbox remember
            if (remember != null) {
                // Tạo cookie
                Cookie cEmail = new Cookie("email", email);
                Cookie cPassword = new Cookie("password", password);

                resp.addCookie(cEmail);
                resp.addCookie(cPassword);
            }
            message = "Đăng nhập thành công!";
        }
        return message;
    }
}
