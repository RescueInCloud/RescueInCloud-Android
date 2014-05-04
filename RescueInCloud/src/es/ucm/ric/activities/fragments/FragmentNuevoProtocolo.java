package es.ucm.ric.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.model.ProtocoloParseado;
import es.ucm.ric.model.TuplaParseada;
import es.ucm.ric.parser.TextInterpreter;
import es.ucm.ric.parser.TextParser;

public class FragmentNuevoProtocolo extends Fragment{
	
	private ProtocoloParseado pp;
	private ViewGroup contenedor;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_protocolo, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
		
		contenedor = (ViewGroup) getActivity().findViewById(R.id.contenedor);

		Protocolo p = new ProtocoloDAO().get("prueba1");
		TextParser tp = new TextParser();
		
		ArrayList<TextInterpreter> cajas = tp.parseCaja(p.cajas_toString());
		//hasta aqui correctamente
		pp = new ProtocoloParseado(cajas,p);
		TextInterpreter cajaelegida = pp.getCajaParseada(0);
		
		/**/
		/*consulta por el id de una caja si es de decision o de texto*/
		boolean b = pp.esCajaDecision(0);
		
		/*devuelve toodos los hijos de una caja, metiendo su id. En la clase TuplaParseada
		 * está la caja hijo ya parseada (TextInterpreter) y su relación 
		 */
		ArrayList<TuplaParseada> t= pp.getHijosParseados(0);
		/*Idem pero devolviendo s—lo los de si o no o normal por separado, devuelven null si no tienen esos hijos*/
		TextInterpreter tno=pp.getHijoParseadoNO(0);
		ArrayList<TextInterpreter> tnormal=pp.getHijoParseadoNORMAL(0);
		TextInterpreter tsi=pp.getHijoParseadoSI(0);
				
		
		//cajaelegida.encontrarRelacionCantidad(cadena, valor)
		
		addItem(cajaelegida);
		
	}
	
	private void addItem(final TextInterpreter caja) {
		
		String texto = caja.getContenido();
		boolean decision = pp.esCajaDecision(caja.getId());
		
		// Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
                R.layout.protocolo_item, contenedor, false);
        
        TextView contenido = (TextView) newView.findViewById(R.id.contenido);
        Button buttonSi = (Button) newView.findViewById(R.id.buttonSi);
        Button buttonNo = (Button) newView.findViewById(R.id.buttonNo);
        Button buttonContinuar = (Button) newView.findViewById(R.id.buttonContinuar);
        
        contenido.setText(texto);
		
		if(decision){
			
			buttonSi.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	
	            	Log.d("cajas", "id: "+ caja.getId());
	            	TextInterpreter hijoSi = pp.getHijoParseadoSI(caja.getId());
	            	
	            	
	            	if(hijoSi!=null){
	            		Log.d("cajas", "hijo_si: "+ hijoSi.getId());
	   	            	addItem(hijoSi);
	            	}
	            	else{
	            		Toast.makeText(FragmentNuevoProtocolo.this.getActivity(), "Protocolo finalizado", Toast.LENGTH_SHORT).show();
	            	}
	            }
	        });
			
			
			buttonNo.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	
	            	Log.d("cajas", "id: "+ caja.getId());
	            	TextInterpreter hijoNo = pp.getHijoParseadoNO(caja.getId());
	            	
	            	
	            	if(hijoNo!=null){
	            		Log.d("cajas", "hijo_no: "+ hijoNo.getId());
	            		addItem(hijoNo);
	            	}
	            	else{
	            		Toast.makeText(FragmentNuevoProtocolo.this.getActivity(), "Protocolo finalizado", Toast.LENGTH_SHORT).show();
	            	}
	            }
	        });
			
			buttonContinuar.setVisibility(View.INVISIBLE);
		}
		else{
			buttonNo.setVisibility(View.INVISIBLE);
			buttonSi.setVisibility(View.INVISIBLE);
			
			buttonContinuar.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	/**
	            	 * Por quŽ se devuelve una lista de hijos normales?
	            	 * No deber’a ser s—lo un hijo?
	            	 */
	            	Log.d("cajas", "id: "+ caja.getId());
	            	ArrayList<TextInterpreter> tnormal = pp.getHijoParseadoNORMAL(caja.getId());
	            	
	            	if(tnormal!=null && !tnormal.isEmpty()){
	            		Log.d("cajas", "hijo_nada: "+ tnormal.get(0));
	            		addItem(tnormal.get(0));
	            	}
	            	else{
	            		Toast.makeText(FragmentNuevoProtocolo.this.getActivity(), "Protocolo finalizado", Toast.LENGTH_SHORT).show();
	            	}
	           
	            }
	        });
		}
        
        // Because mContainerView has android:animateLayoutChanges set to true,
        // adding this view is automatically animated.
        //contenedor.addView(newView,0);
        contenedor.addView(newView);
    }


}
