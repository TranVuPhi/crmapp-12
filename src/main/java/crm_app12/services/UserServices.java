package crm_app12.services;

import crm_app12.Entity.RoleEntity;
import crm_app12.UserEntity;
import crm_app12.repository.RoleRepository;
import crm_app12.repository.UserRepository;

import java.util.List;

/*
* Quản lý logic
*/

public class UserServices {

    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();

    public List<UserEntity> getAllUsers() {
        List<UserEntity>  listUserEntities = userRepository.findAll();

        return listUserEntities;
    }

    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    public boolean addUser(UserEntity userEntity) {
        int count = userRepository.insert(userEntity);

        return count > 0;
    }
}
