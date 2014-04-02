package es.ucm.ric.model;

public class Nota implements IListable{
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
	@Override
	public String getTitulo() {
		return this.nombre;
	}
	@Override
	public String getSubtitulo() {
		return this.descripcion.substring(0, 20)+"...";
	}
	@Override
	public String getRuta() {
		return null;
	}
	
	

}
