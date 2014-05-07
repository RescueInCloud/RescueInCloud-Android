package es.ucm.ric.activities.fragments;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.model.ProtocoloParseado;
import es.ucm.ric.model.TuplaParseada;
import es.ucm.ric.parser.Text;
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

		String id = getArguments().getString("ID");
		
		Protocolo p = new ProtocoloDAO().get(id);
		TextParser tp = new TextParser();
		
		ArrayList<TextInterpreter> cajas = tp.parseCaja(p.cajas_toString());
		//hasta aqui correctamente
		pp = new ProtocoloParseado(cajas,p);
		TextInterpreter cajaelegida = pp.getCajaParseada(0);
//		
//		/**/
//		/*consulta por el id de una caja si es de decision o de texto*/
//		boolean b = pp.esCajaDecision(0);
//		
//		/*devuelve toodos los hijos de una caja, metiendo su id. En la clase TuplaParseada
//		 * está la caja hijo ya parseada (TextInterpreter) y su relación 
//		 */
//		ArrayList<TuplaParseada> t= pp.getHijosParseados(0);
//		/*Idem pero devolviendo s—lo los de si o no o normal por separado, devuelven null si no tienen esos hijos*/
//		TextInterpreter tno=pp.getHijoParseadoNO(0);
//		ArrayList<TextInterpreter> tnormal=pp.getHijoParseadoNORMAL(0);
//		TextInterpreter tsi=pp.getHijoParseadoSI(0);
				
		
		//cajaelegida.encontrarRelacionCantidad(cadena, valor)
		
		addItem(cajaelegida);
		
	}
	
	private void addItem(final TextInterpreter caja) {
		
		String texto = caja.getContenido();
		boolean decision = pp.esCajaDecision(caja.getId());
		
		// Instantiate a new "row" view.
		  ViewGroup newView = null;
		if (caja instanceof es.ucm.ric.parser.Number){
			if (decision){
				newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
		                R.layout.protocolo_item_number_decision, contenedor, false);
			}
			else newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
		                R.layout.protocolo_item_number, contenedor, false);
		}
		else if (caja instanceof Text){
			 newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
		                R.layout.protocolo_item, contenedor, false);
		}
		else if (caja instanceof es.ucm.ric.parser.NumberUnit){
			if (decision){
				newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
		                R.layout.protocolo_item_number_unit_decision, contenedor, false);
			}
			else newView = (ViewGroup) LayoutInflater.from(getActivity()).inflate(
		                R.layout.protocolo_item_number_unit, contenedor, false);
		}
        
        TextView contenido = (TextView) newView.findViewById(R.id.contenido);
        final Button buttonSi = (Button) newView.findViewById(R.id.buttonSi);
        final Button buttonNo = (Button) newView.findViewById(R.id.buttonNo);
        final Button buttonContinuar = (Button) newView.findViewById(R.id.buttonContinuar);
        

        final Button buttonNumberOk = (Button) newView.findViewById(R.id.buttonNumberOk);
        
        final Button buttonNumberCambiarUnidad = (Button) newView.findViewById(R.id.buttonNumberUnitCambiarUnidad);
        final Button buttonNumberCambiarUnidadDefecto = (Button) newView.findViewById(R.id.buttonNumberUnitCambiarUnidadDefecto);
        
        
        TextView textValor = (TextView)newView.findViewById(R.id.textNUmberUnitValor);
        TextView textUnidad = (TextView)newView.findViewById(R.id.textNUmberUnitUnidad);

        if (caja instanceof es.ucm.ric.parser.NumberUnit){
        	double valor = ((es.ucm.ric.parser.NumberUnit)caja).getValor();
        	String unidad = ((es.ucm.ric.parser.NumberUnit)caja).getUnidad();
        	textUnidad.setText(unidad);
        	textValor.setText(""+valor);
        	if (decision){
        		 TextView textUnidad1 = (TextView)newView.findViewById(R.id.textNUmberUnitUnidad1);
        	}
        }


        final ViewGroup newViewDEF = newView;
        
        final EditText numE = (EditText) newViewDEF.findViewById(R.id.editTextNumberDecimal);
        final TextView introducirNum = (TextView) newViewDEF.findViewById(R.id.textView1);
        contenido.setText(texto);
		
        
		if(decision){
			if (buttonNumberOk != null && numE!= null){
				buttonNumberOk.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View view) {

		            	desactivarRoundedButton(buttonNumberOk);
		            	
		            	/*consultar la relacion cantidad de la caja con el numero introducido*/
		            	String rel=caja.getRelacionCantidad();
		            	if (rel == ">"){
		            		
		            		String num = numE.getText().toString();
		            		if (((caja instanceof es.ucm.ric.parser.Number) && (Integer.parseInt(num) > ((es.ucm.ric.parser.Number) caja).getValor()))
		            			|| ((caja instanceof es.ucm.ric.parser.NumberUnit) && (Integer.parseInt(num) > ((es.ucm.ric.parser.NumberUnit) caja).getValor()))){
			            			/*Si el num que mete es mayor que el que esta en la caja y la relacion es >, se cumple -> como si pulsara SI*/
			            			desactivarCircleButton(buttonNumberOk);
			    	            	buttonNo.setEnabled(false);
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
			            		else if ((caja instanceof es.ucm.ric.parser.Number) || (caja instanceof es.ucm.ric.parser.NumberUnit)){
				            			/*Si el num que mete es mayor que el que esta en la caja y la relacion no es >, no se cumple -> como si pulsara NO*/
				            			desactivarCircleButton(buttonNumberOk);
				    	            	buttonSi.setEnabled(false);
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
		            		}
		            	else if (rel == "<"){
		            		String num = numE.getText().toString();
		            		if (((caja instanceof es.ucm.ric.parser.Number) && (Integer.parseInt(num) < ((es.ucm.ric.parser.Number) caja).getValor()))
		            			|| ((caja instanceof es.ucm.ric.parser.NumberUnit) && (Integer.parseInt(num) < ((es.ucm.ric.parser.NumberUnit) caja).getValor()))){
			            			/*Si el num que mete es mayor que el que esta en la caja y la relacion es >, se cumple -> como si pulsara SI*/
			            			desactivarCircleButton(buttonNumberOk);
			    	            	buttonNo.setEnabled(false);
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
			            		else if ((caja instanceof es.ucm.ric.parser.Number) || (caja instanceof es.ucm.ric.parser.NumberUnit)){
				            			/*Si el num que mete es mayor que el que esta en la caja y la relacion no es >, no se cumple -> como si pulsara NO*/
				            			desactivarCircleButton(buttonNumberOk);
				    	            	buttonSi.setEnabled(false);
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
		            	}
					}	
				});
			}
			if (buttonNumberCambiarUnidad !=null){
				buttonNumberCambiarUnidad.setOnClickListener(new View.OnClickListener(){
					public void onClick(View view) {
						
					}
				}
			);			
			}
			if (buttonNumberCambiarUnidadDefecto !=null){
				buttonNumberCambiarUnidadDefecto.setOnClickListener(new View.OnClickListener(){
					public void onClick(View view) {
						
					}
				}
			);			
			}
			buttonSi.setOnClickListener(new View.OnClickListener() {
	
				@Override
	            public void onClick(View view) {
	            	
	            	desactivarCircleButton(buttonSi);
	            	buttonNo.setEnabled(false);
	            	
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
	            	
	            	desactivarCircleButton(buttonNo);
	            	buttonSi.setEnabled(false);
	            	
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
			TextView t=(TextView)newView .findViewById(R.id.textValorNumber);
			if (t!=null){
				t.setVisibility(View.INVISIBLE);
			}
			if (buttonNumberOk != null)
				buttonNumberOk.setVisibility(View.INVISIBLE);
			if (numE != null)
				numE.setVisibility(View.INVISIBLE);
			if (introducirNum != null)
				introducirNum.setVisibility(View.INVISIBLE);
			if (buttonNumberCambiarUnidad != null)
				buttonNumberCambiarUnidad.setVisibility(View.INVISIBLE);
			if (buttonNumberCambiarUnidadDefecto != null){
				buttonNumberCambiarUnidadDefecto.setOnClickListener(new View.OnClickListener() {
		            @Override
		            public void onClick(View view) {
		            
		           
		            }
		        });
			}
			buttonContinuar.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {
	            	
	            	//buttonContinuar.setEnabled(false);
	            	desactivarRoundedButton(buttonContinuar);
	            	
	            	/**
	            	 * Por quŽ se devuelve una lista de hijos normales?
	            	 * No deber’a ser s—lo un hijo?
	            	 * 
	            	 * 
	            	 * no, puede tener mas de un hijo normal, de hecho puede tener hijos de TODO tipo
	            	 */
	            	Log.d("cajas", "id: "+ caja.getId());
	            	//ArrayList<TextInterpreter> tnormal = pp.getHijoParseadoNORMAL(caja.getId());
	            	ArrayList<TuplaParseada> tnormal = pp.getHijosParseados(caja.getId());
	            	
	            	if(tnormal!=null && !tnormal.isEmpty()){
	            		Log.d("cajas", "hijo_nada: "+ tnormal.get(0));
	            		addItem(tnormal.get(0).cajaParseada);
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

	
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void desactivarCircleButton(Button boton){
		
		Resources resources = getResources();
		boton.setEnabled(false);
    	int sdk = android.os.Build.VERSION.SDK_INT;
    	if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
    		boton.setBackgroundDrawable(resources.getDrawable(R.drawable.circle_button_background_pressed));
    	} else {
    		boton.setBackground(resources.getDrawable(R.drawable.circle_button_background_pressed));
    	}
    	
    	boton.setTextColor(resources.getColor(R.color.color_primario));
	}

	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void desactivarRoundedButton(Button boton){
		
		Resources resources = getResources();
		boton.setEnabled(false);
    	int sdk = android.os.Build.VERSION.SDK_INT;
    	if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
    		boton.setBackgroundDrawable(resources.getDrawable(R.drawable.rounded_button_background_pressed));
    	} else {
    		boton.setBackground(resources.getDrawable(R.drawable.rounded_button_background_pressed));
    	}
    	
    	boton.setTextColor(resources.getColor(R.color.color_primario));
	}
}
