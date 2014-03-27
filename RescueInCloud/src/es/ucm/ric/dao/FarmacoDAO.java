package es.ucm.ric.dao;

import java.util.ArrayList;

import es.ucm.ric.MyApp;
import es.ucm.ric.model.Farmaco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


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
	

}
