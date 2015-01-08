/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eproject.easyTickect.model;

import eproject.easyTickect.object.DateTimes;
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
public class DatesDAO {
    Connection con = new Connect().connectData();
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Date> fillDateByPlace(String place) {
        List<Date> lstDateTime = new LinkedList<>();
        try {
            con = new Connect().connectData();
            String query = "SELECT distinct [DateTime].[Date] FROM [DateTime] JOIN [Event] ON [Event].IdDateTime = [DateTime].Id join [Places] on [Event].[IdPlace] = [Places].[Id] where [Places].[Place] = '"+place+"'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date date = rs.getDate("Date");
                lstDateTime.add(date);
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
        return lstDateTime;
    }
    
    public DateTimes findDateTimeById(int idDT) {
        DateTimes dateTimes = new DateTimes();
        try {
            String query = "select * from [DateTime] where [Id] = '" + idDT + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                Date date = rs.getDate("Date");
                Time time = rs.getTime("Time");
                dateTimes = new DateTimes(id, date, time);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dateTimes;
    }
}
