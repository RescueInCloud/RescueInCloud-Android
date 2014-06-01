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
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.tools.ConnectionListener;
import es.ucm.ric.tools.HttpPostConnector;


public class RecuperarTodoConexion implements ConnectionListener{
	
	private HttpPostConnector post;
	private Context context;
	private String mensaje;
	private Listener<Boolean> listener;
	private ArrayList<Farmaco> listdata;
	
	public RecuperarTodoConexion(Context context) {
		this.context = context;
		post = new HttpPostConnector();
	}
	
	public void setListener(Listener<Boolean> listener){
		this.listener = listener;
	}
	
	@Override
	public boolean inBackground(String... params) {
		
		ArrayList<NameValuePair> postParametersToSend = new ArrayList<NameValuePair>();

		postParametersToSend.add(new BasicNameValuePair("email", params[0]));
		postParametersToSend.add(new BasicNameValuePair("password", params[1]));
		postParametersToSend.add(new BasicNameValuePair("code", "bd5e6b731c8bdf0b911bea5d5279f058"));


		// realizamos una peticion y como respuesta obtenes un array JSON
		JSONArray jdata = post.getserverdata(postParametersToSend, HttpPostConnector.URL_LOGIN_ANDROID);

		// si lo que obtuvimos no es null, es decir, hay respuesta v�lida
				if (jdata != null && jdata.length() > 0) {

					try {
						JSONObject json_data = jdata.getJSONObject(0);
						String codeFromServer = json_data.getString("code");
						//String messageFromServer = json_data.getString("message");
						
						if("200".equals(codeFromServer)){

					       
					        String protocolos = json_data.getString("protocolos");
					        //JSONArray ja_protocolos = new JSONArray("["+protocolos+"]");
					        boolean ok_protocolos = Operaciones.syncProtocolos(protocolos);
					        
					        String farmacos = json_data.getString("farmacos");
					        //JSONArray ja_farmacos = new JSONArray("["+farmacos+"]");
					        boolean ok_farmacos = Operaciones.syncFarmacos(farmacos);
					        
					        String notas = json_data.getString("notas");
					        JSONArray ja_notas = new JSONArray("["+notas+"]");
					        
					       
					        return ok_protocolos && ok_farmacos;
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
		listener.actualizar(true);
		
	}
	@Override
	public void afterErrorConnection() {
		listener.actualizar(false);
		
	}
	@Override
	public Context getContext() {
		// TODO Auto-generated method stub
		return this.context;
	}

}
