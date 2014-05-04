package es.ucm.ric.activities.fragments.lists;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import es.ucm.ric.R;
import es.ucm.ric.activities.DrawerActivity;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.activities.listeners.ICambiarFragmentListener;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.model.IListable;

public class FragmentListaFarmacos extends FragmentLista
	implements OnItemClickListener{
	
	private ICambiarFragmentListener<IListable> listener;
	
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
		listView.setOnItemClickListener(this);
	}

	public void setListener(ICambiarFragmentListener<IListable> listener){
		this.listener = listener;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {

		IListable item = (IListable) adapter.getItemAtPosition(position);
		
		listener.cambiarFragment(item, DrawerActivity.ABRIR_DETALLE_FARMACO);
		
	}
}
