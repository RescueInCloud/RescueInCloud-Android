package es.ucm.ric.activities.fragments.lists;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.activities.fragments.details.FragmentActivtyNota;
import es.ucm.ric.dao.NotasDAO;
import es.ucm.ric.model.IListable;
import es.ucm.ric.model.Nota;

public class FragmentListaNotas extends FragmentLista
	implements OnItemClickListener{
	
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		NotasDAO dao = new NotasDAO();
		ArrayList<Nota> lista_notas = dao.getListaNotas();
		
		lista = new ArrayList<IListable>();
		for (Nota n: lista_notas) {
			lista.add(n);
		}
		int[] imagenes_por_defecto = {R.drawable.libro_diagnostico, R.drawable.libro_diagnostico};
		adapter = new MiAdapter(this.getActivity(), lista, imagenes_por_defecto);
		listView = (ListView) getView().findViewById(R.id.mi_lista);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		Intent myIntent = new Intent(this.getActivity(), FragmentActivtyNota.class); 
	    myIntent.putExtra("id", ""+id);
	    startActivity(myIntent);
		
	}
	
}
