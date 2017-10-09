/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuDien.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vutuancong
 */
public class LoadData {

    Connection conn = null;
    PreparedStatement ps = null;
    ConnectionMySql mc = new ConnectionMySql();

    public boolean kiemTra(String name, String pass) {
        conn = mc.connect();
        String sql = "SELECT * FROM users WHERE name = ? AND password = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Loi: " + ex.toString());
        }
        return true;
    }
}
