package es.ucm.ric.peticiones;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.model.sub.CajaHijoVO;
import es.ucm.ric.model.sub.CajaTextoVO;
import es.ucm.ric.model.sub.ProtocoloVO;

public class Operaciones {
	
	public static boolean syncFarmacos(String mensaje) {
		
		try {
			
		    JSONArray jArray = new JSONArray(mensaje);
			ArrayList<Farmaco> listdata = new ArrayList<Farmaco>();     
	        if (jArray != null) { 
	        	for (int i=0;i<jArray.length();i++){ 
	        		JSONObject fila = jArray.getJSONObject(i);
	        		int id = Integer.parseInt(fila.getString("id_farmaco"));
	        		String nombre_farmaco = fila.getString("nombre_farmaco");
	        		String nombre_fabricante = fila.getString("nombre_fabricante");
	        		String presentacion_farmaco = fila.getString("presentacion_farmaco");
	        		String tipo_presentacion = fila.getString("tipo_administracion");
	        		String descripcion_farmaco = fila.getString("descripcion_farmaco");
	        		
	        		Farmaco f = new Farmaco(id, nombre_farmaco, nombre_fabricante, presentacion_farmaco, tipo_presentacion, descripcion_farmaco);
	        		listdata.add(f);
	           } 
	        } 
	        
	        FarmacoDAO dao = new FarmacoDAO();
	        return dao.updateFromServer(listdata);
	        
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
        
		
	}
	
	public static boolean syncProtocolos(String mensaje){
		
		try{
			
		
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
	        return dao.updateFromServer(protocolos);
        
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

}
