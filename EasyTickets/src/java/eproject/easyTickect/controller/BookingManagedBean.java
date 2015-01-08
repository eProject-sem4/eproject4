/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.controller;

import eproject.easyTickect.model.BookingsDAO;
import eproject.easyTickect.model.EventsDAO;
import eproject.easyTickect.model.SeatsDAO;
import eproject.easyTickect.object.Bookings;
import eproject.easyTickect.object.Events;
import eproject.easyTickect.object.Seats;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ViTAnH
 */
@ManagedBean
@SessionScoped

public class BookingManagedBean {

    private final int IdUser = 1;
    private String eventName;
    private String eventPlace;
    private Date eventDate;
    private Time eventTime;
    private List<String> lstAllSeat = new LinkedList<>();
    private List<String> lstSeatBooked = new LinkedList<>();
    private List<Seats> lstSeatConlai = new LinkedList<>();
    private List<Integer> countSeat = new LinkedList<>();
    private List<Seats> lstAllSeatObject = new LinkedList<>();
    List<Seats> lstBooked = new LinkedList<>();
    private Map<Long, Boolean> checked = new HashMap<>();
    
    private List<Bookings> lstBooking = new LinkedList<>();
    
    public BookingManagedBean() {

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Time getEventTime() {
        return eventTime;
    }

    public void setEventTime(Time eventTime) {
        this.eventTime = eventTime;
    }

    public List<String> getLstAllSeat() {
        return lstAllSeat;
    }

    public void setLstAllSeat(List<String> lstAllSeat) {
        this.lstAllSeat = lstAllSeat;
    }

    public List<String> getLstSeatBooked() {
        return lstSeatBooked;
    }

    public void setLstSeatBooked(List<String> lstSeatBooked) {
        this.lstSeatBooked = lstSeatBooked;
    }

    public List<Seats> getLstSeatConlai() {
        findSeatNoBooked();
        return lstSeatConlai;
    }

    public void setLstSeatConlai(List<Seats> lstSeatConlai) {
        this.lstSeatConlai = lstSeatConlai;
    }

    public List<Integer> getCountSeat() {
        findSeatNoBooked();
        return countSeat;
    }

    public void setCountSeat(List<Integer> countSeat) {
        this.countSeat = countSeat;
    }

    public List<Seats> getLstAllSeatObject() {
        return lstAllSeatObject;
    }

    public void setLstAllSeatObject(List<Seats> lstAllSeatObject) {
        this.lstAllSeatObject = lstAllSeatObject;
    }

    public List<Seats> getLstBooked() {
        return lstBooked;
    }

    public void setLstBooked(List<Seats> lstBooked) {
        this.lstBooked = lstBooked;
    }

    public Map<Long, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Long, Boolean> checked) {
        this.checked = checked;
    }

    public List<Bookings> getLstBooking() {
        return lstBooking;
    }

    public void setLstBooking(List<Bookings> lstBooking) {
        this.lstBooking = lstBooking;
    }

    public void findSeatNoBooked() {

        lstAllSeatObject = new SeatsDAO().findAllSeatsObject(eventName, eventPlace, eventDate, eventTime);
        lstAllSeat = new SeatsDAO().findAllSeats(eventName, eventPlace, eventDate, eventTime);
        lstSeatBooked = new SeatsDAO().findSeatsBooked(eventName, eventPlace, eventDate, eventTime);

        lstSeatConlai = lstAllSeatObject;
        for (int i = 0; i < lstAllSeatObject.size(); i++) {
            for (String s : lstSeatBooked) {
                if (lstAllSeatObject.get(i).getSeat().equals(s)) {
                    lstSeatConlai.remove(i);
                }
            }
        }
        countSeat = new LinkedList<>();
        for (int i = 0; i < lstSeatConlai.size(); i++) {
            countSeat.add(i + 1);
        }
    }

    public String bookContinue() {
        Events event = new EventsDAO().findEventByPlaceDate(eventName, eventPlace, eventDate, eventTime);
        for (Seats seat : lstSeatConlai) {
            if (checked.get(seat.getId())) {
                double price = seat.getIdTypeSeat().getPrice();
                int idDiscount = 1;
                int idtypeCard = 1;
                new BookingsDAO().bookingTicket(IdUser, event.getId(), idDiscount, idtypeCard, seat.getId(), price);
                lstBooking.add(new BookingsDAO().findTicketBookedByEventAndSeat(IdUser, IdUser));
            }
        }
        return "index";
    }

}
