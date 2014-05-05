/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;


public abstract class TextInterpreter {
	
    public abstract ArrayList<String> textInterpreterparseText(String cadena);
	
    public abstract String toString();
    
    public abstract int getId();
    public abstract String getContenido();
    
    public abstract String getRelacionCantidad();

	public String encontrarRelacionCantidad(String cadena,String valor){
			String[] w_aux = cadena.split(valor);
			String[] w = w_aux[0].split(" ");
			boolean encontrado = false;
		    int i=1;
		    while (!encontrado && i<w.length){
		    	for (RelacionCantidadMayor relacionMayor: RelacionCantidadMayor.values()){
		    		if (w[i].contains(relacionMayor.toString())){
		    			encontrado=true;
			            return ">";
		    		}
		    	}
		    	for (RelacionCantidadMenor relacionMenor: RelacionCantidadMenor.values()){
		    		if (w[i].contains(relacionMenor.toString())){
		    			encontrado=true;
			            return "<";
		    		}
		    	}
		    	
		         if ( w[i].contains(">")){
		             encontrado=true;
		             return ">";
		         	}
		         if (w[i].contains("<")){
		             encontrado=true;
		             return "<";
		         	}
		         
		        i++;
		        }
		    return "";
	}
	


}
