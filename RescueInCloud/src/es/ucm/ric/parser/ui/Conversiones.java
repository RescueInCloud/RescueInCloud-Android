package es.ucm.ric.parser.ui;

import java.util.ArrayList;

import android.R.mipmap;

public class Conversiones {
	
	private float cantidad;
	private String base;
	public ArrayList<SFuncion> stringsConversiones;
	public ArrayList<IFuncion> funcionesConversiones;
	
	/**
	 * Ejemplo de uso
	 */
	public Conversiones(){
		funcionesConversiones = new ArrayList<IFuncion>();
		stringsConversiones=new ArrayList<SFuncion>();
		setFunciones();
		setStringsConversiones();
		
	}

	private void setStringsConversiones() {
		SFuncion K=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				// TODO Auto-generated method stub
				return unidad.replace(unidad.charAt(0),'K');
			}
		};
		SFuncion H=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				// TODO Auto-generated method stub
				return unidad.replace(unidad.charAt(0),'H');
			}
		};
		SFuncion D=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				// TODO Auto-generated method stub
				return unidad.replace(unidad.charAt(0),'D');
				
				
			}
		};
		SFuncion B=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				String aux="";
				for(int i=1;i<unidad.length();i++){
					aux +=unidad.charAt(i);
				}
				return aux;
			}
		};
		SFuncion d=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				// TODO Auto-generated method stub
				return unidad.replace(unidad.charAt(0),'d');
			}
		};
		SFuncion c=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				// TODO Auto-generated method stub
				return unidad.replace(unidad.charAt(0),'c');
			}
		};
		SFuncion m=new SFuncion() {
			
			@Override
			public String Sformula(String unidad) {
				return unidad.replace(unidad.charAt(0),'m');
			}
		};
		
		stringsConversiones.add(K);
		stringsConversiones.add(H);
		stringsConversiones.add(D);
		stringsConversiones.add(B);
		stringsConversiones.add(d);
		stringsConversiones.add(c);
		stringsConversiones.add(m);
		
	}

	public void setFunciones(){	
		IFuncion miFuncion_x10= new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad* 10;
				return resultado;
			}
		};
		
			IFuncion miFuncion_x10000= new IFuncion() {
						
						@Override
						public float formula(float cantidad) {
							float resultado = cantidad* 10000;
							return resultado;
						}
					};
					
			IFuncion miFuncion_x100000= new IFuncion() {
						
						@Override
						public float formula(float cantidad) {
							float resultado = cantidad* 100000;
							return resultado;
						}
					};
					
			IFuncion miFuncion_x1000000= new IFuncion() {
						
						@Override
						public float formula(float cantidad) {
							float resultado = cantidad* 1000000;
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
		IFuncion miFuncion_div10000 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad/ 10000;
				return resultado;
			}
		};
		IFuncion miFuncion_div100000 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad/ 100000;
				return resultado;
			}
		};
		IFuncion miFuncion_div1000000 = new IFuncion() {
			
			@Override
			public float formula(float cantidad) {
				float resultado = cantidad/ 1000000;
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

		funcionesConversiones.add(miFuncion_x10);
		funcionesConversiones.add(miFuncion_x100);
		funcionesConversiones.add(miFuncion_x1000);
		funcionesConversiones.add(miFuncion_x10000);
		funcionesConversiones.add(miFuncion_x100000);
		funcionesConversiones.add(miFuncion_x1000000);
		funcionesConversiones.add(miFuncion_div10);
		funcionesConversiones.add(miFuncion_div100);	
		funcionesConversiones.add(miFuncion_div1000);	
		funcionesConversiones.add(miFuncion_div10000);		
		funcionesConversiones.add(miFuncion_div100000);		
		funcionesConversiones.add(miFuncion_div1000000);
		
	}
	}
	


