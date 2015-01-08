/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.controller;

import eproject.easyTickect.model.DataAccess;
import eproject.easyTickect.object.Events;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kien
 */
@ManagedBean
@SessionScoped
public class ShowPlaceManagedBean {
    
    private List<String> lstPlace = new LinkedList<>();
    private String eventName;
    private Events event;
    DataAccess da = new DataAccess();

    public List<String> getLstPlace() {
//        lstPlace = da.findPlaceByEventName(eventName);
        return lstPlace;
    }

    public void setLstPlace(List<String> lstPlace) {
        this.lstPlace = lstPlace;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    public List<String> findPlaceByEventName() {
//        lstPlace = new DataAccess().findPlaceByEventName(eventName);
        return lstPlace;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
    
}
