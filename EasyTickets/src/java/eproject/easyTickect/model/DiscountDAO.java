/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eproject.easyTickect.model;

import eproject.easyTickect.object.Discounts;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kien
 */
public class DiscountDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public DiscountDAO() {
        this.con = new Connect().connectData();
    }
    
    public Discounts findDiscountById(int idD) {
        Discounts discounts = new Discounts();
        try {
            String query = "select * from [Discount] where [Id] = '" + idD + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                float percent = rs.getFloat("Percent");
                Date startDate = rs.getDate("StartDate");
                Date endDate = rs.getDate("EndDate");
                discounts = new Discounts(id, percent, startDate, endDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return discounts;
    }
}
