package es.ucm.ric.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.CajasHijos;
import es.ucm.ric.model.Tupla;

public class CajaTextoHijoDAO {
	/*0 -> normal
1-> decision
y en el tipo de relacion
0 -> Sí
1 -> no
2 -> nada*/
	private static final String TABLE = "caja_texto_hijos";
	private static final String ID = "id";
	private static final String ID_PROTOCOLO = "id_protocolo";
	private static final String HIJO = "caja_texto_hijo";
	private static final String PADRE = "caja_texto_padre";
	private static final String RELACION = "tipo_relacion";
	
	public CajasHijos getListaCajas(int id_protocolo) {
		
		
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		//Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		Cursor cursor = db.query(TABLE, null, ID_PROTOCOLO+"=?", new String[] {""+id_protocolo}, null, null, null);
		CajasHijos valueObject = new CajasHijos();
		if(cursor.moveToFirst()){
			
			do{
				int id_hijo = cursor.getInt(cursor.getColumnIndex(HIJO));
				int id_padre = cursor.getInt(cursor.getColumnIndex(PADRE));
				int relacion = cursor.getInt(cursor.getColumnIndex(RELACION));
				Tupla t = new Tupla();
				t.id=id_hijo;
				t.relacion = relacion;
				valueObject.addHijo(id_padre,t);
				
			}while(cursor.moveToNext());
			//al final hay que a�adir el valueObject a la clase protocolo
		}
		
		cursor.close();
		db.close();
		return valueObject;
	}

}
