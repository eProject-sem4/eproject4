/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eproject.easyTickect.model;

import eproject.easyTickect.object.Places;
import eproject.easyTickect.object.Seats;
import eproject.easyTickect.object.TypeSeats;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ViTAnH
 */
public class SeatsDAO {
    Connection con = new Connect().connectData();
    PreparedStatement ps;
    ResultSet rs;

    public SeatsDAO() {
        con = new Connect().connectData();
    }
    
    public List<String> findSeatsBooked(String eventName, String namePlace , Date date , Time time) {
        List<String> lstSeats = new LinkedList<String>();
        try {
            String query = "select [Seats].[Seat] from [Seats] join [Booking] on [Seats].[Id] = [Booking].[IdSeats] join [Event] on [Booking].[IdEvent] = [Event].[Id] join [Places] on [Event].[IdPlace] = [Places].[Id] join [DateTime] on [Event].[IdDateTime] = [DateTime].[Id] where [Event].[Name] = '"+eventName+"' and [Places].[Place] = '"+namePlace+"' and [DateTime].[Date] = '"+date+"' and [DateTime].[Time] = '"+time+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nameSeat = rs.getString("Seat");
                lstSeats.add(nameSeat);
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
        return lstSeats;
    }
    
    public List<String> findAllSeats(String eventName, String namePlace , Date date , Time time) {
        List<String> lstAllSeats = new LinkedList<String>();
        try {
            String query = "select [Seats].[Seat] from [Seats] join [Places] on [Seats].[IdPlace] = [Places].[Id] join [Event] on [Event].[IdPlace] = [Places].[Id] join [DateTime] on [Event].[IdDateTime] = [DateTime].[Id] where [Event].[Name] = '"+eventName+"' and [Places].[Place] = '"+namePlace+"' and [DateTime].[Date] = '"+date+"' and [DateTime].[Time] = '"+time+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nameSeat = rs.getString("Seat");
                lstAllSeats.add(nameSeat);
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
        return lstAllSeats;
    }
    
    public List<Seats> findAllSeatsObject(String eventName, String namePlace , Date date , Time time) {
        List<Seats> lstAllSeats = new LinkedList<>();
        try {
            String query = "select * from [Seats] join [Places] on [Seats].[IdPlace] = [Places].[Id] join [Event] on [Event].[IdPlace] = [Places].[Id] join [DateTime] on [Event].[IdDateTime] = [DateTime].[Id] where [Event].[Name] = '"+eventName+"' and [Places].[Place] = '"+namePlace+"' and [DateTime].[Date] = '"+date+"' and [DateTime].[Time] = '"+time+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int idSeat = rs.getInt("Id");
                int idPlace = rs.getInt("IdPlace");
                int idTypeSeat = rs.getInt("IdTypeSeat");
                String nameSeat = rs.getString("Seat");
                Places place = new PlacesDAO().findPlaceById(idPlace);
                TypeSeats typeSeats = new TypeSeatsDAO().findTypeSeatsById(idTypeSeat);
                lstAllSeats.add(new Seats(idSeat, place, typeSeats, nameSeat));
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
        return lstAllSeats;
    }
    
    public Seats findSeatById(int idS) {
        Seats seats = new Seats();
        try {
            String query = "select * from [Seats] where [Id] = '" + idS + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int idPlace = rs.getInt("IdPlace");
                Places place = new PlacesDAO().findPlaceById(idPlace);
                int idTypeSeat = rs.getInt("IdTypeSeat");
                TypeSeats typeSeats = new TypeSeatsDAO().findTypeSeatsById(idTypeSeat);
                String seat = rs.getString("Seat");
                seats = new Seats(id, place, typeSeats, seat);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SeatsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seats;
    }
    
}
