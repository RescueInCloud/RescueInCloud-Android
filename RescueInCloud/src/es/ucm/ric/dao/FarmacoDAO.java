package es.ucm.ric.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import es.ucm.ric.MyApp;
import es.ucm.ric.model.Farmaco;


public class FarmacoDAO {
	
	private static final String TABLE = "farmacos";
	private static final String ID = "id_farmaco";
	private static final String FARMACO = "nombre_farmaco";
	private static final String FABRICANTE = "nombre_fabricante";
	private static final String PRESENTACION = "presentacion_farmaco";
	private static final String ADMINISTRACION = "tipo_administracion";
	private static final String DESCRIPCION = "descripcion_farmaco";
	
	public ArrayList<Farmaco> getListaFarmacos() {
		
		ArrayList<Farmaco> list = new ArrayList<Farmaco>();
		//SQLiteDatabase db = new DatabaseHelper().getReadableDatabase();
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, null, null, null, null, null);
		Farmaco valueObject;
		if(cursor.moveToFirst()){
			
			do{
				int id = cursor.getInt(cursor.getColumnIndex(ID));
				String farmaco = cursor.getString(cursor.getColumnIndex(FARMACO));
				String fabricante = cursor.getString(cursor.getColumnIndex(FABRICANTE));
				String presentacion = cursor.getString(cursor.getColumnIndex(PRESENTACION));
				String administracion = cursor.getString(cursor.getColumnIndex(ADMINISTRACION));
				String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
				
				valueObject = new Farmaco(id, farmaco, fabricante, presentacion, administracion, descripcion);
				
				list.add(valueObject);
			}while(cursor.moveToNext());
		}
		
		cursor.close();
		db.close();
		return list;
	}
	
	
	public Farmaco get(String id) {
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		Cursor cursor = db.query(TABLE, null, ID+"=?", new String[] {id}, null, null, null);
		
		Farmaco valueObject = null;
		if (cursor.moveToFirst()) {
			String name = cursor.getString(cursor.getColumnIndex(FARMACO));
			String fabrincante = cursor.getString(cursor.getColumnIndex(FABRICANTE));
			String presentacion = cursor.getString(cursor.getColumnIndex(PRESENTACION));
			String administracion = cursor.getString(cursor.getColumnIndex(ADMINISTRACION));
			String descripcion = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
			
			valueObject = new Farmaco(Integer.parseInt(id), name, fabrincante,presentacion, administracion, descripcion);
		}

		cursor.close();
		db.close();
		return valueObject;
	}
	
	public boolean updateFromServer(List<Farmaco> listaFarmacos){
		SQLiteDatabase db = MyApp.getContext().openOrCreateDatabase("rescue_lite_db", Context.MODE_PRIVATE,null);
		boolean result = false;
		try {
		    db.beginTransaction();

		    //Delete all records
		    db.delete(TABLE, null, null);
		    
		    //Insert all values
		    ContentValues values;
		    
		    for (Farmaco farmaco : listaFarmacos) {
		    	values = new ContentValues();
				values.put(FARMACO, farmaco.getNombre_farmaco());
				values.put(FABRICANTE, farmaco.getNombre_fabricante());
				values.put(PRESENTACION, farmaco.getPresentacion_farmaco());
				values.put(ADMINISTRACION, farmaco.getTipo_presentacion());
				values.put(DESCRIPCION, farmaco.getDescripcion_farmaco());
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
