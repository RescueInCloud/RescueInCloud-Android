package es.ucm.ric.activities.fragments.lists;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.dao.NotasDAO;
import es.ucm.ric.model.IListable;
import es.ucm.ric.model.Nota;

public class FragmentListaNotas extends FragmentLista{
	
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
	}

}
