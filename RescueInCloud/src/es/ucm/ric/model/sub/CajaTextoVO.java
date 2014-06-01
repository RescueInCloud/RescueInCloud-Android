package es.ucm.ric.model.sub;

public class CajaTextoVO {
	
	private int id;
	private int tipo;
	private String contenido;
	private int protocolo_id;
	
	public CajaTextoVO(int id, int tipo, String contenido, int protocolo_id) {
		this.id = id;
		this.tipo = tipo;
		this.contenido = contenido;
		this.protocolo_id = protocolo_id;
	}

	public int getId() {
		return id;
	}

	public int getTipo() {
		return tipo;
	}

	public String getContenido() {
		return contenido;
	}

	public int getProtocolo_id() {
		return protocolo_id;
	}
	
	
	

}
