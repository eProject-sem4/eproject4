/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.object;

/**
 *
 * @author Trang
 */
public class Seats {

    private int id;
    private Places idPlace;
    private TypeSeats idTypeSeat;
    private String Seat;

    public Seats() {
    }

    public Seats(int id, Places idPlace, TypeSeats idTypeSeat, String Seat) {
        this.id = id;
        this.idPlace = idPlace;
        this.idTypeSeat = idTypeSeat;
        this.Seat = Seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Places getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Places idPlace) {
        this.idPlace = idPlace;
    }

    public TypeSeats getIdTypeSeat() {
        return idTypeSeat;
    }

    public void setIdTypeSeat(TypeSeats idTypeSeat) {
        this.idTypeSeat = idTypeSeat;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String Seat) {
        this.Seat = Seat;
    }

}
