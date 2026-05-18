package crm_app12.repository;

import crm_app12.MysqlConfig;
import crm_app12.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/*
* Quản lý tất cả câu truy vấn liên quan đến bảng users
* */
public class UserRepository {

    /*
    * select -> find
    * where -> By
    * username = '' -> userName
    * Ví dụ: select *  from users wher username = ' '
    * -> findByUsername();
    */

    public List<UserEntity> findByEmailAndPassword(String email, String password) {
        List<UserEntity> listUserEntities = new ArrayList<UserEntity>();

        try {
            //Bước 1: Chuẩn bị câu truy vấn
            String query = "SELECT * FROM users u WHERE u.email = ? AND u.pass= ?";
            //Bước 2: Mở kết nối CSDL
            Connection connection = MysqlConfig.getConnection();
            //Bước 3: Truyền câu truy vấn vào kết nối mới vừa mở và truyền tham số (nếu có)
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            //Bước 4: Thực thi câu truy vấn
            //excuteQuery: khi câu truy vấn là câu SELECT
            //excuteUpdate: không phải là câu SELECT
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setEmail(resultSet.getString("email"));

                listUserEntities.add(userEntity);
            }
        }catch (Exception e){
            System.out.println("Error : findByEmailAndPassword " + e.getMessage());
        }

        return listUserEntities;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> listUserEntities = new ArrayList<UserEntity>();
        String query = "SELECT u.id, u.first_name, u.last_name, u.email, r.name \n" +
                "FROM users u \n" +
                "JOIN roles r ON u.role_id = r.id";

        try {
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(resultSet.getInt("id"));
                userEntity.setFirstName(resultSet.getString("first_name"));
                userEntity.setLastName(resultSet.getString("last_name"));
                userEntity.setEmail(resultSet.getString("email"));
                userEntity.setRoleName(resultSet.getString("name"));

                listUserEntities.add(userEntity);
            }
        }catch (Exception e) {
            System.out.println("Lỗi findAll " + e.getMessage());
        }

        return listUserEntities;
    }
}
