/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eproject.easyTickect.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ViTAnH
 */
@ManagedBean
@RequestScoped
public class PlaceManagedBean {
    
    private String nameEvent;
    private String address;
    private int idDate;
   
    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }
    
    
    
    /**
     * Creates a new instance of PlaceManagedBean
     */
    public PlaceManagedBean() {
        
    }
}
