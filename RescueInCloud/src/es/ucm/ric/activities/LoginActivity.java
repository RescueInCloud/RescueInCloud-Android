package es.ucm.ric.activities;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.peticiones.RecuperarNotasConexion;
import es.ucm.ric.tools.AsyncConnect;

public class LoginActivity extends Activity{

	
 
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
	    		String email = ((EditText)findViewById(R.id.et_usuario)).getText().toString();
	    		String pass = ((EditText)findViewById(R.id.et_password)).getText().toString();
	    		AsyncConnect connection = new AsyncConnect(new RecuperarNotasConexion(this),email,pass);
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

}
