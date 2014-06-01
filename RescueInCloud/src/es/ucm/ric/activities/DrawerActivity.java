package es.ucm.ric.activities;

import java.util.Stack;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.activities.fragments.FragmentDrawerLateral;
import es.ucm.ric.activities.fragments.FragmentPaginador;
import es.ucm.ric.activities.fragments.lists.FragmentListaFarmacos;
import es.ucm.ric.activities.fragments.lists.FragmentListaNotas;
import es.ucm.ric.activities.fragments.lists.FragmentListaProtocolos;
import es.ucm.ric.activities.fragments.sandbox.FragmentTest;
import es.ucm.ric.activities.listeners.ICambiarFragmentListener;
import es.ucm.ric.activities.peticiones.SincronizarFarmacosIntentService;
import es.ucm.ric.activities.peticiones.SincronizarProtocolosIntentService;
import es.ucm.ric.model.IListable;
import es.ucm.ric.tools.BaseActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DrawerActivity extends BaseActivity
	implements ICambiarFragmentListener<IListable>{
	
	
	public static final int ABRIR_PAGINADOR = 0;
	public static final int ABRIR_DETALLE_FARMACO = 1;
	public static final int ABRIR_LECTOR_NOTA = 2;
	
	public boolean silenciar = false;
	
	
    private DrawerLayout drawerLayout;
    private View drawerList;
    private ActionBarDrawerToggle drawerToggle;
    
    private CharSequence tituloSeccion;  
    private CharSequence tituloApp;  
    
    boolean drawerAbierto = false;
    
    FragmentTest fragTest;
    
    Stack<Fragment> pila_navegacion;
    Fragment fragment_actual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drawer);
		
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.drawer);
        
        pila_navegacion = new Stack<Fragment>();
        
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
		
		fragment_actual = fragment;
		
		if(	fragment instanceof FragmentListaProtocolos ||
			fragment instanceof FragmentListaFarmacos ||
			fragment instanceof FragmentListaNotas){
			
			//se reinicia la navegación
			pila_navegacion.clear();
			pila_navegacion.push(fragment);
			
		}
		else{
			pila_navegacion.push(fragment);
		}
		
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
				
			case R.id.action_refresh:
				refrescarDatosConServidor();
				return true;
			
			case R.id.action_silence:
				silenciar();
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onBackPressed() {
	   
		if(!pila_navegacion.isEmpty()){
			//desapilar
			pila_navegacion.pop();
			
			if(!pila_navegacion.isEmpty()){
				cambiarFragment(pila_navegacion.firstElement());
			}
		}
		else{
			super.onBackPressed();
		}
		
		
	}
	
	private void refrescarDatosConServidor(){
		if(fragment_actual instanceof FragmentListaProtocolos ){
			Toast.makeText(this, "Sincronizando protocolos...", Toast.LENGTH_SHORT).show();
			Intent msgIntent = new Intent(DrawerActivity.this, SincronizarProtocolosIntentService.class);
	        msgIntent.putExtra("email", "ricardocb48@gmail.com");
	        msgIntent.putExtra("password", "admin");
	        startService(msgIntent);
		}
		else if(fragment_actual instanceof FragmentListaFarmacos ){
			Toast.makeText(this, "Sincronizando fármacos...", Toast.LENGTH_SHORT).show();
			Intent msgIntent = new Intent(DrawerActivity.this, SincronizarFarmacosIntentService.class);
	        msgIntent.putExtra("email", "ale7jandra.89@gmail.com");
	        msgIntent.putExtra("password", "admin");
	        startService(msgIntent);
		}
		else if(fragment_actual instanceof FragmentListaNotas ){//fragmentnotas
			Toast.makeText(this, "Sincronizando notas...", Toast.LENGTH_SHORT).show();
		}
		else{
			Toast.makeText(this, "Imposible sincronizar...", Toast.LENGTH_SHORT).show();
		}
		
	}

	@SuppressLint("NewApi")
	private void silenciar(){
		if (silenciar){
			silenciar = false;
			Toast.makeText(this, "Sin volumen", Toast.LENGTH_SHORT).show();
		}
		else{ 
			silenciar=true;
			Toast.makeText(this, "Con volumen", Toast.LENGTH_SHORT).show();
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
				FragmentListaProtocolos fp = new FragmentListaProtocolos();
				fp.setListener(this);
				cambiarFragment(fp);
				break;
			
			case R.id.opcion_farmaco:
				FragmentListaFarmacos lf = new FragmentListaFarmacos();
				cambiarFragment(lf);
				break;
				
			case R.id.opcion_notas:
				FragmentListaNotas ln = new FragmentListaNotas();
				cambiarFragment(ln);
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
		Bundle bundle = null;
		switch (opcion) {
			case ABRIR_PAGINADOR:
				FragmentPaginador f = new FragmentPaginador();
				bundle = new Bundle();
			    bundle.putString("ID", data.getTitulo());
			    f.setArguments(bundle);
				cambiarFragment(f);
				break;
				
			default:
				break;
		}
		
	}
	

}
