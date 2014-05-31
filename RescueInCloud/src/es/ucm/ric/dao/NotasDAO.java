package es.ucm.ric.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.Nota;

public class NotasDAO {
	private static final String TABLE = "notas";
	private static final String ID = "id";
	private static final String NOMBRE = "nombre_nota";
	private static final String DESCRIPCION = "descripcion";
	
	public ArrayList<Nota> getListaNotas() {
		
		ArrayList<Nota> list = new ArrayList<Nota>();
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		Nota valueObject;
		if(cursor.moveToFirst()){
			
			do{
				int id = cursor.getInt(cursor.getColumnIndex(ID));
				String nota = cursor.getString(cursor.getColumnIndex(NOMBRE));
				String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
				
				valueObject = new Nota(id,nota, descripcion);
				
				list.add(valueObject);
			}while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return list;
	}
	
	
	public Nota get(String id) {
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, ID+"=?", new String[] {id}, null, null, null);
		
		Nota valueObject = null;
		if (cursor.moveToFirst()) {
			String nombre = cursor.getString(cursor.getColumnIndex(NOMBRE));
			String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
			
			valueObject = new Nota(Integer.parseInt(id), nombre, descripcion);
					
		}

		cursor.close();
		db.close();
		return valueObject;
	}
	
	public boolean updateFromServer(List<Nota> listaNotas){
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		boolean result = false;
		try {
		    db.beginTransaction();

		    //Delete all records
		    db.delete(TABLE, null, null);
		    
		    //Insert all values
		    ContentValues values;
		    
		    for (Nota nota : listaNotas) {
		    	values = new ContentValues();
				values.put(NOMBRE, nota.getNombre());
				values.put(DESCRIPCION, nota.getDescripcion());
				long new_id = db.insertOrThrow(TABLE, null, values);
			}
		    
		    db.setTransactionSuccessful();
		    result = true;
		} catch(SQLException e) {
		    // do some error handling
			result = false;
		} finally {
		   db.endTransaction();
		}
		
		return result;

	}

}


