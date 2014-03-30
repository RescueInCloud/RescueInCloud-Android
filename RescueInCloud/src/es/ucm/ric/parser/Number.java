/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;

/**
 *
 * @author Mila
 */
public class Number extends TextInterpreter{

    static private double valor;
    static private String relacionCantidad,id,texto;
    
    public Number(){
        super();
    }
    
    public Number(String id,String valor,String rC,String texto){
        super();
        this.valor=Double.parseDouble(valor);
        this.relacionCantidad=rC;
        this.texto=texto;
        this.id=id;
    }
   

    @Override
    public ArrayList<String> textInterpreterparseText(String cadena) {
    	ArrayList<String> command = null;
        String[] w = cadena.split(" ");
        boolean encontrado = false;
        int i=3;
        this.texto="";
        while (!encontrado && i<w.length){
        	texto += w[i]+ " ";
            if (isNumber(w[i])){
                encontrado=true;
                command = new ArrayList<String>();
                command.add("Number");//0->tipo
                command.add(w[0].replace("id", ""));//1->id
                command.add(w[i]);//2->valor
                command.add(encontrarRelacionCantidad(cadena));//3->rC
                command.add(cadena);
            }
            i++;
        }
	return command;
    }
    


	private void setRelacionCantidad(String rC) {
		this.relacionCantidad=rC;
	}

	public boolean isNumber(String s){
        if (s.length()==0 || s==null){
            return false;
        }
        else{
            for (int i =0;i<s.length();i++){
                if(!(s.charAt(i)=='.') && !Character.isDigit(s.charAt(i)))
                    return false;
            }
        }
        return true;
    }
    
    
    void setValor(double parseInt) {
        this.valor=valor; 
    }
    
    public void setId(String id) {
        this.id=id; 
    }


	@Override
	public String encontrarRelacionCantidad(String cadena) {
		String[] w_aux = cadena.split(String.valueOf(this.valor));
		String[] w = w_aux[0].split(" ");
		boolean encontrado = false;
	    int i=3;
	    this.texto="";
	    while (!encontrado && i<w.length){
        	this.texto+= " "+w[i];
	         if (w[i].contains(RelacionCantidad.mas.toString()) || w[i].contains(RelacionCantidad.mayor.toString()) || 
	        		 w[i].contains(">")){
	             encontrado=true;
	             return ">";
	         	}
	         else if (w[i].contains(RelacionCantidad.menos.toString()) || w[i].contains(RelacionCantidad.menor.toString()) || 
	        		 w[i].contains("<")){
	             encontrado=true;
	             return "<";
	         	}
	        i++;
	        }
		return "";
	}


	@Override
	public String toString() {
		return "ID: "+ this.id + " TIPO_PARSER: " + "Number" + " VALOR: " + this.valor + " TEXTO_COMPLETO: "+this.texto
				 + " RELACION_CANTIDAD: "+this.relacionCantidad;
	}
    
}
