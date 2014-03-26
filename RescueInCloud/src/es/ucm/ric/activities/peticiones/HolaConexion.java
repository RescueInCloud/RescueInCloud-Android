package es.ucm.ric.activities.peticiones;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import es.ucm.ric.tools.ConnectionListener;
import es.ucm.ric.tools.HttpPostConnector;


public class HolaConexion implements ConnectionListener{
	
	private HttpPostConnector post;
	private Context context;
	private String mensaje;
	
	public HolaConexion(Context context) {
		this.context = context;
		post = new HttpPostConnector();
	}
	
	@Override
	public boolean inBackground(String... params) {
		
		ArrayList<NameValuePair> postParametersToSend = new ArrayList<NameValuePair>();

		postParametersToSend.add(new BasicNameValuePair("email", params[0]));


		// realizamos una peticion y como respuesta obtenes un array JSON
		JSONArray jdata = post.getserverdata(postParametersToSend, HttpPostConnector.URL_HOLA);

		// si lo que obtuvimos no es null, es decir, hay respuesta v�lida
				if (jdata != null && jdata.length() > 0) {

					try {
						JSONObject json_data = jdata.getJSONObject(0);
						String codeFromServer = json_data.getString("code");
						//String messageFromServer = json_data.getString("message");
						
						if("303".equals(codeFromServer)){

					       
					        mensaje = json_data.getString("message");
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
		Toast.makeText(this.context,mensaje,Toast.LENGTH_LONG).show();
		
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
