package es.ucm.ric.activities.fragments;

import java.util.ArrayList;
import java.util.Locale;

import android.speech.tts.TextToSpeech;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.model.ProtocoloParseado;
import es.ucm.ric.model.TuplaParseada;
import es.ucm.ric.parser.NumberUnit;
import es.ucm.ric.parser.Text;
import es.ucm.ric.parser.TextInterpreter;
import es.ucm.ric.parser.TextParser;
import es.ucm.ric.parser.ui.Conversiones;
import es.ucm.ric.parser.ui.IFuncion;
import es.ucm.ric.parser.ui.SFuncion;

public class FragmentNuevoProtocolo extends Fragment implements TextToSpeech.OnInitListener{
	
	private ProtocoloParseado pp;
	private ViewGroup contenedor;
	private Spinner spinner;
	private ArrayAdapter<String> LTRadapter;
	TextInterpreter caja;
	private TextToSpeech tts;
	TextView contenido;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_protocolo, container, false);
		tts = new TextToSpeech(getActivity(), this);
		 
		 /*
		 Button miBoton = (Button)v.findViewById(R.id.miBoton);
	        miBoton.setOnClickListener(new View.OnClickListener() {

	            @Override
	            public void onClick(View v) {
	                Intent intent = new Intent(getActivity(), OtraActivity.class);
	                startActivity(intent);
	            }
	        });
			*/
		return v;
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
		
		addItem(cajaelegida);
		
	}
	
	private void addItem(final TextInterpreter caja) {
		
		
		
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
			
			String [] values = {"Cambiar Unidad","Kilo","Hecta","Deca","base","deci","centi","mili",};
		    spinner = (Spinner) newView.findViewById(R.id.spinnerUnit);
		    LTRadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
		    LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
		    spinner.setAdapter(LTRadapter);
		}
        
        contenido = (TextView) newView.findViewById(R.id.contenido);
        final Button buttonSi = (Button) newView.findViewById(R.id.buttonSi);
        final Button buttonNo = (Button) newView.findViewById(R.id.buttonNo);
        final Button buttonContinuar = (Button) newView.findViewById(R.id.buttonContinuar);
        

        final Button buttonNumberOk = (Button) newView.findViewById(R.id.buttonNumberOk);
        
        
        TextView textValor = (TextView)newView.findViewById(R.id.textNUmberUnitValor);
        TextView textUnidad = (TextView)newView.findViewById(R.id.textNUmberUnitUnidad);
        
        if (caja instanceof es.ucm.ric.parser.NumberUnit){
        	
        	float valorCaja = ((es.ucm.ric.parser.NumberUnit)caja).getValor();
        	
        	String unidad = ((es.ucm.ric.parser.NumberUnit)caja).getUnidad();
        	textUnidad.setText(unidad);
        	textValor.setText(""+valorCaja);
        	if (decision){
        		 TextView textUnidad1 = (TextView)newView.findViewById(R.id.textNUmberUnitUnidad1);
        	}
        }


        final ViewGroup newViewDEF = newView;
        
        final EditText numE = (EditText) newViewDEF.findViewById(R.id.editTextNumberDecimal);
        final TextView introducirNum = (TextView) newViewDEF.findViewById(R.id.textView1);
        
        String texto="";
        if (caja instanceof es.ucm.ric.parser.NumberUnit){
        	texto=((NumberUnit)caja).getContenido() +" " + ((NumberUnit)caja).gettValor()+" "+((NumberUnit)caja).gettUnidad()+" " + ((NumberUnit)caja).getTextoFin();
        }
        else texto=caja.getContenido();
        
        contenido.setText(texto);
        speakOut();
        if (spinner !=null){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Object item = parent.getItemAtPosition(pos);
            	String a="";
            	if (pos==1){
            		a="1"+(spinner.getSelectedItem().toString());
            	}
            	else {
            	 a=spinner.getSelectedItem().toString();
            	//FUNCIONA-> hace la funcion de cambiar unidad y cambiar la informacion de la caja
            	
            	
            	}
            	if (pos!=0)
            		unidadSeleccionada( pos,caja);
            	
            	
        		Toast.makeText(FragmentNuevoProtocolo.this.getActivity(),a, Toast.LENGTH_SHORT).show();

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
		}
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
			
			buttonContinuar.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View view) {

	            	
	            	
	            	//buttonContinuar.setEnabled(false);
	            	desactivarRoundedButton(buttonContinuar);
	            	
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
	
	private void unidadSeleccionada(int pos,TextInterpreter caja){
		String unidad=((es.ucm.ric.parser.NumberUnit)caja).getUnidad();
		char prefijo=unidad.charAt(0);
		/*depende del valor que sea el primer chat estoy en un rango de unidad y ejecuto una función u otra*/
		boolean anyadir=false;
		if (prefijo == 'k' || prefijo == 'K'){
			anyadir=cambioUnidadesK(pos,caja);
		}
		else if (prefijo == 'h' || prefijo == 'H'){
			anyadir=cambioUnidadesH(pos,caja);
		}
		else if (prefijo == 'D'){
			anyadir=cambioUnidadesD(pos,caja);
		}
		else if (prefijo == 'd'){
			anyadir=cambioUnidadesd(pos,caja);
		}
		else if (prefijo == 'c'){
			anyadir=cambioUnidadesc(pos,caja);
		}
		else if (prefijo == 'm'){
			anyadir=cambioUnidadesm(pos,caja);
		}
		else anyadir=cambioUnidadesB(pos,caja);
		
		
		if (anyadir){
			addItem(caja);
		}
		
	}
	
	
	private boolean cambioUnidadesH(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: funcion=conversiones.funcionesConversiones.get(6);///10
					sfuncion=conversiones.stringsConversiones.get(0);
					break;
			//H
			case 2: return false;
			//D
			case 3: funcion=conversiones.funcionesConversiones.get(0);///x100
					sfuncion=conversiones.stringsConversiones.get(2);
					break;
			//ba
			case 4: funcion=conversiones.funcionesConversiones.get(1);//x1000
					sfuncion=conversiones.stringsConversiones.get(3);
					break;
			//d
			case 5: funcion=conversiones.funcionesConversiones.get(2);
					sfuncion=conversiones.stringsConversiones.get(4);
					break;
			case 6: funcion=conversiones.funcionesConversiones.get(3);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			case 7: funcion=conversiones.funcionesConversiones.get(4);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	
	
	private boolean cambioUnidadesD(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: funcion=conversiones.funcionesConversiones.get(7);///100
					sfuncion=conversiones.stringsConversiones.get(0);
					break;
			//H
			case 2: funcion=conversiones.funcionesConversiones.get(6);///10
					sfuncion=conversiones.stringsConversiones.get(1);
					break;
			//D
			case 3: return false;
			//ba
			case 4: funcion=conversiones.funcionesConversiones.get(0);//x1000
					sfuncion=conversiones.stringsConversiones.get(3);
					break;
			//d
			case 5: funcion=conversiones.funcionesConversiones.get(1);
					sfuncion=conversiones.stringsConversiones.get(4);
					break;
			case 6: funcion=conversiones.funcionesConversiones.get(2);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			case 7: funcion=conversiones.funcionesConversiones.get(3);
					sfuncion=conversiones.stringsConversiones.get(6);
					break;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	
	private boolean cambioUnidadesB(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: funcion=conversiones.funcionesConversiones.get(8);//x1000
					sfuncion=conversiones.stringsConversiones.get(0); break;
			//H
			case 2: funcion=conversiones.funcionesConversiones.get(7);//x10
					sfuncion=conversiones.stringsConversiones.get(1);
					break;
			//D
			case 3: funcion=conversiones.funcionesConversiones.get(6);///x100
					sfuncion=conversiones.stringsConversiones.get(2);
					break;
			//ba
			case 4:return false;
			//d
			case 5: funcion=conversiones.funcionesConversiones.get(0);
					sfuncion=conversiones.stringsConversiones.get(4);
					break;
			case 6: funcion=conversiones.funcionesConversiones.get(1);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			case 7: funcion=conversiones.funcionesConversiones.get(2);
					sfuncion=conversiones.stringsConversiones.get(6);
					break;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	
	private boolean cambioUnidadesd(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: funcion=conversiones.funcionesConversiones.get(9);
					sfuncion=conversiones.stringsConversiones.get(0);break;
			//H
			case 2: funcion=conversiones.funcionesConversiones.get(8);//x10
					sfuncion=conversiones.stringsConversiones.get(1);
					break;
			//D
			case 3: funcion=conversiones.funcionesConversiones.get(7);///x100
					sfuncion=conversiones.stringsConversiones.get(2);
					break;
			//ba
			case 4: funcion=conversiones.funcionesConversiones.get(6);//x1000
					sfuncion=conversiones.stringsConversiones.get(3);
					break;
			//d
			case 5: return false;
			case 6: funcion=conversiones.funcionesConversiones.get(0);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			case 7: funcion=conversiones.funcionesConversiones.get(1);
					sfuncion=conversiones.stringsConversiones.get(6);
					break;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	
	private boolean cambioUnidadesc(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: funcion=conversiones.funcionesConversiones.get(10);
					sfuncion=conversiones.stringsConversiones.get(0);
					break;
			//H
			case 2: funcion=conversiones.funcionesConversiones.get(9);//x10
					sfuncion=conversiones.stringsConversiones.get(1);
					break;
			//D
			case 3: funcion=conversiones.funcionesConversiones.get(8);///x100
					sfuncion=conversiones.stringsConversiones.get(2);
					break;
			//ba
			case 4: funcion=conversiones.funcionesConversiones.get(7);//x1000
					sfuncion=conversiones.stringsConversiones.get(3);
					break;
			//d
			case 5: funcion=conversiones.funcionesConversiones.get(6);
					sfuncion=conversiones.stringsConversiones.get(4);
					break;
			case 6: return false;
			
			case 7: funcion=conversiones.funcionesConversiones.get(0);
					sfuncion=conversiones.stringsConversiones.get(6);
					break;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	
	private boolean cambioUnidadesm(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: funcion=conversiones.funcionesConversiones.get(11);
					sfuncion=conversiones.stringsConversiones.get(0);
					break;
			//H
			case 2: funcion=conversiones.funcionesConversiones.get(10);//x10
					sfuncion=conversiones.stringsConversiones.get(1);
					break;
			//D
			case 3: funcion=conversiones.funcionesConversiones.get(9);///x100
					sfuncion=conversiones.stringsConversiones.get(2);
					break;
			//ba
			case 4: funcion=conversiones.funcionesConversiones.get(8);//x1000
					sfuncion=conversiones.stringsConversiones.get(3);
					break;
			//d
			case 5: funcion=conversiones.funcionesConversiones.get(7);
					sfuncion=conversiones.stringsConversiones.get(4);
					break;
			case 6: funcion=conversiones.funcionesConversiones.get(6);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			case 7: return false;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	
	private boolean cambioUnidadesK(int pos,TextInterpreter caja){
		Conversiones conversiones=new Conversiones();
		IFuncion funcion = null;
		SFuncion sfuncion=null;
		switch (pos){
			//K
			case 1: return false;
			//H
			case 2: funcion=conversiones.funcionesConversiones.get(0);//x10
					sfuncion=conversiones.stringsConversiones.get(1);
					break;
			//D
			case 3: funcion=conversiones.funcionesConversiones.get(1);///x100
					sfuncion=conversiones.stringsConversiones.get(2);
					break;
			//ba
			case 4: funcion=conversiones.funcionesConversiones.get(2);//x1000
					sfuncion=conversiones.stringsConversiones.get(3);
					break;
			//d
			case 5: funcion=conversiones.funcionesConversiones.get(3);
					sfuncion=conversiones.stringsConversiones.get(4);
					break;
			case 6: funcion=conversiones.funcionesConversiones.get(4);
					sfuncion=conversiones.stringsConversiones.get(5);
					break;
			case 7: funcion=conversiones.funcionesConversiones.get(5);
					sfuncion=conversiones.stringsConversiones.get(6);
					break;
			default: break;
		
		}
		actualizarContenido(funcion,sfuncion,caja);
		return true;
	}
	private void actualizarContenido(IFuncion funcion,SFuncion sfuncion, TextInterpreter caja){
		if (funcion!=null){
			float nuevoValor=0;
			String nuevaUnidad="";
			float valor=((NumberUnit)caja).getValor();
			nuevoValor=funcion.formula(valor);
			
			String unidad = ((NumberUnit)caja).getUnidad();
			nuevaUnidad=sfuncion.Sformula(unidad);
			
			((NumberUnit) caja).settUnidad(nuevaUnidad);
			((NumberUnit) caja).settValor(nuevoValor+"");
			
			
			((NumberUnit)caja).setValor(nuevoValor);

			((NumberUnit)caja).setUnidad(nuevaUnidad);			 
		}
	}


	@Override
	public void onInit(int status) {
		 if (status == TextToSpeech.SUCCESS) {
			 
	            int result = tts.setLanguage(new Locale("spa"));
	 
	            if (result == TextToSpeech.LANG_MISSING_DATA
	                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	                Log.e("TTS", "This Language is not supported");
	            } else {
	                //btnSpeak.setEnabled(true);
	                speakOut();
	            }
	 
	        } else {
	            Log.e("TTS", "Initilization Failed!");
	        }
	 
	    }
	 
	    private void speakOut() {
	 
	        String text = contenido.getText().toString();
	 
	        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	    }
}
