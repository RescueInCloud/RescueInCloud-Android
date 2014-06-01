package es.ucm.ric.activities.fragments.sandbox;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.ucm.ric.R;
import es.ucm.ric.activities.HolaMundoActivity;
import es.ucm.ric.activities.MenuPrincipalActivity;
import es.ucm.ric.peticiones.RecuperarTodoConexion;
//import es.ucm.ric.activities.peticiones.RecuperarNotasConexion;
import es.ucm.ric.tools.AsyncConnect;

public class FragmentTest extends Fragment {
	
	AsyncConnect connection;

	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_test, container, false);
	}
	
	
	public void onClick(View v){
		
		switch (v.getId()){
			case R.id.button1:
				Intent intent = new Intent(getActivity(), HolaMundoActivity.class);
				startActivity(intent);
				break;
			
			case R.id.button2:
				connection = new AsyncConnect(new RecuperarTodoConexion(getActivity()),"ricardocb48@gmail.com","admin");
		        connection.execute();
		        break;
				
			case R.id.button3:
				Intent intent2 = new Intent(getActivity(), MenuPrincipalActivity.class);
				startActivity(intent2);
		        break;
		        
			case R.id.button4:
				Intent intent3 = new Intent(getActivity(), SandboxActivity.class);
				startActivity(intent3);
		        break;
		
		
		}
		
	}
	
	
}

