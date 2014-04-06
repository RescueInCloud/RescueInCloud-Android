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
            for (int i =0;i<s.length();i++){
                //si no es punto ni digito puede ser una unidad
                if(!(s.charAt(i)=='.') && !Character.isDigit(s.charAt(i)))
                	for (Unidades uni : Unidades.values()){
                		dev=separarUnidad(s,uni.toString());
                		if (dev != null){ 
                			break;
                		}
                	}
            }
        }
        return dev;
    }
    
	
	String[] separarUnidad(String cadena,String unidad){
		 String[] dev = null;
		 if (cadena.contains(unidad)){
			dev = new String[2];
            String[] aux = unidad.split(unidad);
            dev[0]=aux[0];
            dev[1]=unidad;
		}
		return dev;
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
    
}

