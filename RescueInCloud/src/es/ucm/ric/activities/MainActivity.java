package es.ucm.ric.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import es.ucm.ric.R;
import es.ucm.ric.activities.peticiones.HolaConexion;
import es.ucm.ric.tools.AsyncConnect;
import es.ucm.ric.tools.BaseActivity;

public class MainActivity extends BaseActivity {
	
	AsyncConnect connection;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onClick(View v){
		
		switch (v.getId()){
			case R.id.button1:
				Intent intent = new Intent(this, SecondActivity.class);
				startActivity(intent);
				break;
			
			case R.id.button2:
				connection = new AsyncConnect(new HolaConexion(MainActivity.this),"ale7jandra.89@gmail.com");
		        connection.execute();
		        break;
				
		
		
		}
		
	}
	
	
}

