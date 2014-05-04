package es.ucm.ric.activities;

import android.os.Bundle;
import android.view.View;
import es.ucm.ric.R;
import es.ucm.ric.activities.fragments.sandbox.FragmentMenuPrincipal;
import es.ucm.ric.tools.BaseActivity;

public class MenuPrincipalActivity extends BaseActivity{
	
	FragmentMenuPrincipal menuPrincipal;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		menuPrincipal = (FragmentMenuPrincipal)getSupportFragmentManager().findFragmentById(R.id.FrgMenuPrincipal);
	}
	
	public void onClick(View v){
		
		switch (v.getId()){
			case R.id.protocolos:
			case R.id.farmacos:
			case R.id.notas:
			case R.id.test:
				menuPrincipal.onClick(v);
				break;
		}
	}

}
