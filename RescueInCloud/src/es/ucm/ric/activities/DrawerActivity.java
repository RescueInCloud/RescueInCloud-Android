package es.ucm.ric.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import es.ucm.ric.R;
import es.ucm.ric.tools.BaseActivity;

public class DrawerActivity extends BaseActivity{
    private DrawerLayout drawerLayout;
    private View drawerList;
    private ActionBarDrawerToggle drawerToggle;
    
    private CharSequence tituloSeccion;  
    private CharSequence tituloApp;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);
		
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.drawer);
        
        getSupportFragmentManager().beginTransaction()
		.replace(R.id.drawer, new FragmentDrawerLateral())
		.commit();

		
		tituloSeccion = getTitle();
		tituloApp = getTitle();
		
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_navigation_drawer, 
				R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(tituloSeccion);
				ActivityCompat.invalidateOptionsMenu(DrawerActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(tituloApp);
				ActivityCompat.invalidateOptionsMenu(DrawerActivity.this);
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		cambiarFragment(new FragmentInit());
	}
	
	private void cambiarFragment(Fragment fragment){
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
	}


	public void onClick(View v){
		
		//drawerList.setItemChecked(position, true);
		//tituloSeccion = opcionesMenu[position];
		//getSupportActionBar().setTitle(tituloSeccion);
		
		switch(v.getId()){
			case R.id.imagen_ini:
				cambiarFragment(new FragmentInit());
				break;
			case R.id.opcion_protocolo:
				cambiarFragment(new FragmentDetalle());
				break;
			
			case R.id.opcion_farmaco:
				cambiarFragment(new FragmentListaFarmacos());
				break;
				
			case R.id.opcion_notas:
				cambiarFragment(new FragmentNota());
				break;
				
			case R.id.opcion_test:
				cambiarFragment(new FragmentTest());
				break;
		}
		
		drawerLayout.closeDrawers();
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
	

}
