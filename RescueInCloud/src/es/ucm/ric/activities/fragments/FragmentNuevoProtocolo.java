package es.ucm.ric.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.model.ProtocoloParseado;
import es.ucm.ric.parser.Number;
import es.ucm.ric.parser.NumberUnit;
import es.ucm.ric.parser.Text;
import es.ucm.ric.parser.TextInterpreter;
import es.ucm.ric.parser.TextParser;

public class FragmentNuevoProtocolo extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.nuevo_protocolo, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
		Protocolo p = new ProtocoloDAO().get("prueba1");
		TextParser tp = new TextParser();
		ArrayList<TextInterpreter> cajas = tp.parseCaja(p.cajas_toString());
		//hasta aqui correctamente
		ProtocoloParseado pp = new ProtocoloParseado(cajas,p);
		TextInterpreter cajaelegida=null;
		for (TextInterpreter caja : cajas) {
			
			if(caja instanceof Number){
				Number n = (Number)caja;
				int id = n.getId();
				if(id==0)
				cajaelegida = caja;
			}
			else if(caja instanceof NumberUnit){
				NumberUnit n = (NumberUnit)caja;
				int id = n.getId();
				if(id==0)
				cajaelegida = caja;
			}
			else if(caja instanceof Text){
				Text n = (Text)caja;
				int id = n.getId();
				if(id==0)
				cajaelegida = caja;
			}
		}
		
		TextView contenidoTV = (TextView)getActivity().findViewById(R.id.contenido);
		contenidoTV.setText(cajaelegida.getContenido());
		
	}

}
