/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kien
 */
public class UsersDAO {
    private final Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public UsersDAO() {
        this.con = new Connect().connectData();
    }
    
    public Users findUsersById(int id) {
        Users user = new Users();
        try {
            String query = "select * from [Users] where [Users].[Id] = '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fullName = rs.getString("Fullname");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String telephone = rs.getString("Telephone");
                String position = rs.getString("Position");
                user = new Users(id, fullName, username, password, email, address, telephone, position);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsersDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }
}
