/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Attributes;
import eproject.easyTickect.object.TypeSeats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class TypeSeatsDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public TypeSeatsDAO() {
        con = new Connect().connectData();
    }
    
    public TypeSeats findTypeSeatsById(int id){
        TypeSeats typeSeat = new TypeSeats();
        try {
            con = new Connect().connectData();
            String query = "SELECT * FROM [TypeSeat] WHERE [Id]= '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String type = rs.getString("Type");
                double price = rs.getDouble("Price");
                typeSeat = new TypeSeats(id, type, price);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return typeSeat;
    }
    
}
