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
public class EventAttributes {

    private int id;
    private Events idEvent;
    private Attributes idAttribute;
    private String values;

    public EventAttributes() {
    }

    public EventAttributes(int id, Events idEvent, Attributes idAttribute, String values) {
        this.id = id;
        this.idEvent = idEvent;
        this.idAttribute = idAttribute;
        this.values = values;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Events getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Events idEvent) {
        this.idEvent = idEvent;
    }

    public Attributes getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Attributes idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

}
