package es.ucm.ric.model;

import java.util.ArrayList;

import es.ucm.ric.parser.RelacionCantidadMayor;
import es.ucm.ric.parser.TextInterpreter;

public class ProtocoloParseado extends Protocolo{
	
	//las cajas que forman el protocolo ya parseadas
	/*La clase TextParser devuelve un arraylist de TextInterpreter, sera con lo que trabajemos*/
	private ArrayList<TextInterpreter> cajasParseadas; 
	
	
	public ProtocoloParseado(ArrayList<TextInterpreter> cajasParseadas, Protocolo protocolo) {
		super();
		this.cajasParseadas = cajasParseadas;
		super.cajas = protocolo.getCajas();
		super.hijos = protocolo.getHijos();
		super.id = protocolo.getId();
		super.nombre = protocolo.nombre;
		super.decision = protocolo.getDecision();
	}

	public ProtocoloParseado(ArrayList<TextInterpreter> cajasParseadas,
			ArrayList<CajasHijos> hijos) {
		super();
		this.cajasParseadas = cajasParseadas;

	}
	
	public ArrayList<TextInterpreter> getCajasParseadas() {
		return cajasParseadas;
	}
	
	/*devuelve los hijos de una caja y su relacion*/
	public ArrayList<TuplaParseada> getHijosParseados(int id){
		ArrayList<Tupla> hijosDeID = super.hijos.getHijos(id);
		if (hijosDeID == null){
			return null;
		}
		ArrayList<TuplaParseada> hijosDeIDparseados =new ArrayList<TuplaParseada>();
		for (Tupla t: hijosDeID){
			TuplaParseada tp = new TuplaParseada();
			tp.cajaParseada=getCajaParseada(t.id);
			tp.relacion=t.relacion;
			hijosDeIDparseados.add(tp);
		}
		return hijosDeIDparseados;
	}
	
	
	/*devuelve el hijo de una caja si su relacion es si=0*/
	public TextInterpreter getHijoParseadoSI(int id){
		ArrayList<Tupla> hijosDeID = super.hijos.getHijos(id);
		if (hijosDeID.size()==0)
			return null;
		for (Tupla t: hijosDeID){
			if (t.relacion==0){
				return getCajaParseada(t.id);
			}
		}
		return null;
	}
	
	/*devuelve el hijo de una caja si su relacion es no=1*/
	public TextInterpreter getHijoParseadoNO(int id){
		ArrayList<Tupla> hijosDeID = super.hijos.getHijos(id);
		if (hijosDeID.size()==0)
			return null;
		for (Tupla t: hijosDeID){
			if (t.relacion==1){
				return getCajaParseada(t.id);
			}
		}
		return null;
	}
	
	/*devuelve los hijos de una caja si su relacion es normal=2*/
	public ArrayList<TextInterpreter> getHijoParseadoNORMAL(int id){

		ArrayList<Tupla> hijosDeID = super.hijos.getHijos(id);
		
		//Se pregunta por hijosDeID porque no se peude hacer un foreach de null y da error
		if(hijosDeID==null)
			return null;
		
		ArrayList<TextInterpreter> hijosDeIDparseados = null;
		for (Tupla t: hijosDeID){
			if (t.relacion==2){
				if (hijosDeIDparseados != null)
					hijosDeIDparseados.add(getCajaParseada(t.id));
				else {
					hijosDeIDparseados=new ArrayList<TextInterpreter>();
					hijosDeIDparseados.add(getCajaParseada(t.id));
				}
			}
		}
		return hijosDeIDparseados;
	}
	
	
	public TextInterpreter getCajaParseada(int id){
		for( TextInterpreter t:cajasParseadas){
			if (t.getId()==id){
				return t;
			}
		}
		return null;
	}
	
	public boolean esCajaDecision(int id){
		for (int t:decision){
			if (t==id){
				return true;
			}
		}
		return false;
	}

	
}
