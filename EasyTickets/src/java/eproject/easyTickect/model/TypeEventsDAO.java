/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.TypeEvents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class TypeEventsDAO {
    private final Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public TypeEventsDAO() {
        this.con = new Connect().connectData();
    }

    public TypeEvents getTypeEventById(int id) {
        TypeEvents typeevent = new TypeEvents();
        try {
            String query = "SELECT * FROM [TypeEvent] WHERE [TypeEvent].[Id] = '" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("Name");
                typeevent = new TypeEvents(id, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeEventsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TypeEventsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return typeevent;
    }
    
    public TypeEvents getTypeEventByName(String name) {
        TypeEvents typeevent = new TypeEvents();
        try {
            String query = "SELECT * FROM [TypeEvent] WHERE [TypeEvent].[Name] = '" + name + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                typeevent = new TypeEvents(id, name);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeEventsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TypeEventsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return typeevent;
    }
    
    public List<TypeEvents> getAllTypeEvents(){
        List<TypeEvents> lstTypeEvents = new LinkedList<>();
        try {
            String query = "SELECT * FROM [TypeEvent]";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                lstTypeEvents.add(new TypeEvents(id, name));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeEventsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(TypeEventsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstTypeEvents;
    }
}
