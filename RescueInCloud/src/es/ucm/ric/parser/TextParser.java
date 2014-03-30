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
/*public class TextParser {
      private TextInterpreter [] texts = {
    	  new Number(),new Text(),new NumberUnit()
      };
      
     public ArrayList parseCaja(String line){
          ArrayList<TextInterpreter> arr= new ArrayList<TextInterpreter>();
           String[] lines = line.split("\n");
           /*falta quitar el id y los tipos para hacerla mas efeciente*/
/*           for (int i=0;i<lines.length;i++){
                arr.add(parseText(lines[i]));
           }
           return arr;
     }
      
     public TextInterpreter parseText(String s){
        	  TextInterpreter miText = null;
        	  int i=0;
        	  /*compruebo con cada componente del array si es el valido, si es el valido
        	   * devuelve algo distinto de null y sale del bucle
        	   */
                 
     /*   	  while(miText == null && i<texts.length){
        		  miText = texts[i].textInterpreterparseText(s);
        		  i++;
        	  }
        	  return miText;
      	}
      }

*/

public class TextParser {
	private TextInterpreter [] texts = {
	    	 new Number(),new Text(),new NumberUnit()
	      };
	public ArrayList parseCaja(String line){
        ArrayList<TextInterpreter> arr= new ArrayList<TextInterpreter>();
         String[] lines = line.split("\n");
         TextInterpreter auxT;
         //cada line es una caja
         /*falta quitar el id y los tipos para hacerla mas efeciente*/
         ArrayList<String> aux = null;
         for (int i=0;i<lines.length;i++){
        	 int j=0;
        	 while(aux==null && j<texts.length){
	        	 aux =  texts[j].textInterpreterparseText(lines[i]);
	        	 if (aux!= null){
	        		 if (aux.get(0).equals("Text")){
	        			 auxT=new Text(aux.get(1),aux.get(2));
	        			 arr.add(auxT);
	        		 } else if (aux.get(0).equals("NumberUnit")){
	        			 auxT=new NumberUnit(aux.get(1),aux.get(2),aux.get(3),aux.get(4),aux.get(5));
	        			 arr.add(auxT);
	        		 }  else if (aux.get(0).equals("Number")){
	    			 auxT=new Number(aux.get(1),aux.get(2),aux.get(3),aux.get(4));
	    			 arr.add(auxT);
	    		 	} 
        	 	}
	        	 aux=null;
	        	 j++;
        	}
         }
         
   return arr;
   }
}