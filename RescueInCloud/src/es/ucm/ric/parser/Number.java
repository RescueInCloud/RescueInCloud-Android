/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;

/**
 *
 * reconoce las cajas cuyo contenido tiene un numero o un numero seguido de un espacio y una unidad */
public class Number extends TextInterpreter{

    private double valor;
    private int id;
    private String relacionCantidad,texto;
    
    public Number(){
        super();
    }
    
    public Number(int id,String valor,String rC,String texto){
        super();
        this.valor=Double.parseDouble(valor);
        this.relacionCantidad=rC;
        this.texto=texto;
        this.id=id;
    }
   
    public int getId(){
    	return id;
    }
    
    public double getValor(){
    	return valor;
    }


    public String getRelacionCantidad(){
    	return this.relacionCantidad;
    }
    
    @Override
    public ArrayList<String> textInterpreterparseText(String cadena) {
    	ArrayList<String> command = null;
        String[] w = cadena.split(" ");
        boolean encontrado = false;
        int i=3;
        //this.texto= cadena.replace(w[0],"").replace(w[1], "").replace(w[2], "");
        this.texto="";
        while (!encontrado && i<w.length){
        	//texto += w[i]+ " ";
            if (isNumber(w[i])){
            	 encontrado=true;
            	 command = new ArrayList<String>();
            		if (w[i].charAt(w[i].length()-1)==','){
            			w[i]=w[i].replace(",","");
                	}
                	else if (w[i].charAt(w[i].length()-1)=='.'){
                		w[i]=w[i].replace(".","");
                	}
                	
            	 if (i+1<w.length){
	            	 if(!isUnit(w[i+1])){
		                command.add("Number");//0->tipo
		                command.add(w[0].replace("id", ""));//1->id
		                command.add(w[i]);//2->valor
		                command.add(encontrarRelacionCantidad(cadena,w[i]));//3->rC
		                if (i+1<w.length){
	                     	for (int j=i;j<w.length;j++){
	                     		texto+=w[j]+" ";
	                     	}
	                     }
		                
		                
		                command.add(texto);
	            	 }
	            	 else{
	            		 command.add("NumberUnit"); //0->nombre
	                     command.add(w[0].replace("id", "")); //1->id
	                     command.add(w[i]); //2->valor
	                     command.add(w[i+1]); //3->unidad
	                     command.add(this.encontrarRelacionCantidad(cadena,w[i]));//4->relacionCant
	                     command.add(texto); //4->texto
	                     command.add(w[i]);
	                     command.add(w[i+1]);
	                     String aux="";
	                     if (i+1<w.length-1){
	                     	for (int j=i+2;j<w.length;j++){
	                     		aux+=w[j]+" ";
	                     	}
	                     }
	                     command.add(aux);
	            	 }
            	 }
            	 else{
            		 command.add("Number");//0->tipo
		                command.add(w[0].replace("id", ""));//1->id
		                command.add(w[i]);//2->valor
		                command.add(encontrarRelacionCantidad(cadena,w[i]));//3->rC
		                command.add(texto+" "+ w[i]);
            	 }
            }
            else this.texto += w[i]+" ";
            i++;
        }
	return command;
    }
    


	private void setRelacionCantidad(String rC) {
		this.relacionCantidad=rC;
	}

	public boolean isUnit(String s){
		for (Unidades uni : Unidades.values()){
    		 if (s.equals(uni.getFriendlyName()))
    			 return true;
    		 }
		return false;
	}
	
	public boolean isNumber(String s){
        if (s.length()==0 || s==null){
            return false;
        }
        else{
        
            for(int i=0;i<s.length();i++){
                if(!(s.charAt(i)==',') && !(s.charAt(i)=='.') && !Character.isDigit(s.charAt(i))){
                     return false;
                }
            }
        }
        return true;
    }
    
    
    void setValor(double parseInt) {
        this.valor=valor; 
    }
    
    public void setId(int id) {
        this.id=id; 
    }


	@Override
	public String toString() {
		return "ID: "+ this.id + " TIPO_PARSER: " + "Number" + " VALOR: " + this.valor + " TEXTO_COMPLETO: "+this.texto
				 + " RELACION_CANTIDAD: "+this.relacionCantidad;
	}

	@Override
	public String getContenido() {
		// TODO Auto-generated method stub
		return texto;
	}
    
}
