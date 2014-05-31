package es.ucm.ric.model;

public class Nota implements IListable{
	
	private final int LIMITE = 36;
	
	private int id;
	private String nombre;
	private String descripcion;
	public Nota(int id, String nombre, String descripcion) {
		this.id = id;
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
		if(this.descripcion.length()>LIMITE)
			return this.descripcion.substring(0, LIMITE)+"...";
		else
			return this.descripcion;
	}
	@Override
	public String getRuta() {
		return null;
	}
	@Override
	public int getId() {
		return id;
	}
	
	

}
