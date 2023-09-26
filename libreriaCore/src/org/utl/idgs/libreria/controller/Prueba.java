/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.libreria.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.idgs.libreria.db.ConexionMySQL;
import org.utl.idgs.libreria.model.Alumno;
import org.utl.idgs.libreria.model.Usuario;


/**
 *
 * @author garni
 */
public class Prueba {

    public static void main(String[] args) {
        //probarInseratar();
        probarAcceso();
//        probarConexion();
    }

    public static void probarConexion() {
        try {
            ConexionMySQL objConexion = new ConexionMySQL();
            Connection conexion = objConexion.open();
            System.out.println(conexion.toString());
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    public static void probarAcceso(){
        Usuario u = new Usuario();
        u.setContrasenia("15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225");
        u.setCorreo("garnic09@gmail.com");
    
        ControllerLogin ca = new ControllerLogin();
        try{
            //Empleado e = ca.entrar(u);
            Alumno a = ca.entrarAlumno(u);
            u.toString();
            System.out.println("Acceso Concedido");
            System.out.println(a.getIdAlumno());
        }catch(Exception ex){
            System.out.println("Acceso Denegado");
        }
    }
//    
    

}
