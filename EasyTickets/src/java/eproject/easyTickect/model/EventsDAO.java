/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.DateTimes;
import eproject.easyTickect.object.Events;
import eproject.easyTickect.object.Places;
import eproject.easyTickect.object.TypeEvents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class EventsDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public EventsDAO() {
        con = new Connect().connectData();
    }

    public void fillPlaceByEventName(String eventName) {
        try {
            String query = "SELECT * FROM [Event] WHERE [Name] = N'" + eventName + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

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
    }

    public Events findEventByPlaceDate(String nameEvent, String namePlace, Date date, Date time) {
        Events event = new Events();
        try {
            String query = "SELECT [Event].* FROM [Event] JOIN [DateTime] ON [DateTime].[Id] = [Event].[IdDateTime] JOIN [Places] ON [Places].[Id] = [Event].[IdPlace] WHERE [Event].[Name] = N'" + nameEvent + "' AND [Places].[Place]=N'" + namePlace + "' AND [DateTime].[Date]='" + date + "' AND [DateTime].[Time]='" + time + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Places p = new PlacesDAO().findPlaceById(rs.getInt("IdPlace"));
                int idDateTime = rs.getInt("IdDateTime");
                TypeEvents tye = new TypeEventsDAO().getTypeEventById(rs.getInt("IdTypeEvent"));
                String image = rs.getString("Image");
                event = new Events(rs.getInt("Id"), tye, p, new DateTimes(idDateTime, date, time), nameEvent, image);
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
        return event;
    }
    
    public Events findEventsById(int id) {
        Events events = new Events();
        try {
            String query = "select * from [Event] where [Id] = '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idEvent = rs.getInt("Id");
                int idTypeEvent = rs.getInt("IdTypeEvent");
                int idPlace = rs.getInt("IdPlace");
                int idDateTime = rs.getInt("IdDateTime");
                String name = rs.getString("Name");
                String image = rs.getString("Image");
                events = new Events(id, new TypeEventsDAO().getTypeEventById(idTypeEvent), new PlacesDAO().findPlaceById(idPlace), new DatesDAO().findDateTimeById(id), name, image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }

}
