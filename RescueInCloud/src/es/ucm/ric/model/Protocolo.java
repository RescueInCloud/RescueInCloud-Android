/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.model;

//import javax.swing.JOptionPane;

/**
 *
 * @author dav
 */
public class Protocolo {

	private int id;
    private String nombre;
    
    public Protocolo(){
    	this.nombre="";
    }
    public Protocolo(int id, String nombre){
        this.id = id;
    	this.nombre=nombre;
    }
    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	public int getId() {
		return id;
	}

    
}
