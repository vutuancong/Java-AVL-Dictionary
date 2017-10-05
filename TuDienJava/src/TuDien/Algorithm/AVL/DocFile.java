/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuDien.Algorithm.AVL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author vutuancong
 */

public class DocFile {
    
    public static void docFile(String tu) {
        try {
            Scanner docFile = new Scanner(new File("D:\\TuDienJava\\Tu\\"+ tu+".txt"));
            while(docFile.hasNextLine()){
                System.out.println(docFile.nextLine());
            }
            docFile.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Từ chưa có trong từ điển!");
        }
    }
}
