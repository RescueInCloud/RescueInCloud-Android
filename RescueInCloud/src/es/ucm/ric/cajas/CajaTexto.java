/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.cajas;

/**
 *
 * @author Mila
 */
public class CajaTexto {
    int idCajaTexto;
    String tipo,subTipo,text,nombre_protocolo;

    public CajaTexto() {
        this.idCajaTexto = -1;
        this.tipo = "";
        this.subTipo = "";
        this.text = "";
        this.nombre_protocolo = "";
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

    public String getSubTipo() {
        return subTipo;
    }

    public void setSubTipo(String subTipo) {
        this.subTipo = subTipo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNombre_protocolo() {
        return nombre_protocolo;
    }

    public void setNombre_protocolo(String nombre_protocolo) {
        this.nombre_protocolo = nombre_protocolo;
    }
    
    public String toString(){
        return "id"+this.idCajaTexto+" " + this.tipo+" " + this.subTipo+" "+this.text+"\n";
    }
    
}
