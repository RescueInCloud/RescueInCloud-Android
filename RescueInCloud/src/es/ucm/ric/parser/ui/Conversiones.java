package es.ucm.ric.parser.ui;

import java.util.ArrayList;

public class Conversiones {
	
	private float cantidad;
	private String base;
	private ArrayList<String> posiblesConversiones;
	private ArrayList<IFuncion> funcionesConversiones;
	
	/**
	 * Ejemplo de uso
	 */
	public void main(){
		
		IFuncion miFuncion = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad* 1000;
				return resultado;
			}
		};
		
		
		
		float x = 30f;
		float convertido = funcionesConversiones.get(3).formula(x);
		
	}
	

}
