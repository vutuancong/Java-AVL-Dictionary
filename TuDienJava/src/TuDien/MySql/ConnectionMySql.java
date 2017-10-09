/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuDien.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vutuancong
 */
public class ConnectionMySql {

    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/tu_dien";
    String user = "root";
    String pass = "1234";

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Ket noi thanh cong");
            } else {
                System.out.println("Ket noi khong thanh cong");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Loi: "+ex.toString());
            System.out.println("Load driver");
        }catch (SQLException ex){
            System.out.println("Loi: "+ex.toString());
        }
        return conn;
    }
}
