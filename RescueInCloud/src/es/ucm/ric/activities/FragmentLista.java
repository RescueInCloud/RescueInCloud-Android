package es.ucm.ric.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;

public class FragmentLista extends Fragment {
	
	ArrayList<Farmaco> lista;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		FarmacoDAO dao = new FarmacoDAO();
		lista = dao.getListaFarmacos();
		
		MiAdapter adapter = new MiAdapter(this.getActivity(), lista);
		listView = (ListView) getView().findViewById(R.id.mi_lista);
		listView.setAdapter(adapter);
	}
	
	
	
}
