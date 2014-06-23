package es.ucm.ric.peticiones;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.tools.HttpPostConnector;

public class SincronizarFarmacosIntentService extends IntentService {
	
	 
    public static final String ACTION_PROGRESO = "es.ucm.ric.intent.action.farmaco.PROGRESO";
    public static final String ACTION_FIN = "es.ucm.ric.intent.action.farmaco.FIN";
 
    public SincronizarFarmacosIntentService() {
        super("SincronizarFarmacosIntentService");
    }
 
    @Override
    protected void onHandleIntent(Intent intent)
    {
    	String email = intent.getStringExtra("email");
    	String password = intent.getStringExtra("password");
    	tarea(email,password);
 
        Intent bcIntent = new Intent();
        bcIntent.setAction(ACTION_FIN);
        sendBroadcast(bcIntent);
    }
 
    private void tarea(String email, String pass)
    {
    	HttpPostConnector post = new HttpPostConnector();
		ArrayList<NameValuePair> postParametersToSend = new ArrayList<NameValuePair>();
		postParametersToSend.add(new BasicNameValuePair("email", email));
		postParametersToSend.add(new BasicNameValuePair("password", pass));
		

		// realizamos una peticion y como respuesta obtenes un array JSON
		JSONArray jdata = post.getserverdata(postParametersToSend, HttpPostConnector.URL_LISTA_FARMACOS);

		String mensaje;
		ArrayList<Farmaco> listdata;
		// si lo que obtuvimos no es null, es decir, hay respuesta v�lida
		if (jdata != null && jdata.length() > 0) {

			try {
				JSONObject json_data = jdata.getJSONObject(0);
				String codeFromServer = json_data.getString("code");
				//String messageFromServer = json_data.getString("message");
				
				if("200".equals(codeFromServer)){

			       
			        mensaje = json_data.getString("message");
			        
//			        JSONArray jArray = new JSONArray(mensaje);
//			        listdata = new ArrayList<Farmaco>();     
//			        if (jArray != null) { 
//			        	for (int i=0;i<jArray.length();i++){ 
//			        		JSONObject fila = jArray.getJSONObject(i);
//			        		int id = Integer.parseInt(fila.getString("id_farmaco"));
//			        		String nombre_farmaco = fila.getString("nombre_farmaco");
//			        		String nombre_fabricante = fila.getString("nombre_fabricante");
//			        		String presentacion_farmaco = fila.getString("presentacion_farmaco");
//			        		String tipo_presentacion = fila.getString("tipo_administracion");
//			        		String descripcion_farmaco = fila.getString("descripcion_farmaco");
//			        		
//			        		Farmaco f = new Farmaco(id, nombre_farmaco, nombre_fabricante, presentacion_farmaco, tipo_presentacion, descripcion_farmaco);
//			        		listdata.add(f);
//			           } 
//			        } 
//			        
//			        FarmacoDAO dao = new FarmacoDAO();
//			        dao.updateFromServer(listdata);
			        
			        boolean ok = Operaciones.syncFarmacos(mensaje);
				}
				else{
					//Toast.makeText(this, "Error desconocido.", Toast.LENGTH_SHORT).show();
				}
			

			} catch (JSONException e) {
				
			}
			
		} else { // json obtenido invalido verificar parte WEB.
			Log.e("JSON  ", "ERROR");
			
		}
	
    }
}