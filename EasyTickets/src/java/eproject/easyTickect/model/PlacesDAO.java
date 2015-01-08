/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Places;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class PlacesDAO {

    Connection con = new Connect().connectData();
    PreparedStatement ps;
    ResultSet rs;

    public PlacesDAO() {
    }

    public List<String> fillPlaceByEventName(String eventName) {
        List<String> lstPlace = new LinkedList<>();
        try {
            con = new Connect().connectData();
            String query = "SELECT distinct [Places].[Place] FROM [Places] JOIN [Event] ON [Event].IdPlace = [Places].Id WHERE [Name] = N'" + eventName + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String place = rs.getString("Place");
                lstPlace.add(place);
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
        return lstPlace;
    }
    
    public Places findPlaceById(int idPlace) {
        Places places = new Places();
        try {
            con = new Connect().connectData();
            String query = "select * from [Places] where [Places].[Id] = '" + idPlace + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String place = rs.getString("Place");
                String address = rs.getString("Address");
                String room = rs.getString("Room");
                places = new Places(id, place, address, room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlacesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlacesDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return places;
    }

}
