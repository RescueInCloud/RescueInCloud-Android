/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.model;

import java.util.ArrayList;


/**
 *
 * @author Mila
 */
public class CajasHijos {
   
	private int id;
	private ArrayList<Tupla> hijos;
	public CajasHijos(int id) {
		this.id = id;
		this.hijos = new ArrayList<Tupla>();
	}
	public int getId() {
		return id;
	}
	public ArrayList<Tupla> getHijos() {
		return hijos;
	}
	
	public void addHijo(Tupla hijo){
		hijos.add(hijo);
	}
	
	
	
	
}
