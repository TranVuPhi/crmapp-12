package crm_app12.services;

import crm_app12.UserEntity;
import crm_app12.repository.UserRepository;

import java.util.List;

public class LoginServices {
    private UserRepository userRepository = new UserRepository();

    public String checkLogin(String email, String password) {
        String message = "Đăng nhập thất bại!";
        List<UserEntity> listUserEntities = userRepository.findByEmailAndPassword(email, password);
        if (listUserEntities.size() > 0) {
            message = "Đăng nhập thành công!";
        }
        return message;
    }
}
