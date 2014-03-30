/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.ucm.ric.parser;

import java.util.ArrayList;

public class NumberUnit extends TextInterpreter{
    
 private String unidad,id,texto,relacionCantidad;
 private double valor;
 private String rC;
  
    public NumberUnit(){
        super();
    }
   
    public NumberUnit(String id,String valor,String unidad,String relacionCantidad,String texto){
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
                command.add(this.encontrarRelacionCantidad(cadena));//4->relacionCant
                command.add(texto); //4->texto
            }
            i++;
        }
	return command;
    }
    public void setId(String id) {
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
                    if (s.contains(Unidades.dg.toString())){
                        dev = new String[2];
                        String[] aux = s.split("dg");
                        dev[0]=aux[0];
                        dev[1]="dg";
                        break;
                    }
                    else if (s.contains(Unidades.mg.toString())){
                        dev = new String[2];
                        String[] aux = s.split("mg");
                        dev[0]=aux[0];
                        dev[1]="mg"; 
                        break;
                    } else if (s.contains(Unidades.kg.toString())){
                        dev = new String[2];
                        String[] aux = s.split("kg");
                        dev[0]=aux[0];
                        dev[1]="kg";
                        break;
                    } else if (s.contains(Unidades.mmol.toString())){
                        dev = new String[2];
                        String[] aux = s.split("mmol");
                        dev[0]=aux[0];
                        if (aux.length==2)
                        	dev[1]="mmol"+aux[1];
                        else 
                        	dev[1]="mmol";
                        break;
                    }
            }
        }
        return dev;
    }
    
    
    void setValor(Double valor,String unidad) {
        this.valor=valor;
        this.unidad=unidad; 
    }


	@Override
	public String encontrarRelacionCantidad(String cadena) {
		String[] w_aux = cadena.split(String.valueOf(this.valor));
		String[] w = w_aux[0].split(" ");
		boolean encontrado = false;
	    int i=1;
	    while (!encontrado && i<w.length){
	         if (w[i].contains(RelacionCantidad.mas.toString()) || w[i].contains(RelacionCantidad.mayor.toString()) || 
	        		  w[i].contains(RelacionCantidad.superior.toString()) || w[i].contains(">")){
	             encontrado=true;
	             return ">";
	         	}
	         else if (w[i].contains(RelacionCantidad.menos.toString()) || w[i].contains(RelacionCantidad.menor.toString()) || 
	        		 w[i].contains(RelacionCantidad.inferior.toString()) || w[i].contains("<")){
	             encontrado=true;
	             return "<";
	         	}
	        i++;
	        }
	    return "";
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

