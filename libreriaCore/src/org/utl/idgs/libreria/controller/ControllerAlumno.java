/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.libreria.controller;
import org.utl.idgs.libreria.db.ConexionMySQL;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.libreria.db.ConexionMySQL;
import org.utl.idgs.libreria.model.Alumno;
/**
 *
 * @author iamra
 */
public class ControllerAlumno {
    public int insertar(Alumno alumno) throws Exception{
        String query = "call insertarAlumno(?,?,?,?,?,?,?,?,?,?)";
        int idUsuario = 0;
        int idAlumno = 0;
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        CallableStatement cstmt = conn.prepareCall(query);
        
        cstmt.setString(1, alumno.getUsuario().getNombre());
        cstmt.setString(2, alumno.getUsuario().getApellidoP());
        cstmt.setString(3, alumno.getUsuario().getApellidoM());
        cstmt.setString(4, alumno.getUsuario().getGenero());
        cstmt.setString(5, alumno.getUsuario().getCorreo());
        cstmt.setString(6, alumno.getUsuario().getContrasenia());
        cstmt.setString(7, alumno.getMatricula());
        cstmt.setInt(8, alumno.getUniversidad().getIdUniversidad());
        
        
        cstmt.registerOutParameter(9, Types.INTEGER);
        cstmt.registerOutParameter(10, Types.INTEGER);
        
        cstmt.executeUpdate();
        idUsuario = cstmt.getInt(9);
        idAlumno = cstmt.getInt(10);
        
        alumno.getUsuario().setIdUsuario(idUsuario);
        alumno.setIdAlumno(idAlumno);
        
        cstmt.close();
        conn.close();
        conexion.close();

        return idAlumno;
    }
    
    public void actualizar(Alumno alumno) throws Exception{
        String query = "call actualizarAlumno(?,?,?,?,?,?,?,?,?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();     
        
        CallableStatement cstmt = conn.prepareCall(query);
        
        cstmt.setString(1, alumno.getUsuario().getNombre());
        cstmt.setString(2, alumno.getUsuario().getApellidoP());
        cstmt.setString(3, alumno.getUsuario().getApellidoM());
        cstmt.setString(4, alumno.getUsuario().getGenero());
        cstmt.setString(5, alumno.getUsuario().getContrasenia());
        cstmt.setString(6, alumno.getMatricula());
        cstmt.setInt(7, alumno.getUniversidad().getIdUniversidad());
        cstmt.setInt(8, alumno.getUsuario().getIdUsuario());
        cstmt.setInt(9, alumno.getIdAlumno());
        cstmt.executeUpdate();
        
        cstmt.close();
        conn.close();
        conexion.close();         
    }
    public void eliminar(Alumno alumno) throws Exception{
        String query = "call eliminarAlumno(?,?)";
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        //Paso 3: Generar un objeto que permita preparar la llamada al procedure
        PreparedStatement cstmt = conn.prepareCall(query);
        cstmt.setInt(1, alumno.getUsuario().getIdUsuario());
        cstmt.setInt(2, alumno.getIdAlumno());
        cstmt.executeUpdate();
        cstmt.close();
        conn.close();
        conexion.close();
    }     
    
}
