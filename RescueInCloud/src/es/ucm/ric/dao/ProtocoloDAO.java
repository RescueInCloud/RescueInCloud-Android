package es.ucm.ric.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.Protocolo;

public class ProtocoloDAO {
	
	private static final String TABLE = "protocolos";
	private static final String ID = "protocolo_id";
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
}
