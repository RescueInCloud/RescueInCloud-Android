package es.ucm.ric.model;

public class Farmaco implements IListable{

	private int id;
	private String nombre_farmaco;
	private String nombre_fabricante;
	private String presentacion_farmaco;
	private String tipo_presentacion;
	private String descripcion_farmaco;
	
	public Farmaco(int id, String nombre_farmaco, String nombre_fabricante,
			String presentacion_farmaco, String tipo_presentacion,
			String descripcion_farmaco) {

		this.id = id;
		this.nombre_farmaco = nombre_farmaco;
		this.nombre_fabricante = nombre_fabricante;
		this.presentacion_farmaco = presentacion_farmaco;
		this.tipo_presentacion = tipo_presentacion;
		this.descripcion_farmaco = descripcion_farmaco;
	}
	public int getId() {
		return id;
	}
	public String getNombre_farmaco() {
		return nombre_farmaco;
	}
	public String getNombre_fabricante() {
		return nombre_fabricante;
	}
	public String getPresentacion_farmaco() {
		return presentacion_farmaco;
	}
	public String getTipo_presentacion() {
		return tipo_presentacion;
	}
	public String getDescripcion_farmaco() {
		return descripcion_farmaco;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", nombre_farmaco=" + nombre_farmaco
				+ ", nombre_fabricante=" + nombre_fabricante + "]";
	}
	@Override
	public String getTitulo() {
		return this.nombre_farmaco;
	}
	@Override
	public String getSubtitulo() {
		return this.descripcion_farmaco;
	}
	@Override
	public String getRuta() {
		return null;
	}
	
	
	
	
	
}
