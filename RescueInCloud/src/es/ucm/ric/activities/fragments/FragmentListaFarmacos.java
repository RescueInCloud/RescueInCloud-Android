package es.ucm.ric.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.model.IListable;

public class FragmentListaFarmacos extends FragmentLista{
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		FarmacoDAO dao = new FarmacoDAO();
		ArrayList<Farmaco> lista_farmacos = dao.getListaFarmacos();
		
		lista = new ArrayList<IListable>();
		for (Farmaco f : lista_farmacos) {
			lista.add(f);
		}
		int[] imagenes_por_defecto = {R.drawable.frasco_medicina_roja, R.drawable.frasco_medicina_verde};
		adapter = new MiAdapter(this.getActivity(), lista, imagenes_por_defecto);
		listView = (ListView) getView().findViewById(R.id.mi_lista);
		listView.setAdapter(adapter);
	}

}
