package es.ucm.ric.activities;


import java.util.ArrayList;

import android.os.Bundle;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.tools.BaseActivity;

public class SecondActivity extends BaseActivity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		
		TextView tv = (TextView)findViewById(R.id.tv_lite);
		
		FarmacoDAO dao = new FarmacoDAO();
		ArrayList<Farmaco> lista = dao.getListaFarmacos();
		
		for (Farmaco farmaco : lista) {
			tv.append(farmaco+ "\n");
		}
		
		
	}

}
