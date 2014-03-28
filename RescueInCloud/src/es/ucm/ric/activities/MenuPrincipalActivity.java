package es.ucm.ric.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.tools.BaseActivity;

public class MenuPrincipalActivity extends BaseActivity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}
	
	public void onClick(View v){
		
		switch (v.getId()){
			case R.id.protocolos:
				Toast.makeText(this, "Has pulsado protocolos", Toast.LENGTH_SHORT).show();
				break;
			case R.id.farmacos:
				Toast.makeText(this, "Has pulsado farmacos", Toast.LENGTH_SHORT).show();
				break;
			case R.id.notas:
				Toast.makeText(this, "Has pulsado notas", Toast.LENGTH_SHORT).show();
				break;
		}
	}

}
