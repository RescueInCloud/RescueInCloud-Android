package es.ucm.ric.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.CajaTexto;
import es.ucm.ric.model.CajasHijos;
import es.ucm.ric.model.Protocolo;
import es.ucm.ric.model.sub.CajaHijoVO;
import es.ucm.ric.model.sub.CajaTextoVO;
import es.ucm.ric.model.sub.ProtocoloVO;

public class ProtocoloDAO {
	
	private static final String TABLE = "protocolos";
	private static final String ID = "protocolos_id";
	private static final String NOMBRE = "nombre_protocolo";
	
	public ArrayList<Protocolo> getListaProtocolo() {
		
		ArrayList<Protocolo> list = new ArrayList<Protocolo>();
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		Protocolo valueObject;
		if(cursor.moveToFirst()){
			
			do{
				int id = cursor.getInt(cursor.getColumnIndex(ID));
				String nombre = cursor.getString(cursor.getColumnIndex(NOMBRE));
				
				valueObject = new Protocolo(id,nombre);
				
				list.add(valueObject);
			}while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return list;
	}
	
	public Protocolo get(String nombre) {
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, NOMBRE+"=?", new String[] {nombre}, null, null, null);
		Protocolo valueObject = null;
		
		if (cursor.moveToFirst()) {
			int id = cursor.getInt(cursor.getColumnIndex(ID));
			valueObject = new Protocolo(id, nombre);
			
			ArrayList<CajaTexto> cajas = new CajaTextoDAO().getListaProtocolo(id);
			for (CajaTexto cajaTexto : cajas) {
				valueObject.anyadirCaja(cajaTexto);
			}
			
			CajasHijos relaciones = new CajaTextoHijoDAO().getListaCajas(id);
			valueObject.setHijos(relaciones);
			
		}
		
		cursor.close();
		db.close();
		return valueObject;
	}
	
	public boolean updateFromServer(SparseArray<ProtocoloVO> lista_protocolos){
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		boolean result = false;
		try {
		    db.beginTransaction();

		    //Delete all records
		    db.delete(TABLE, null, null);
		    db.delete(CajaTextoDAO.TABLE, null, null);
		    db.delete(CajaTextoHijoDAO.TABLE, null, null);
		    
		    //Insert all values
		    ContentValues values;
		    ProtocoloVO protocolo;
		    int key = 0;
		    for(int i = 0; i < lista_protocolos.size(); i++) {
		    
		    	key = lista_protocolos.keyAt(i);
		    	protocolo = lista_protocolos.get(key);
		    	values = new ContentValues();
		    	values.put(ID, protocolo.getId());
				values.put(NOMBRE, protocolo.getNombre());
				long new_id = db.insertOrThrow(TABLE, null, values);
				
				List<CajaTextoVO> cajas = protocolo.getCajas();
				for (CajaTextoVO caja : cajas) {
					values = new ContentValues();
			    	values.put(CajaTextoDAO.ID_CAJA, caja.getId());
			    	values.put(CajaTextoDAO.CONTENIDO, caja.getContenido());
			    	values.put(CajaTextoDAO.TIPO, caja.getTipo());
			    	values.put(CajaTextoDAO.PROTOCOLO_ID, caja.getProtocolo_id());
			    	new_id = db.insertOrThrow(CajaTextoDAO.TABLE, null, values);
				}
				
				List<CajaHijoVO> hijos = protocolo.getHijos();
				for (CajaHijoVO hijo : hijos) {
					values = new ContentValues();
			    	values.put(CajaTextoHijoDAO.ID, hijo.getId());
			    	values.put(CajaTextoHijoDAO.ID_PROTOCOLO, hijo.getId_protocolo());
			    	values.put(CajaTextoHijoDAO.HIJO, hijo.getId_hijo());
			    	values.put(CajaTextoHijoDAO.PADRE, hijo.getId_padre());
			    	values.put(CajaTextoHijoDAO.RELACION, hijo.getTipo_relacion());
			    	new_id = db.insertOrThrow(CajaTextoHijoDAO.TABLE, null, values);
				}
		       
		    }
		   
		    
		    db.setTransactionSuccessful();
		    result = true;
		} catch(SQLException e) {
		    e.printStackTrace();
			Log.e("Protocolos", "Error");
			result = false;
		} finally {
		   db.endTransaction();
		}
		
		return result;

	}
}
