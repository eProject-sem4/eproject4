/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Attributes;
import eproject.easyTickect.object.EventAttributes;
import eproject.easyTickect.object.Events;
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
public class EventAttributesDAO {

    Connection con = new Connect().connectData();
    PreparedStatement ps = null;
    ResultSet rs = null;

    public EventAttributesDAO() {
    }

    // (EventAttributes)---- OK ---- fill All EventAttribute By Id Event
    public List<EventAttributes> fillAllEventAttributeByIdEvent(int id) {
        List<EventAttributes> lstEA = new LinkedList<>();
        try {
            con = new Connect().connectData();
            String query = "SELECT * FROM [EventAttributes] WHERE [IdEvent]= '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Events e = new DataAccess().fillEventById(rs.getInt("IdEvent"));
                Attributes a = new DataAccess().fillAttributeById(rs.getInt("IdAttributes"));
                String v = rs.getString("Value");
                lstEA.add(new EventAttributes(id, e, a, v));
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
        return lstEA;
    }
}
