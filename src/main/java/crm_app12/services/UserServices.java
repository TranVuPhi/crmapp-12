package crm_app12.services;

import crm_app12.UserEntity;
import crm_app12.repository.UserRepository;

import java.util.List;

/*
* Quản lý logic
*/

public class UserServices {

    private UserRepository userRepository = new UserRepository();

    public List<UserEntity> getAllUsers() {
        return  userRepository.findAll();
    }

}
