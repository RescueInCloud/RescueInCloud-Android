package es.ucm.ric.activities.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;

public class FragmentDetalleFarmaco extends FragmentDetalle{

	private String id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		id = getArguments().getString("ID");
		
		return v;
	}
	
	
	@Override
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
		
		FarmacoDAO dao = new FarmacoDAO();
		Farmaco f = dao.get(id);
		
		TextView nombre = (TextView) getActivity().findViewById(R.id.nombre_farmaco);
		TextView fabricante = (TextView) getActivity().findViewById(R.id.nombre_fabricante);
		TextView presentacion = (TextView) getActivity().findViewById(R.id.presentacion_farmaco);
		TextView tipo_presentacion = (TextView) getActivity().findViewById(R.id.tipo_presentacion);
		TextView descripcion = (TextView) getActivity().findViewById(R.id.descripcion);
		
		
		nombre.setText(f.getNombre_farmaco());
		fabricante.setText(f.getNombre_fabricante());
		presentacion.setText(f.getPresentacion_farmaco());
		tipo_presentacion.setText(f.getTipo_presentacion());
		descripcion.setText(f.getDescripcion_farmaco());
	}
	
	
}
