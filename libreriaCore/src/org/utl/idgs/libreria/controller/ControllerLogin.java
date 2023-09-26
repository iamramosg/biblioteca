/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.libreria.controller;
import org.utl.idgs.libreria.db.ConexionMySQL;
import org.utl.idgs.libreria.model.Alumno;
import org.utl.idgs.libreria.model.Usuario;
import org.utl.idgs.libreria.model.Universidad;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author iamra
 */
public class ControllerLogin {
    public Usuario entrarUsuario(Usuario u) throws Exception{
        String query = "SELECT * FROM usuario WHERE correo = '" + u.getCorreo() + "' AND contrasenia = '" + u.getContrasenia() + "'";
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        u = new Usuario();
        if (rs.next()) {
            u = fillU(rs);
        }
        rs.close();
        stmt.close();
        conn.close();
        conexion.close();
        System.out.println(query);
        return u;
    }
    
    public Usuario fillU(ResultSet rs) throws Exception{
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombre(rs.getString("nombre"));
        u.setApellidoP(rs.getString("apellidoP"));
        u.setApellidoM(rs.getString("apellidoM"));
        u.setGenero(rs.getString("genero"));
        u.setCorreo(rs.getString("correo"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setRol(rs.getString("rol"));
        u.setEstatus(rs.getInt("estatus"));
        
        return u;
    }
    
    public Alumno entrarAlumno(Usuario u) throws Exception{
        String query = "SELECT * FROM vista_a WHERE correo = '" + u.getCorreo() + "' AND contrasenia = '" + u.getContrasenia() + "'";
        
        ConexionMySQL conexion = new ConexionMySQL();
        Connection conn = conexion.open();
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        Alumno a = new Alumno();
        if (rs.next()){
            a = fillA(rs);
        }
        rs.close();
        stmt.close();
        conn.close();
        conexion.close();
        System.out.println(query);
        return a;        
    }
    
    public Alumno fillA(ResultSet rs) throws Exception{
        Usuario u = new Usuario();
        Alumno a = new Alumno();
        Universidad un = new Universidad();
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombre(rs.getString("nombre"));
        u.setApellidoP(rs.getString("apellidoP"));
        u.setApellidoM(rs.getString("apellidoM"));
        u.setGenero(rs.getString("genero"));
        u.setCorreo(rs.getString("correo"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setRol(rs.getString("rol"));
        u.setEstatus(rs.getInt("estatus"));
        
        un.setIdUniversidad(rs.getInt("idUniversidad"));
        un.setNombre(rs.getString("nombreU"));
        un.setPais(rs.getString("pais"));
        un.setEstatus(rs.getInt("estatusU"));
        
        a.setIdAlumno(rs.getInt("idAlumno"));
        a.setUsuario(u);
        a.setMatricula(rs.getString("matricula"));
        a.setUniversidad(un);
        
        return a; 
    }
    
}
