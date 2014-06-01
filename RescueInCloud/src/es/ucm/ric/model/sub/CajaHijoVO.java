package es.ucm.ric.model.sub;

public class CajaHijoVO {

	private int id;
	private int id_protocolo;
	private int id_hijo;
	private int id_padre;
	private int tipo_relacion;
	
	public CajaHijoVO(int id, int id_protocolo, int id_hijo, int id_padre,
			int tipo_relacion) {
		this.id = id;
		this.id_protocolo = id_protocolo;
		this.id_hijo = id_hijo;
		this.id_padre = id_padre;
		this.tipo_relacion = tipo_relacion;
	}

	public int getId() {
		return id;
	}

	public int getId_protocolo() {
		return id_protocolo;
	}

	public int getId_hijo() {
		return id_hijo;
	}

	public int getId_padre() {
		return id_padre;
	}

	public int getTipo_relacion() {
		return tipo_relacion;
	}
	
	
}
