package es.ucm.ric.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import es.ucm.ric.MyApp;
import es.ucm.ric.R;
import es.ucm.ric.activities.listeners.Listener;
import es.ucm.ric.peticiones.RecuperarTodoConexion;
import es.ucm.ric.tools.AsyncConnect;

public class LoginActivity extends Activity implements Listener<Boolean>{

	private String email;
	private String pass;
 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       
 
        setContentView(R.layout.activity_login);
    }
    
    public void onClick(View v){
    	
    	switch(v.getId()){
    	
	    	case R.id.button_login:
	    		email = ((EditText)findViewById(R.id.et_usuario)).getText().toString();
	    		pass = ((EditText)findViewById(R.id.et_password)).getText().toString();
	    		RecuperarTodoConexion c = new RecuperarTodoConexion(this);
	    		c.setListener(this);
	    		AsyncConnect connection = new AsyncConnect(c,email,pass);
		        connection.execute();
	    		break;
	    	case R.id.button_forget:
	    		Toast.makeText(this, "Opción aún no disponible", Toast.LENGTH_SHORT).show();
	    		break;
	    	case R.id.button_registro:
	    		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ems-rczone.rhcloud.com/init/login"));
	 	       	startActivity(intent);
	    		break;
    	}
    	
    }

	@Override
	public void actualizar(Boolean ok) {
		
		if(ok){
			SharedPreferences preferences = getSharedPreferences(MyApp.PREFERENCES_FILE,Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("email", email);
			editor.putString("pass", pass);
			editor.commit();
			
			Intent intent = new Intent(this, DrawerActivity.class);
			startActivity(intent);
		}
		else{
			Toast.makeText(this, "El usuario o la contraseña no son correctas.", Toast.LENGTH_SHORT).show();
		}
		
	}

}
