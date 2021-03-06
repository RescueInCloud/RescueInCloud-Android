/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;


public class Text extends TextInterpreter{
    public String texto;
    private int id;
    
    public Text(){
        super();
    }
    
    public Text(int id,String texto){
        super();
        this.texto=texto;
        this.id=id;
    }
    

    @Override
    public ArrayList<String> textInterpreterparseText(String cadena) {
    	ArrayList<String> command = null;
        String[] w = cadena.split(" ");
        boolean encontrado = false;
        String cadena_aux="";
        int i=3;
        while (!encontrado && i<w.length){
        	cadena_aux += w[i] + " ";
            if (!isText(w[i])){
                encontrado=true;
            }
            i++;
        }
        if (!encontrado){
        	command = new  ArrayList<String>();
        	command.add("Text");
        	command.add(w[0].replace("id", ""));
        	command.add(cadena_aux);
        }
	return command;
    }
    
    public void setId(int id) {
		this.id=id;
	}



	public boolean isText(String s){
        if (s.length()==0 || s==null)
            return false;
        else if(Character.isDigit(s.charAt(0)))
                return false;
        return true;
    }
    
    
    void setValor(String texto) {
        this.texto=texto; 
    }


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: "+ this.id + " TIPO_PARSER: " + "Text" + " TEXTO: "+this.texto;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public String getContenido() {
		// TODO Auto-generated method stub
		return texto;
	}

	@Override
	public String getRelacionCantidad() {
		// TODO Auto-generated method stub
		return "";
	}
    
}
