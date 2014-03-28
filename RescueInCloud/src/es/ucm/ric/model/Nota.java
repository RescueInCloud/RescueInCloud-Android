package es.ucm.ric.model;

public class Nota {
	private String nombre;
	private String descripcion;
	public Nota(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	

}
