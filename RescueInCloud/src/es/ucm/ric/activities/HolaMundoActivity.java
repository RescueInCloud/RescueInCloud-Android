package es.ucm.ric.activities;


import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.peticiones.RecuperarTodoConexion;
import es.ucm.ric.tools.AsyncConnect;
import es.ucm.ric.tools.BaseActivity;

public class HolaMundoActivity extends BaseActivity {
	
	AsyncConnect connector; 
	TextView tv;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hola_mundo);
		
		
		tv = (TextView)findViewById(R.id.tv_lite);
		
		FarmacoDAO dao = new FarmacoDAO();
		ArrayList<Farmaco> lista = dao.getListaFarmacos();
		
		for (Farmaco farmaco : lista) {
			tv.append(farmaco+ "\n");
		}
		
		
	}
	
	public void onClick(View v){
		
		switch(v.getId()){
			case R.id.button_conexion:
				RecuperarTodoConexion connection = new RecuperarTodoConexion(this);
				connector = new AsyncConnect(connection,"ale7jandra.89@gmail.com","lalal");
		        connector.execute();
				break;
		}
	}


}
