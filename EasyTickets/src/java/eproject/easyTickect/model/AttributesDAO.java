/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Attributes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Trang
 */
public class AttributesDAO {

    Connection conA = new Connect().connectData();
    PreparedStatement psA = null;
    ResultSet rsA = null;

    public AttributesDAO() {
    }

    // (Attributes)---- Ok ---- Fill Attribute by Id
    public Attributes fillAttributeById(int id) {
        Attributes attribute = new Attributes();
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
                psA.close();
                rsA.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attribute;
    }

    // (Attributes)---- Ok ---- Fill Attribute by Id
    public Attributes fillAttributeByName(String name) {
        Attributes attribute = new Attributes();
        boolean check = checkNameAttributeSystem(name);
        if (check) {
            try {
                conA = new Connect().connectData();
                String query = "SELECT * FROM [Attributes] WHERE [Name]= '" + name + "'";
                psA = conA.prepareStatement(query);
                rsA = psA.executeQuery();
                while (rsA.next()) {
                    int id = rsA.getInt("Id");
                    attribute = new Attributes(id, name);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    psA.close();
                    rsA.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return attribute;
    }

    //(ATTRIBUTE) ----- OK ----- check name of attribute in system
    public boolean checkNameAttributeSystem(String name) {
        boolean check = false;
        try {
            conA = new Connect().connectData();
            String query = "SELECT COUNT(Name) AS c FROM [Attributes] WHERE [Name]= N'" + name + "'";
            psA = conA.prepareStatement(query);
            rsA = psA.executeQuery();
            while (rsA.next()) {
                int count = rsA.getInt("c");
                if (count != 0) {
                    check = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                psA.close();
                rsA.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public boolean createAttribute(String nameAttribute) {
        boolean check = checkNameAttributeSystem(nameAttribute);
        if (!check) {
            try {
                conA = new Connect().connectData();
                String query = "INSERT INTO [Attributes] VALUES (N'" + nameAttribute + "')";
                psA = conA.prepareStatement(query);
                psA.executeUpdate();
                check = false;
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    psA.close();
                    rsA.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return check;
    }

}
