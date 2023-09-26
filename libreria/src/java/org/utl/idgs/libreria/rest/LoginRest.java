/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.idgs.libreria.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utl.idgs.libreria.controller.ControllerLogin;
import org.utl.idgs.libreria.model.Empleado;
import org.utl.idgs.libreria.model.Usuario;

/**
 *
 * @author garni
 */
@Path("restlibreria")
public class LoginRest extends Application{
    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceder(@FormParam ("datosUsuario") @DefaultValue ("") String datosUsuario){
        Gson gson = new Gson();
        Usuario u = new Usuario();
        Empleado e = new Empleado();
        u = gson.fromJson(datosUsuario, Usuario.class);
        ControllerLogin objCA = new ControllerLogin();
        try {
            e = objCA.entrar(u);
        } catch (Exception ex) {
            Logger.getLogger(LoginRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        String out = gson.toJson(e);
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("in")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response logIn(@FormParam("datosAcceso") @DefaultValue("") String datosAcceso){
        Gson gson = new Gson();
        Usuario usuario = new Usuario();
        Empleado e = new Empleado();
        usuario = gson.fromJson(datosAcceso, Usuario.class);
        System.out.println(usuario.toString());
        ControllerLogin objCA = new ControllerLogin();
        try {
            e = objCA.entrar(usuario);
            e.getUsuario().setLastToken();
            objCA.guardarToken(e);
            
        } catch (Exception ex) {
            Logger.getLogger(LoginRest.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        String out = gson.toJson(e);
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
