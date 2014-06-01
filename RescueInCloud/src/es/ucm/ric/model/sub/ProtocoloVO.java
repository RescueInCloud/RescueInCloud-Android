package es.ucm.ric.model.sub;

import java.util.ArrayList;
import java.util.List;

public class ProtocoloVO {
	
	private int id;
	private String nombre;
	private List<CajaTextoVO> cajas;
	private List<CajaHijoVO> hijos;
	public ProtocoloVO(int id) {
		this.id = id;
		this.cajas = new ArrayList<CajaTextoVO>();
		this.hijos = new ArrayList<CajaHijoVO>();
	}
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public List<CajaTextoVO> getCajas() {
		return cajas;
	}
	public List<CajaHijoVO> getHijos() {
		return hijos;
	}
	
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	public void addCaja(CajaTextoVO caja){
		cajas.add(caja);
	}
	
	public void addHijo(CajaHijoVO hijo){
		hijos.add(hijo);
	}

}
