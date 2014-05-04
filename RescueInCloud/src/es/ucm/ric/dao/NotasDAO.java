package es.ucm.ric.dao;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.Nota;

public class NotasDAO {
	private static final String TABLE = "notas";
	//private static final String ID = "rowid";
	private static final String FARMACO = "nombre_nota";
	private static final String DESCRIPCION = "descripcion";
	
	public ArrayList<Nota> getListaNotas() {
		
		ArrayList<Nota> list = new ArrayList<Nota>();
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		Nota valueObject;
		if(cursor.moveToFirst()){
			
			do{
				//int id = cursor.getInt(cursor.getColumnIndex(ID));
				String nota = cursor.getString(cursor.getColumnIndex(FARMACO));
				String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
				
				valueObject = new Nota(nota, descripcion);
				
				list.add(valueObject);
			}while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return list;
	}
	

}


