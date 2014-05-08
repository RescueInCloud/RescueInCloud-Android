package es.ucm.ric.parser.ui;

import java.util.ArrayList;

import android.R.mipmap;

public class Conversiones {
	
	private float cantidad;
	private String base;
	private ArrayList<String> posiblesConversiones;
	private ArrayList<IFuncion> funcionesConversiones;
	
	/**
	 * Ejemplo de uso
	 */
	public Conversiones(){
		funcionesConversiones = new ArrayList<IFuncion>();
		funcionesConversiones.add(miFuncion_x10);
		funcionesConversiones.add(miFuncion_div10);
		funcionesConversiones.add(miFuncion_div100);
		funcionesConversiones.add(miFuncion_x1000);	
		funcionesConversiones.add(miFuncion_div1000);
		
	}

		
		IFuncion miFuncion_x10= new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad* 10;
				return resultado;
			}
		};
		
		IFuncion miFuncion_div10 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad/ 10;
				return resultado;
			}
		};
		IFuncion miFuncion_x100 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad* 100;
				return resultado;
			}
		};
		
		IFuncion miFuncion_div100 = new IFuncion() {
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad/ 100;
				return resultado;
			}
		};

		IFuncion miFuncion_x1000 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad* 1000;
				return resultado;
			}
		};
		IFuncion miFuncion_div1000 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad/ 1000;
				return resultado;
			}
		};
		
		
		float x = 30f;
		float convertido = funcionesConversiones.get(3).formula(x);
		
	}
	


