package es.ucm.ric.activities.peticiones;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.sub.CajaHijoVO;
import es.ucm.ric.model.sub.CajaTextoVO;
import es.ucm.ric.model.sub.ProtocoloVO;
import es.ucm.ric.tools.HttpPostConnector;

public class SincronizarProtocolosIntentService extends IntentService {
	
	 
    public static final String ACTION_PROGRESO = "es.ucm.ric.intent.action.protocolos.PROGRESO";
    public static final String ACTION_FIN = "es.ucm.ric.intent.action.protocolos.FIN";
 
    public SincronizarProtocolosIntentService() {
        super("SincronizarProtocolosIntentService");
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
		JSONArray jdata = post.getserverdata(postParametersToSend, HttpPostConnector.URL_LISTA_PROTOCOLOS);

		// si lo que obtuvimos no es null, es decir, hay respuesta v�lida
		String mensaje;
		
		if (jdata != null && jdata.length() > 0) {

			try {
				JSONObject json_data = jdata.getJSONObject(0);
				String codeFromServer = json_data.getString("code");
				//String messageFromServer = json_data.getString("message");
				
				if("200".equals(codeFromServer)){
			        mensaje = json_data.getString("message");
			        
			        SparseArray<ProtocoloVO> protocolos = new SparseArray<ProtocoloVO>();
			        
			        JSONArray ja = new JSONArray("["+mensaje+"]");
			        JSONArray cajas = ja.getJSONObject(0).getJSONArray("cajas");
			            
			        if (cajas != null) { 
			        	for (int i=0;i<cajas.length();i++){ 
			        		
			        		JSONObject fila = cajas.getJSONObject(i);
			        		int id = fila.getInt("id_caja_texto");
			        		String contenido = fila.getString("contenido");
			        		int tipo = fila.getInt("tipo");
			        		int id_protocolo = fila.getInt("id_protocolo");
			        		CajaTextoVO vo = new CajaTextoVO(id, tipo, contenido, id_protocolo);
			        		
			        		ProtocoloVO protocolo = protocolos.get(id_protocolo);
			        		if(protocolo==null){
			        			ProtocoloVO p = new ProtocoloVO(id_protocolo);
			        			if(id==0){
			        				p.setNombre(contenido);
			        			}
			        			p.addCaja(vo);
			        			protocolos.put(id_protocolo, p);
			        		}
			        		else{
			        			protocolo.addCaja(vo);
			        			if(id==0){
			        				protocolo.setNombre(contenido);
			        			}
			        		}
			        		
			           } 
			        } 
			        
			        JSONArray relaciones = ja.getJSONObject(0).getJSONArray("relaciones");
			             
			        if (relaciones != null) { 
			        	for (int i=0;i<relaciones.length();i++){ 
			        		JSONObject fila = relaciones.getJSONObject(i);
			        		int id = fila.getInt("id");
			        		int id_protocolo = fila.getInt("id_protocolo");
			        		int id_hijo = fila.getInt("id_hijo");
			        		int id_padre = fila.getInt("id_padre");
			        		int relacion = fila.getInt("relacion");
			        		
			        		CajaHijoVO vo = new CajaHijoVO(id, id_protocolo, id_hijo, id_padre, relacion);
			        		
			        		ProtocoloVO protocolo = protocolos.get(id_protocolo);
			        		if(protocolo!=null){
			        			protocolo.addHijo(vo);
			        		}
			        		else{
			        			Log.e("Protocolos", "Error");
			        		}
			           }  
			        } 
			        
			        ProtocoloDAO dao = new ProtocoloDAO();
			        dao.updateFromServer(protocolos);
			        
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