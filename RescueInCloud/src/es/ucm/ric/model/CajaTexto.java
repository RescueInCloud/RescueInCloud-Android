/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.model;

/**
 *
 * @author Mila
 */
public class CajaTexto {
    private int idCajaTexto, id_protocolo;
    private String tipo,contenido;

    public CajaTexto(int idCajaTexto, int id_protocolo, String tipo, String text) {
		super();
		this.idCajaTexto = idCajaTexto;
		this.id_protocolo = id_protocolo;
		this.tipo = tipo;
		this.contenido = text;
	}

    
    
	public int getId_protocolo() {
		return id_protocolo;
	}

	public int getIdCajaTexto() {
        return idCajaTexto;
    }

    public void setIdCajaTexto(int idCajaTexto) {
        this.idCajaTexto = idCajaTexto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    public String getContenido() {
		return contenido;
	}



	public String toString(){
        return "id"+this.idCajaTexto+" " + this.tipo+" " +" "+this.contenido+"\n";
    }
    
    
}