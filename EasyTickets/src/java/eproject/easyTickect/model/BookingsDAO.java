/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Attributes;
import eproject.easyTickect.object.Bookings;
import eproject.easyTickect.object.Discounts;
import eproject.easyTickect.object.Events;
import eproject.easyTickect.object.Seats;
import eproject.easyTickect.object.TypeCards;
import eproject.easyTickect.object.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class BookingsDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public BookingsDAO() {
        con = new Connect().connectData();
    }

    public void bookingTicket(int idUse, int idEvent, int idDiscount, int idTypeCard, int idSeat, double price) {
        try {
            con = new Connect().connectData();
            String query = "insert into [Booking] values ('" + idUse + "','" + idEvent + "','" + idDiscount + "','" + idTypeCard + "','" + idSeat + "','" + price + "')";
            ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Bookings findTicketBookedByEventAndSeat(int idEvent, int idSeat) {
        Bookings bookings = new Bookings();
        try {
            con = new Connect().connectData();
            String query = "select * from [Booking] where [Booking].[IdEvent] = '" + idEvent + "' and [Booking].[IdSeats] = '" + idSeat + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                int idUser = rs.getInt("IdUser");
                Users user = new UsersDAO().findUsersById(idUser);
                int event = rs.getInt("IdEvent");
                Events events = new EventsDAO().findEventsById(event);
                int idDiscount = rs.getInt("IdDiscount");
                Discounts discounts = new DiscountDAO().findDiscountById(idDiscount);
                int idTypeCard = rs.getInt("IdTypeCard");
                TypeCards typeCards = new TypeCardDAO().findTypeCardById(idTypeCard);
                int seat = rs.getInt("IdSeats");
                Seats seats = new SeatsDAO().findSeatById(seat);
                double priceBuy = rs.getDouble("PriceBuy");
                bookings = new Bookings(id, user, events, discounts, typeCards, seats, priceBuy);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookings;
    }
}
