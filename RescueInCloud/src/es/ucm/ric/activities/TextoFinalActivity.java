package es.ucm.ric.activities;

import java.io.IOException;
import java.io.OutputStreamWriter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.tools.BaseActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TextoFinalActivity extends BaseActivity{
	public static int contador =0;
	String textoProtocolo;
	String tituloProtocolo;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_texto_final);
		
		Intent intent = getIntent();
		Bundle todosParametros = intent.getExtras();
		 textoProtocolo = todosParametros.getString("textoFinal");
		 tituloProtocolo = todosParametros.getString("tituloProtocolo");
		
        TextView t = (TextView)findViewById(R.id.textTextoFinal);
        Button buttonGuardar = (Button)findViewById(R.id.buttonGuardarTxt);
        
        t.setText(tituloProtocolo+"\n"+textoProtocolo);
        
        contador++;
        buttonGuardar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				grabar();
				Intent intent = new Intent(view.getContext(), DrawerActivity.class);

                startActivity(intent);
			}
        });
        
        
        
        

	}
	
	public void grabar() {
	 try {
		 String nombreP=tituloProtocolo+contador+"txt";
         OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
        		 nombreP, Activity.MODE_PRIVATE));
         archivo.write(textoProtocolo);
         archivo.flush();
         archivo.close();
     } catch (IOException e) {
     }
	 Toast t = Toast.makeText(this, "Los datos fueron grabados",
             Toast.LENGTH_SHORT);
     t.show();
     finish();
 }
	/*implements ICambiarFragmentListener<IListable>{
	
	
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
				ActivityCompat.invalidateOptionsMenu(TextoFinalActivity.this);
			}

			public void onDrawerOpened(View drawerView) {
				drawerAbierto = true;
				getSupportActionBar().setTitle(tituloApp);
				ActivityCompat.invalidateOptionsMenu(TextoFinalActivity.this);
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
	
	private void refrescarDatosConServidor(){
		Toast.makeText(this, "Sincronizar...", Toast.LENGTH_SHORT).show();
	}

	@SuppressLint("NewApi")
	private void silenciar(){
		if (silenciar){
			silenciar = false;
		}
		else{ 
			silenciar=true;
		}
		
		Toast.makeText(this, "Silenciar...", Toast.LENGTH_SHORT).show();
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
				lf.setListener(this);
				cambiarFragment(lf);
				break;
				
			case R.id.opcion_notas:
				FragmentListaNotas ln = new FragmentListaNotas();
				ln.setListener(this);
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
	
			case ABRIR_DETALLE_FARMACO:
				FragmentDetalleFarmaco fdf = new FragmentDetalleFarmaco();
				bundle = new Bundle();
			    bundle.putString("ID", data.getTitulo());
			    fdf.setArguments(bundle);
				cambiarFragment(fdf);
				break;
				
			case ABRIR_LECTOR_NOTA:
				FragmentNota fn = new FragmentNota();
				bundle = new Bundle();
			    bundle.putString("ID", data.getTitulo());
			    fn.setArguments(bundle);
				cambiarFragment(fn);
				break;
			default:
				break;
		}
		
	}
	*/

}
