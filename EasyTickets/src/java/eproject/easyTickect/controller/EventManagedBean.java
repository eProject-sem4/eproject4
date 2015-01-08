/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.controller;

import eproject.easyTickect.model.DatesDAO;
import eproject.easyTickect.model.PlacesDAO;
import eproject.easyTickect.model.TimesDAO;
import eproject.easyTickect.model.DataAccess;
import eproject.easyTickect.object.DateTimes;
import eproject.easyTickect.object.Events;
import eproject.easyTickect.object.Places;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Trang
 */
@ManagedBean
@ViewScoped
public class EventManagedBean {

    public EventManagedBean() {
    }
    private List<String> lstEvent;
    private List<String> lstPlace;
    private List<Date> lstDate;
    private List<Time> lstTime;
    private Events event = new Events();
    private Places place = new Places();
    private DateTimes dateTime = new DateTimes();
    private String name;

    public List<String> getLstEvent() {
        lstEvent = new DataAccess().findAllEvent();
        return lstEvent;
    }

    public String getName() {
        name = event.getName();
        return name;
    }

    public void setLstEvent(List<String> lstEvent) {
        this.lstEvent = lstEvent;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }

    public List<String> getLstPlace() {
        return lstPlace;
    }

    public void setLstPlace(List<String> lstPlace) {
        this.lstPlace = lstPlace;
    }

    public List<Date> getLstDate() {
        return lstDate;
    }

    public void setLstDate(List<Date> lstDate) {
        this.lstDate = lstDate;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public DateTimes getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTimes dateTime) {
        this.dateTime = dateTime;
    }

    public List<Time> getLstTime() {
        return lstTime;
    }

    public void setLstTime(List<Time> lstTime) {
        this.lstTime = lstTime;
    }
    
    public void showEventDetail(int id) {
        event = new DataAccess().fillEventById(id);
    }

    public void showPlace(String ename) {
        lstPlace = new PlacesDAO().fillPlaceByEventName(ename);
        lstDate = null;
        lstTime = null;
    }

    public void showDateTime(String place) {
        lstDate = new DatesDAO().fillDateByPlace(place);
        lstTime = null;
    }
    
    public void showTime(Date date){
        lstTime = new TimesDAO().fillTimeByDate(date);
    }
    public String chang(){
        return "booking";
    }
}
