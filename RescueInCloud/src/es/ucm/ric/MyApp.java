package es.ucm.ric;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import es.ucm.ric.tools.Tools;

public class MyApp extends Application {
	
	private static MyApp INSTANCE;
	public final static String PREFERENCES_FILE = "Ajustes";
	public final String  DATABASE_NAME = "rescue_lite_db";
	private boolean hayActualizacion;
	
	public static Context getContext() {
		return INSTANCE.getApplicationContext();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		INSTANCE = this;
		
		SharedPreferences prefs = this.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    	
    	boolean flag = prefs.getBoolean("flag",false);
    	int version = prefs.getInt("version", -1);
    	
    	int posible_nueva_version = Tools.getApplocationVersion(this.getApplicationContext());
		
		if(version<posible_nueva_version){
			hayActualizacion = true;
			prefs = this.getApplicationContext().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
	        SharedPreferences.Editor editor = prefs.edit();
	        editor.putInt("version", posible_nueva_version);
	        editor.commit();
		}
		else{
			hayActualizacion = false;
		}
    	
		
		if(!flag || hayActualizacion){
			
			prefs = this.getApplicationContext().getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
	        SharedPreferences.Editor editor = prefs.edit();
	        editor.putBoolean("flag", true);
	        editor.commit();
			
	        Tools.loadDataBase(this.getApplicationContext(), DATABASE_NAME, R.raw.rescue_lite_db);
	   	}
    	
	}

}
