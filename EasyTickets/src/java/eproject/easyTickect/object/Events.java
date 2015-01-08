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
public class Events {

    private int id;
    private TypeEvents idTypeEvent;
    private Places idPlace;
    private DateTimes idDateTime;
    private String name;
    private String image;

    public Events() {
    }

    public Events(int id, TypeEvents idTypeEvent, Places idPlace, DateTimes idDateTime, String name, String image) {
        this.id = id;
        this.idTypeEvent = idTypeEvent;
        this.idPlace = idPlace;
        this.idDateTime = idDateTime;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TypeEvents getIdTypeEvent() {
        return idTypeEvent;
    }

    public void setIdTypeEvent(TypeEvents idTypeEvent) {
        this.idTypeEvent = idTypeEvent;
    }

    public Places getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(Places idPlace) {
        this.idPlace = idPlace;
    }

    public DateTimes getIdDateTime() {
        return idDateTime;
    }

    public void setIdDateTime(DateTimes idDateTime) {
        this.idDateTime = idDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
