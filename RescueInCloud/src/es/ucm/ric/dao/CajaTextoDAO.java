package es.ucm.ric.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.CajaTexto;

public class CajaTextoDAO {
	/*0 -> normal
1-> decision
y en el tipo de relacion
0 -> S�
1 -> no
2 -> nada*/
	public static final String TABLE = "caja_texto";
	public static final String ID = "id";
	public static final String ID_CAJA = "caja_texto_id";
	public static final String TIPO = "tipo";
	public static final String CONTENIDO = "contenido";
	public static final String PROTOCOLO_ID = "protocolo_id";
	
	public ArrayList<CajaTexto> getListaProtocolo(int id_protocolo) {
		
		ArrayList<CajaTexto> list = new ArrayList<CajaTexto>();
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		//Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		Cursor cursor = db.query(TABLE, null, PROTOCOLO_ID+"=?", new String[] {""+id_protocolo}, null, null, null);
		CajaTexto valueObject;
		if(cursor.moveToFirst()){
			
			do{
				int id = cursor.getInt(cursor.getColumnIndex(ID_CAJA));
				int tipo = cursor.getInt(cursor.getColumnIndex(TIPO));
				String contenido = cursor.getString(cursor.getColumnIndex(CONTENIDO));
				
				valueObject = new CajaTexto(id, id_protocolo, tipo, contenido);
				//falta a�adir que se a�ada la CajaTexto al protocolo
				
				list.add(valueObject);
			}while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return list;
	}
}
