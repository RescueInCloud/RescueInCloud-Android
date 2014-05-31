package es.ucm.ric.activities.fragments.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.tools.BaseActivity;

public class FragmentDetalleFarmaco extends BaseActivity{

	private String id;

	
	@Override
	public void onCreate(Bundle state) { 
		super.onCreate(state);
		setContentView(R.layout.fragment_detalle);

	    Intent intent = getIntent();
	    id = intent.getStringExtra("id");
	    
	    if(id!=null){
		
			FarmacoDAO dao = new FarmacoDAO();
			Farmaco f = dao.get(id);
			
			TextView nombre = (TextView) findViewById(R.id.nombre_farmaco);
			TextView fabricante = (TextView)findViewById(R.id.nombre_fabricante);
			TextView presentacion = (TextView)findViewById(R.id.presentacion_farmaco);
			TextView tipo_presentacion = (TextView)findViewById(R.id.tipo_presentacion);
			TextView descripcion = (TextView)findViewById(R.id.descripcion);
			
			
			nombre.setText(f.getNombre_farmaco());
			fabricante.setText(f.getNombre_fabricante());
			presentacion.setText(f.getPresentacion_farmaco());
			tipo_presentacion.setText(f.getTipo_presentacion());
			descripcion.setText(f.getDescripcion_farmaco());
	    }
	}
	
	
}
