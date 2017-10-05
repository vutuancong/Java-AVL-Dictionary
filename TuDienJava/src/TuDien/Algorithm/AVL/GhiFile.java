/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuDien.Algorithm.AVL;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author vutuancong
 */
public class GhiFile {

    public static void ghiFile(String tu, String nghia, int flag) {
        String a ="";
        if (flag == 1) {
            a = "D:\\TuDienJava\\AnhViet\\";
        }
        if (flag == 2) {
            a = "D:\\TuDienJava\\AnhAnh\\";
        }
        try {
            PrintWriter fileNew = new PrintWriter(new FileOutputStream(a + tu + ".txt"));
            fileNew.write(nghia);
            fileNew.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.toString());
        }
    }
}
