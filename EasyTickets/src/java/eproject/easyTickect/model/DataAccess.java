/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Attributes;
import eproject.easyTickect.object.DateTimes;
import eproject.easyTickect.object.EventAttributes;
import eproject.easyTickect.object.Events;
import eproject.easyTickect.object.Places;
import eproject.easyTickect.object.TypeEvents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class DataAccess {

    public DataAccess() {
    }

    // (Attributes)---- Ok ---- Fill Attribute by Id
    public Attributes fillAttributeById(int id) {
        Attributes attribute = new Attributes();
        Connection conA;
        PreparedStatement psA = null;
        ResultSet rsA = null;
        try {
            conA = new Connect().connectData();
            String query = "SELECT * FROM [Attributes] WHERE [Id]= '" + id + "'";
            psA = conA.prepareStatement(query);
            rsA = psA.executeQuery();
            while (rsA.next()) {
                String name = rsA.getString("Name");
                attribute = new Attributes(id, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rsA.close();
                psA.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attribute;
    }
    
    // (EventAttributes)---- OK ---- fill All EventAttribute By Id Event
    public List<EventAttributes> fillAllEventAttributeByIdEvent(int id) {
        List<EventAttributes> lstEA = new LinkedList<>();
        Connection conEA = new Connect().connectData();
        PreparedStatement psEA = null;
        ResultSet rsEA = null;
        try {
            conEA = new Connect().connectData();
            String query = "SELECT * FROM [EventAttributes] WHERE [IdEvent]= '" + id + "'";
            psEA = conEA.prepareStatement(query);
            rsEA = psEA.executeQuery();
            while (rsEA.next()) {
                Events e = fillEventById(rsEA.getInt("IdEvent"));
                Attributes a = fillAttributeById(rsEA.getInt("IdAttributes"));
                String v = rsEA.getString("Value");
                lstEA.add(new EventAttributes(id, e, a, v));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psEA.close();
                rsEA.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstEA;
    }
    
    

    //(EVENT) ---- OK ---- fill all event by a type event
    public List<Events> findAllEventByType(String type) {
        List<Events> lstEvent = new LinkedList<>();
        Connection conE = new Connect().connectData();
        PreparedStatement psE = null;
        ResultSet rsE = null;
        try {
            conE = new Connect().connectData();
            String query = "SELECT * FROM [Event] join [TypeEvent] on [Event].[IdTypeEvent] = [TypeEvent].[Id] where [TypeEvent].[Name] = '" + type + "'";
            psE = conE.prepareStatement(query);
            rsE = psE.executeQuery();
            while (rsE.next()) {
                int id = rsE.getInt("Id");
                TypeEvents typeE = fillTypeEventById(rsE.getInt("IdTypeEvent"));
                Places place = fillPlaceById(rsE.getInt("IdPlace"));
                DateTimes dateTime = fillDateTimeById(rsE.getInt("IdDateTime"));
                String name = rsE.getString("Name");
                String image = rsE.getString("Image");
                Events e = new Events(id, typeE, place, dateTime, name, image);
                lstEvent.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psE.close();
                rsE.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstEvent;
    }

    //(EVENT) ---- OK ---- fill all event
    public List<String> findAllEvent() {
        List<String> lstEvent = new LinkedList<>();
        Connection conE = new Connect().connectData();
        PreparedStatement psE = null;
        ResultSet rsE = null;
        try {
            conE = new Connect().connectData();
            String query = "SELECT distinct [Event].[Name] FROM [Event]";
            psE = conE.prepareStatement(query);
            rsE = psE.executeQuery();
            while (rsE.next()) {
                String name = rsE.getString("Name");
                lstEvent.add(name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psE.close();
                rsE.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstEvent;
    }

    // (EVENT)---- Ok ---- Fill Event by Id
    public Events fillEventById(int id) {
        Events event = new Events();
        Connection conE = new Connect().connectData();
        PreparedStatement psE = null;
        ResultSet rsE = null;
        try {
            conE = new Connect().connectData();
            String query = "SELECT * FROM [Event] WHERE [Id]= '" + id + "'";
            psE = conE.prepareStatement(query);
            rsE = psE.executeQuery();
            while (rsE.next()) {
                TypeEvents ty = fillTypeEventById(rsE.getInt("IdTypeEvent"));
                Places place = fillPlaceById(rsE.getInt("IdPlace"));
                DateTimes dt = fillDateTimeById(rsE.getInt("IdDateTime"));
                String name = rsE.getString("Name");
                String image = rsE.getString("Image");
                event = new Events(id, ty, place, dt, name, image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psE.close();
                rsE.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return event;
    }

    // (Place) ---- OK ----- fill a place by Id
    public Places fillPlaceById(int id) {
        Places place = new Places();
        Connection conP = new Connect().connectData();
        PreparedStatement psP = null;
        ResultSet rsP = null;
        try {
            if (conP != null) {
                conP.close();
            }
            conP = new Connect().connectData();
            String query = "SELECT * FROM Place WHERE Id =" + id;
            psP = conP.prepareStatement(query);
            rsP = psP.executeQuery();
            while (rsP.next()) {
                String p = rsP.getString("Place");
                String address = rsP.getString("Address");
                place = new Places(id, p, address, p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psP.close();
                rsP.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return place;
    }

    //(Place) --- OK ---  Check place have in system neu co trong he thong roi tra ve la true nguoc lai la false
    public boolean checkStatusPlaceById(int id) {
        int count = 0;
        Connection conP = new Connect().connectData();
        PreparedStatement psP = null;
        ResultSet rsP = null;
        try {
            if (conP != null) {
                conP.close();
            }
            conP = new Connect().connectData();
            String query = "SELECT COUNT(Id) AS countRow FROM Place WHERE Id =" + id;
            psP = conP.prepareStatement(query);
            rsP = psP.executeQuery();
            while (rsP.next()) {
                count = rsP.getInt("countRow");
            }
            if (count != 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psP.close();
                rsP.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    // (DateTime) --- OK --- fill all date time by id
    public DateTimes fillDateTimeById(int id) {
        DateTimes dt = new DateTimes();
        Connection conDT = new Connect().connectData();
        PreparedStatement psDT = null;
        ResultSet rsDT = null;
        try {
            if (conDT != null) {
                conDT.close();
            }
            conDT = new Connect().connectData();
            String query = "SELECT * FROM [DATETIME] WHERE Id =" + id;
            psDT = conDT.prepareStatement(query);
            rsDT = psDT.executeQuery();
            while (rsDT.next()) {
                Date date = rsDT.getDate("Date");
                Time time = rsDT.getTime("Time");
                dt = new DateTimes(id, date, date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psDT.close();
                rsDT.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dt;
    }

    // (Type Event) --- OK --- Fill Type Event By Id
    public TypeEvents fillTypeEventById(int id) {
        TypeEvents typeE = new TypeEvents();
        Connection conTE = new Connect().connectData();
        PreparedStatement psTE = null;
        ResultSet rsTE = null;
        try {
            if (conTE != null) {
                conTE.close();
            }
            conTE = new Connect().connectData();
            String query = "SELECT * FROM [TypeEvent] WHERE Id =" + id;
            psTE = conTE.prepareStatement(query);
            rsTE = psTE.executeQuery();
            while (rsTE.next()) {
                String name = rsTE.getString("Name");
                typeE = new TypeEvents(id, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psTE.close();
                rsTE.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return typeE;
    }

}
