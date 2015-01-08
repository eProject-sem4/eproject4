/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eproject.easyTickect.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ViTAnH
 */
public class TimesDAO {
    
    Connection con = new Connect().connectData();
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Time> fillTimeByDate(Date tdate) {
        List<Time> lstTime = new LinkedList<Time>();
        try {
            con = new Connect().connectData();
            String query = "SELECT * from [DateTime] where [DateTime].[Date] = '"+tdate+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Time time = rs.getTime("Time");
                lstTime.add(time);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lstTime;
    }
}
