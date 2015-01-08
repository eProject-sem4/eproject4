/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.TypeCards;
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
public class TypeCardDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public TypeCardDAO() {
        con = new Connect().connectData();
    }
    
    public TypeCards findTypeCardById(int idTC) {
        TypeCards typeCards = new TypeCards();
        try {
            String query = "select * from [TypeCard] where [Id] = '" + idTC + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String card = rs.getString("Card");
                typeCards = new TypeCards(id, card);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeCardDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typeCards;
    }
}
