package es.ucm.ric.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import es.ucm.ric.R;
import es.ucm.ric.activities.fragments.FragmentDrawerLateral;
import es.ucm.ric.activities.fragments.FragmentNota;
import es.ucm.ric.activities.fragments.FragmentPaginador;
import es.ucm.ric.activities.fragments.FragmentTest;
import es.ucm.ric.activities.fragments.lists.FragmentListaFarmacos;
import es.ucm.ric.activities.fragments.lists.FragmentListaNotas;
import es.ucm.ric.activities.fragments.lists.FragmentListaProtocolos;
import es.ucm.ric.activities.listeners.ICambiarFragmentListener;
import es.ucm.ric.model.IListable;
import es.ucm.ric.tools.BaseActivity;

public class DrawerActivity extends BaseActivity
	implements ICambiarFragmentListener<IListable>{
	
	
	public static final int ABRIR_PAGINADOR = 0;
	
	
    private DrawerLayout drawerLayout;
    private View drawerList;
    private ActionBarDrawerToggle drawerToggle;
    
    private CharSequence tituloSeccion;  
    private CharSequence tituloApp;  
    
    boolean drawerAbierto = false;
    
    FragmentTest fragTest;
    

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
				drawerAbierto = false;
				getSupportActionBar().setTitle(tituloSeccion);
				ActivityCompat.invalidateOptionsMenu(DrawerActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				drawerAbierto = true;
				getSupportActionBar().setTitle(tituloApp);
				ActivityCompat.invalidateOptionsMenu(DrawerActivity.this);
			}
		};

		drawerLayout.setDrawerListener(drawerToggle);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		FragmentListaProtocolos f = new FragmentListaProtocolos();
		f.setListener(this);
		cambiarFragment(f);
	}
	
	private void cambiarFragment(Fragment fragment){
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if(drawerAbierto)
					drawerLayout.closeDrawers();
				else
					drawerLayout.openDrawer(drawerList);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


	public void onClick(View v){
		
		//drawerList.setItemChecked(position, true);
		//tituloSeccion = opcionesMenu[position];
		//getSupportActionBar().setTitle(tituloSeccion);
		
		switch(v.getId()){
			case R.id.imagen_ini:
				//cambiarFragment(new FragmentInit());
				break;
			case R.id.opcion_protocolo:
				FragmentListaProtocolos f = new FragmentListaProtocolos();
				f.setListener(this);
				cambiarFragment(f);
				break;
			
			case R.id.opcion_farmaco:
				cambiarFragment(new FragmentListaFarmacos());
				break;
				
			case R.id.opcion_notas:
				cambiarFragment(new FragmentListaNotas());
				break;
				
			case R.id.opcion_test:
				fragTest = new FragmentTest();
				cambiarFragment(fragTest);
				break;
				
				
			case R.id.empezar:
				cambiarFragment(new FragmentPaginador());
				break;
				
			case R.id.button1:
			case R.id.button2:
			case R.id.button3:
			case R.id.button4:
			    fragTest.onClick(v);
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

	@Override
	public void cambiarFragment(IListable data, int opcion) {
		
		switch (opcion) {
			case ABRIR_PAGINADOR:
				FragmentPaginador f = new FragmentPaginador();
				Bundle bundle = new Bundle();
			    bundle.putString("ID", data.getTitulo());
			    f.setArguments(bundle);
				cambiarFragment(f);
				break;
	
			default:
				break;
		}
		
	}
	

}
