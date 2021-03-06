package es.ucm.ric.peticiones;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import es.ucm.ric.activities.listeners.Listener;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.tools.ConnectionListener;
import es.ucm.ric.tools.HttpPostConnector;


public class RecuperarProtocolosConexion implements ConnectionListener{
	
	private HttpPostConnector post;
	private Context context;
	private String mensaje;
	private Listener<ArrayList<Protocolo>> listener;
	private ArrayList<Protocolo> listdata;
	
	public RecuperarProtocolosConexion(Context context) {
		this.context = context;
		post = new HttpPostConnector();
	}
	
	public void setListener(Listener<ArrayList<Protocolo>> listener){
		this.listener = listener;
	}
	
	@Override
	public boolean inBackground(String... params) {
		
		ArrayList<NameValuePair> postParametersToSend = new ArrayList<NameValuePair>();

		postParametersToSend.add(new BasicNameValuePair("email", params[0]));
		postParametersToSend.add(new BasicNameValuePair("password", params[1]));


		// realizamos una peticion y como respuesta obtenes un array JSON
		JSONArray jdata = post.getserverdata(postParametersToSend, HttpPostConnector.URL_LISTA_FARMACOS);

		// si lo que obtuvimos no es null, es decir, hay respuesta v�lida
				if (jdata != null && jdata.length() > 0) {

					try {
						JSONObject json_data = jdata.getJSONObject(0);
						String codeFromServer = json_data.getString("code");
						//String messageFromServer = json_data.getString("message");
						
						if("200".equals(codeFromServer)){

					       
					        mensaje = json_data.getString("message");
					        
					        JSONArray jArray = new JSONArray(mensaje);
					        listdata = new ArrayList<Protocolo>();     
					        if (jArray != null) { 
					        	for (int i=0;i<jArray.length();i++){ 
					        		JSONObject fila = jArray.getJSONObject(i);
					        		
					           } 
					        } 
					        return true;
						}
						else{
							//Toast.makeText(this, "Error desconocido.", Toast.LENGTH_SHORT).show();
						}
					

					} catch (JSONException e) {
						
						return false;
					}
					
					
					return true;
				} else { // json obtenido invalido verificar parte WEB.
					Log.e("JSON  ", "ERROR");
					
					return false;
				}

		
		

	}
	
	
	@Override
	public boolean validateDataBeforeConnection(String... params) {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public void invalidInputData() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void afterGoodConnection() {
		listener.actualizar(listdata);
		
	}
	@Override
	public void afterErrorConnection() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return this.context;
	}

}
