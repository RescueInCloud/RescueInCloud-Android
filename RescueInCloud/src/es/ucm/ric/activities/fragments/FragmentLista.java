package es.ucm.ric.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.model.IListable;

public class FragmentLista extends Fragment {
	
	protected ArrayList<IListable> lista;
	protected ListView listView;
	protected MiAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_list, container, false);
	}
	
//	@Override
//	public void onActivityCreated(Bundle state) {
//		super.onActivityCreated(state);
//		
//		FarmacoDAO dao = new FarmacoDAO();
//		ArrayList<Farmaco> lista_farmacos = dao.getListaFarmacos();
//		
//		for (Farmaco f : lista_farmacos) {
//			lista.add(f);
//		}
//		int[] imagenes_por_defecto = {R.drawable.frasco_medicina_roja, R.drawable.frasco_medicina_verde};
//		adapter = new MiAdapter(this.getActivity(), lista, imagenes_por_defecto);
//		listView = (ListView) getView().findViewById(R.id.mi_lista);
//		listView.setAdapter(adapter);
//	}
	
}
