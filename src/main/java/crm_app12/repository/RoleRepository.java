package crm_app12.repository;

import crm_app12.Entity.RoleEntity;
import crm_app12.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    public List<RoleEntity> findAll() {
        List<RoleEntity> listRoles = new ArrayList<RoleEntity>();

        try {
            String query = "SELECT * FROM roles";
            Connection connection = MysqlConfig.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(resultSet.getInt("id"));
                roleEntity.setName(resultSet.getString("name"));
                roleEntity.setDescription(resultSet.getString("description"));

                listRoles.add(roleEntity);
            }

        }catch (Exception e) {
            System.out.println("findAll" + e.getMessage());
        }
        return listRoles;
    }

//    public int save() {
//
//    }
}
