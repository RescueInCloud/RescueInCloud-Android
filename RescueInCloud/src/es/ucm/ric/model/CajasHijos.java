/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


/**
 *
 * queda inutilizada, se actualiza en protocolo
 */
public class CajasHijos {
   
	private HashMap<Integer, ArrayList<Tupla>> hijos;
	
	
	public CajasHijos() {
		this.hijos = new HashMap<Integer, ArrayList<Tupla>>();
	}
	
	public Set getIds() {
		return this.hijos.keySet();
	}
	
	public ArrayList<Tupla> getHijos(int id) {
		return this.hijos.get(id);
	}
	
	
	public void addHijo(int id,Tupla hijo){
		if (this.hijos.containsKey(id)){
			this.hijos.get(id).add(hijo);
		}
		else {
			ArrayList<Tupla> arr = new ArrayList<Tupla>();
			arr.add(hijo);
			this.hijos.put(id,arr);
		}
	}
	
	
	
	
}
