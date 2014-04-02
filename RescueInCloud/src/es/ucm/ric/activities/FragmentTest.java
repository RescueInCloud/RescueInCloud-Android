package es.ucm.ric.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.ucm.ric.R;
import es.ucm.ric.activities.peticiones.HolaConexion;
import es.ucm.ric.tools.AsyncConnect;

public class FragmentTest extends Fragment {
	
	AsyncConnect connection;

	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.activity_test, container, false);
	}
	
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_test);
//	}
	
	public void onClick(View v){
		
		switch (v.getId()){
			case R.id.button1:
				Intent intent = new Intent(getActivity(), HolaMundoActivity.class);
				startActivity(intent);
				break;
			
			case R.id.button2:
				connection = new AsyncConnect(new HolaConexion(getActivity()),"ale7jandra.89@gmail.com");
		        connection.execute();
		        break;
				
			case R.id.button3:
				Intent intent2 = new Intent(getActivity(), MenuPrincipalActivity.class);
				startActivity(intent2);
		        break;
		
		
		}
		
	}
	
	
}

