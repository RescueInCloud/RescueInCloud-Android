/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.model;

import java.util.ArrayList;


public class Protocolo {

	protected int id;
	protected String nombre;
	protected ArrayList<CajaTexto> cajas;
	protected ArrayList<CajasHijos> hijos;
	protected ArrayList<Integer> decision;
    
	public Protocolo(){
    	this.nombre="";
    	cajas= new ArrayList<CajaTexto>();
    	hijos= new ArrayList<CajasHijos>();
    }
    
    public Protocolo(int id, String nombre){
        this.id = id;
    	this.nombre=nombre;
    	cajas= new ArrayList<CajaTexto>();
    	hijos= new ArrayList<CajasHijos>();
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
	
    public ArrayList<CajaTexto> getCajas() {
		return cajas;
	}

	public ArrayList<CajasHijos> getHijos() {
		return hijos;
	}


	public void setHijos(CajasHijos hijos) {
		this.hijos.add(hijos);
	}


    public void setCajas(CajaTexto caja){
    	this.cajas.add(caja);
    	if (caja.getTipo().equals("decision"))
    		this.decision.add(caja.getIdCajaTexto());	
    }

}
