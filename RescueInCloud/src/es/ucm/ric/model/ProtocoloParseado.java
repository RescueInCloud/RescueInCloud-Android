package es.ucm.ric.model;

import java.util.ArrayList;

import es.ucm.ric.parser.TextInterpreter;

public class ProtocoloParseado extends Protocolo{
	//las cajas que forman el protocolo ya parseadas
	/*La clase TextParser devuelve un arraylist de TextInterpreter, sera con lo que trabajemos*/
	private ArrayList<TextInterpreter> cajasParseadas; 

	
	public ProtocoloParseado(ArrayList<TextInterpreter> cajasParseadas) {
		super();
		this.cajasParseadas = cajasParseadas;
	}

	public ProtocoloParseado(ArrayList<TextInterpreter> cajasParseadas,
			ArrayList<CajasHijos> hijos) {
		super();
		this.cajasParseadas = cajasParseadas;

	}
	
	public ArrayList<TextInterpreter> getCajasParseadas() {
		return cajasParseadas;
	}
	

	
}
