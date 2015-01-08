/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.test;

import eproject.easyTickect.model.EventsDAO;
import eproject.easyTickect.object.Events;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Trang
 */
public class NewClass {

    public static void main(String[] args) {
//        List<Seats> lstSeat = new SeatsDAO().findAllSeatsObject("CHANG TRAI NAM AY", "Rap thang 8", Date.valueOf("2015-05-01"), Time.valueOf("12:00:00"));
//        for (Seats lstSeat1 : lstSeat) {
////            System.out.println(lstSeat1.getSeat());
//        }
//        
//         List<Seats> lstAllSeatObject = new SeatsDAO().findAllSeatsObject("CHANG TRAI NAM AY", "Rap thang 8", Date.valueOf("2015-05-01"), Time.valueOf("12:00:00"));
//         List<String> lstAllSeat = new SeatsDAO().findAllSeats("CHANG TRAI NAM AY", "Rap thang 8", Date.valueOf("2015-05-01"), Time.valueOf("12:00:00"));
//         List<String> lstSeatBooked = new SeatsDAO().findSeatsBooked("CHANG TRAI NAM AY", "Rap thang 8", Date.valueOf("2015-05-01"), Time.valueOf("12:00:00"));
//        
//        List<Seats> lstSeatConlai = lstAllSeatObject;
//        for (int i = 0; i < lstAllSeatObject.size(); i++) {
//            for (String s : lstSeatBooked) {
//                if (lstAllSeatObject.get(i).getSeat().equals(s)) {
//                    lstSeatConlai.remove(i);
//                }
//            }
//        }
//        
//        for (Seats sd : lstSeatConlai) {
//            System.out.println(sd.getSeat());
//        }
//        
        
        Events e = new EventsDAO().findEventByPlaceDate("CHANG TRAI NAM AY", "Rap thang 8", Date.valueOf("2015-05-01"), Time.valueOf("12:00:00"));
        System.out.println(e.getId());
        System.out.println(e.getIdDateTime().getDate());
        System.out.println(e.getIdDateTime().getTime());
        System.out.println(e.getIdPlace().getPlace());
        System.out.println(e.getIdTypeEvent().getName());
        System.out.println(e.getName());
    }
}
