/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.libreria.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author garni
 */
public class ConexionMySQL {
    Connection conn;
    
    public Connection open(){
        String usuario = "root";
        String password = "12345";
        String url = "jdbc:mysql://127.0.0.1:3306/biblioteca?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, password);
            return conn;
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    public void close(){
        try{
            conn.close();
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
