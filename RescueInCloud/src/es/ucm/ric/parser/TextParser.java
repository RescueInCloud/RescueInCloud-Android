/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;


public class TextParser {
	private TextInterpreter [] texts = {
	    	 new Number(),new Text(),new NumberUnit()
	      };
	
	/*devuelve un ArrayList con todas las cajas del protocolo parseadas*/
	public ArrayList parseCaja(String line){
        ArrayList<TextInterpreter> arr= new ArrayList<TextInterpreter>();
         String[] lines = line.split("\n");
         TextInterpreter auxT;
         //cada line es una caja
         /*falta quitar el id y los tipos para hacerla mas efeciente*/
         ArrayList<String> aux = null;
         for (int i=0;i<lines.length;i++){
        	 int j=0;
        	 aux=null;
        	 while(aux==null && j<texts.length){
	        	 aux =  texts[j].textInterpreterparseText(lines[i]);
	        	 if (aux!= null){
	        		 if (aux.get(0).equals("Text")){
	        			 auxT=new Text(Integer.parseInt(aux.get(1)),aux.get(2)); //id,texto
	        			 arr.add(auxT);
	        		 } else if (aux.get(0).equals("NumberUnit")){
	        			 auxT=new NumberUnit(Integer.parseInt(aux.get(1)),aux.get(2),aux.get(3),aux.get(4),aux.get(5));
	        			 arr.add(auxT);
	        		 }  else if (aux.get(0).equals("Number")){
	    			 auxT=new Number(Integer.parseInt(aux.get(1)),aux.get(2),aux.get(3),aux.get(4));
	    			 arr.add(auxT);
	    		 	} 
        	 	}
	        	 //aux=null;
	        	 j++;
        	}
         }
         
   return arr;
   }
}