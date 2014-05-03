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
import es.ucm.ric.model.TuplaParseada;
import es.ucm.ric.parser.Number;
import es.ucm.ric.parser.NumberUnit;
import es.ucm.ric.parser.Text;
import es.ucm.ric.parser.TextInterpreter;
import es.ucm.ric.parser.TextParser;

public class FragmentNuevoProtocolo extends Fragment{
	
	private ViewGroup contenedor;
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
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
		ProtocoloParseado pp = new ProtocoloParseado(cajas,p);
		
		/**/
		/*consulta por el id de una caja si es de decision o de texto*/
		boolean b = pp.esCajaDecision(0);
		/*devuelve toodos los hijos de una caja, metiendo su id. En la clase TuplaParseada
		 * está la caja hijo ya parseada (TextInterpreter) y su relación 
		 */
		ArrayList<TuplaParseada> t= pp.getHijosParseados(0);
		/*Idem pero devolviendo sólo los de si o no o normal por separado, devuelven null si no tienen esos hijos*/
		TextInterpreter tno=pp.getHijoParseadoNO(0);
		ArrayList<TextInterpreter> tnormal=pp.getHijoParseadoNORMAL(0);
		TextInterpreter tsi=pp.getHijoParseadoSI(0);
		/**/
		
		/**/TextInterpreter cajaelegida=null;
		for (TextInterpreter caja : cajas) {
			if(caja.getId()==0){
				cajaelegida = caja;
				break;
			}
			
		}
		
		
		//cajaelegida.encontrarRelacionCantidad(cadena, valor)
		
		addItem(cajaelegida.getContenido());
		
	}
	
	private void addItem(String texto) {
        // Instantiate a new "row" view.
        final ViewGroup newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
                R.layout.protocolo_item, contenedor, false);

        // Set the text in the new row to a random country.
        ((TextView) newView.findViewById(R.id.contenido)).setText(texto);

        // Set a click listener for the "X" button in the row that will remove the row.
        newView.findViewById(R.id.buttonSi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove the row from its parent (the container view).
                // Because mContainerView has android:animateLayoutChanges set to true,
                // this removal is automatically animated.
                //contenedor.removeView(newView);

            }
        });

        newView.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               

            }
        });
        
        newView.findViewById(R.id.buttonContinuar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
            	addItem("lalala");
            }
        });
        // Because mContainerView has android:animateLayoutChanges set to true,
        // adding this view is automatically animated.
        //contenedor.addView(newView,0);
        contenedor.addView(newView);
    }


}
