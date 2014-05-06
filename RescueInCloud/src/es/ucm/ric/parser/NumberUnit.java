/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;

/*
 * Por ejemplo: 44mg, 70 kg etc
 */
public class NumberUnit extends TextInterpreter{
    
 private String unidad,texto,relacionCantidad;
 private double valor;
 private int id;
 private String rC;
  
    public NumberUnit(){
        super();
    }
   
    public String getRelacionCantidad(){
    	return this.relacionCantidad;
    }
    
    
    public NumberUnit(int id,String valor,String unidad,String relacionCantidad,String texto){
        super();
        this.id=id;
        this.unidad=unidad;
        this.texto=texto;
        this.valor=Double.parseDouble(valor);
        this.relacionCantidad=relacionCantidad;
    }
    @Override
     public ArrayList<String> textInterpreterparseText(String cadena) {
    	ArrayList<String> command = null;
        String[] w = cadena.split(" ");
        boolean encontrado = false;
        int i=3;
        String[] datos;
        this.texto="";
        while (!encontrado && i<w.length){
        	texto+= " "+w[i];
            datos = (isNumber(w[i]));
            if (datos!=null){
            	if (datos[0].charAt(datos[0].length()-1)==','){
            		datos[0]=datos[0].replace(",","");
            	}
            	else if (datos[0].charAt(datos[0].length()-1)=='.'){
            		datos[0]= datos[0].replace(".","");
            	}
            	
                encontrado=true;
                command = new ArrayList<String>();
                command.add("NumberUnit"); //0->nombre
                command.add(w[0].replace("id", "")); //1->id
                command.add(datos[0]); //2->valor
                command.add(datos[1]); //3->unidad
                command.add(this.encontrarRelacionCantidad(cadena,datos[0]));//4->relacionCant
                command.add(texto); //4->texto
            }
            i++;
        }
	return command;
    }
    public void setId(int id) {
		this.id=id;
		
	}


	public String[] isNumber(String s){
        String[] dev = null;
        if (s.length()==0 || s==null){
            return dev;
        }
        else{
        	boolean hayDigito=false;
        	int i=0;
        	
        	while(i<s.length() && dev == null){
            	String copia="";
                //si no es punto ni digito puede ser una unidad
            	if(Character.isDigit(s.charAt(i)) && !hayDigito){
            		hayDigito=true;
            		copia=s.replace("0", "").replace("1", "").replace("2","").replace("3","").replace("4","").replace("5","").replace("6","").replace("7","").replace("8","").replace("9","");
            	}
                if(!(s.charAt(i)=='.') && hayDigito)
                	for (Unidades uni : Unidades.values()){
                		if (separarUnidad(copia,uni.getFriendlyName())){
                			dev = new String[2];
                			dev[0]=s.replace(copia, "");
                			dev[1]=copia;
                			break;
                		}
                	}
                i++;
            }
        }
        return dev;
    }
    
	
	boolean separarUnidad(String cadena,String unidad){
		 if (cadena.equals(unidad)){
			return true;
		}
		return false;
	}
    
    void setValor(Double valor,String unidad) {
        this.valor=valor;
        this.unidad=unidad; 
    }




	private void setRelacionCantidad(String string) {
		this.rC=rC;
		
	}


	@Override
	public String toString() {
			return "ID: "+ this.id + " TIPO_PARSER: " + "NumberUnit" + " VALOR: " + this.valor + " UNIDAD: " + this.unidad + " texto_completo: "+this.texto
					 + " RELACION_CANTIDAD: "+this.relacionCantidad;
		
	}
	
	public String getUnidad(){
		return this.unidad;
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

	public double getValor() {
		// TODO Auto-generated method stub
		return this.valor;
	}
    
}

