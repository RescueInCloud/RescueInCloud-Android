package es.ucm.ric.activities.fragments.lists;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.DrawerActivity;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.activities.fragments.FragmentNuevoProtocolo;
import es.ucm.ric.activities.listeners.ICambiarFragmentListener;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.IListable;
import es.ucm.ric.model.Protocolo;

public class FragmentListaProtocolos extends FragmentLista 
	implements OnItemClickListener{
	
	private ICambiarFragmentListener<IListable> listener;
	
	
	@Override
	public void onActivityCreated(Bundle state) {
		super.onActivityCreated(state);
		
		ProtocoloDAO dao = new ProtocoloDAO();
		ArrayList<Protocolo> lista_protocolos = dao.getListaProtocolo();
		
		lista = new ArrayList<IListable>();
		for (Protocolo p : lista_protocolos) {
			lista.add((IListable) p);
		}
		int[] imagenes_por_defecto = {R.drawable.diagnostico, R.drawable.diagnostico_check};
		adapter = new MiAdapter(this.getActivity(), lista, imagenes_por_defecto);
		
		listView = (ListView) getView().findViewById(R.id.mi_lista);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		
		IListable item = (IListable) adapter.getItemAtPosition(position);
		
		listener.cambiarFragment(item, DrawerActivity.ABRIR_PAGINADOR);
		
	}
	
	public void setListener(ICambiarFragmentListener<IListable> listener){
		this.listener = listener;
	}
}
