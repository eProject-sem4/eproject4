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
public class Bookings {

    private int id;
    private Users idUser;
    private Events idEvent;
    private Discounts idDiscount;
    private TypeCards idTypeCard;
    private Seats idSeat;
    private double priceBuy;

    public Bookings() {
    }

    public Bookings(int id, Users idUser, Events idEvent, Discounts idDiscount, TypeCards idTypeCard, Seats idSeat, double priceBuy) {
        this.id = id;
        this.idUser = idUser;
        this.idEvent = idEvent;
        this.idDiscount = idDiscount;
        this.idTypeCard = idTypeCard;
        this.idSeat = idSeat;
        this.priceBuy = priceBuy;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public Events getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Events idEvent) {
        this.idEvent = idEvent;
    }

    public Discounts getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(Discounts idDiscount) {
        this.idDiscount = idDiscount;
    }

    public TypeCards getIdTypeCard() {
        return idTypeCard;
    }

    public void setIdTypeCard(TypeCards idTypeCard) {
        this.idTypeCard = idTypeCard;
    }

    public Seats getIdSeat() {
        return idSeat;
    }

    public void setIdSeat(Seats idSeat) {
        this.idSeat = idSeat;
    }

    public double getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

}
