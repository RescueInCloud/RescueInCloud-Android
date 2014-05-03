/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ucm.ric.model;

import java.util.ArrayList;


public class Protocolo implements IListable{

	protected int id;
	protected String nombre;
	protected ArrayList<CajaTexto> cajas;
	protected CajasHijos hijos;//relaciones
	protected ArrayList<Integer> decision;
   

	public Protocolo(){
    	this.nombre="";
    	cajas= new ArrayList<CajaTexto>();
    	hijos= new CajasHijos();
    	decision= new ArrayList<Integer>();
    }
    
    public Protocolo(int id, String nombre){
        this.id = id;
    	this.nombre=nombre;
    	cajas= new ArrayList<CajaTexto>();
    	hijos= new CajasHijos();
    	decision= new ArrayList<Integer>();
    }
    
    public ArrayList<Integer> getDecision() {
		return decision;
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

	public CajasHijos getHijos() {
		return hijos;
	}


	public void setHijos(CajasHijos hijos) {
		this.hijos = hijos;
	}


    public void anyadirCaja(CajaTexto caja){
    	this.cajas.add(caja);
    	if (caja.getTipo()==1)
    		this.decision.add(caja.getIdCajaTexto());	
    }
    
    public String cajas_toString(){
    	String s="";
    	for (CajaTexto ct: cajas){
    	s+=ct.toString();
    	}
    	return s;
    }

	@Override
	public String getTitulo() {
		return nombre;
	}

	@Override
	public String getSubtitulo() {
		return nombre;
	}

	@Override
	public String getRuta() {
		return null;
	}

}
