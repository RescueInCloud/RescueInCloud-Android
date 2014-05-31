package es.ucm.ric.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;


/**
 * Clase que se encarga de enviar una peticion y gestionar la respuesta 
 * devolviendo un JSON Array.
 * 
 * @author Ricardo Champa
 *
 */
public class HttpPostConnector {
	

	//public static String IP_Server = "192.168.1.107";	
	//public static String IP_Server = "192.168.1.109";//ip mac
	public static String IP_Server = "ems-rczone.rhcloud.com/ws/scripts";//ip ws
	/* Connections */
	
	public static String URL_LOGIN_ANDROID = "http://" + IP_Server + "/listar_todo.php";
	public static String URL_LISTA_FARMACOS = "http://" + IP_Server + "/listar_farmacos.php";
	public static String URL_LISTA_PROTOCOLOS = "http://" + IP_Server + "/listar_protocolos.php";
	public static String URL_LISTA_NOTAS = "http://" + IP_Server + "/listar_notas.php";

	private InputStream is = null;
	private String result = "";

	public JSONArray getserverdata(ArrayList<NameValuePair> parameters,
			String urlwebserver) {

		httppostconnect(parameters, urlwebserver);

		if (is != null) {
			getpostresponse();
			return getjsonarray();
		} else
			return null;

	}

	// peticion HTTP
	private void httppostconnect(ArrayList<NameValuePair> parametros,
			String urlwebserver) {

		//
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(urlwebserver);
			httppost.setEntity(new UrlEncodedFormEntity(parametros));
			// ejecuto peticion enviando datos por POST
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();

		} catch (Exception e) {
			Log.e("log_tag", "Error in http connection " + e.toString());
		}

	}

	private void getpostresponse() {

		// Convierte respuesta a String
		try {
			//BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result = sb.toString();
			Log.e("getpostresponse", " result= " + sb.toString());
		} catch (Exception e) {
			Log.e("log_tag", "Error converting result " + e.toString());
		}
	}

	public JSONArray getjsonarray() {
		// parse json data
		try {
			JSONArray jArray = new JSONArray(result);
			return jArray;
		} catch (JSONException e) {
			Log.e("log_tag", "Error parsing data " + e.toString());
			return null;
		}

	}

}
