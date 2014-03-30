package es.ucm.ric.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import es.ucm.ric.R;
import es.ucm.ric.activities.peticiones.HolaConexion;
import es.ucm.ric.tools.AsyncConnect;
import es.ucm.ric.tools.BaseActivity;

public class TestActivity extends BaseActivity {
	
	AsyncConnect connection;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}
	
	public void onClick(View v){
		
		switch (v.getId()){
			case R.id.button1:
				Intent intent = new Intent(this, HolaMundoActivity.class);
				startActivity(intent);
				break;
			
			case R.id.button2:
				connection = new AsyncConnect(new HolaConexion(TestActivity.this),"ale7jandra.89@gmail.com");
		        connection.execute();
		        break;
				
			case R.id.button3:
				Intent intent2 = new Intent(this, MenuPrincipalActivity.class);
				startActivity(intent2);
		        break;
		
		
		}
		
	}
	
	
}

