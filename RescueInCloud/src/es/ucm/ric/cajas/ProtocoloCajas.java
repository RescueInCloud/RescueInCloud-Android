/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.cajas;

import java.sql.*;
//import javax.swing.JOptionPane;

/**
 *
 * @author dav
 */
public class ProtocoloCajas {

    String nombre,email_usuario,fecha;
    
    public ProtocoloCajas(){
        this.nombre="";
        this.email_usuario="";
        this.fecha="";
    }
    public String getNombre() {
        return nombre;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
   
}
