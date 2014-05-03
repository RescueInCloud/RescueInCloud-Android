package es.ucm.ric.activities.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.dao.ProtocoloDAO;
import es.ucm.ric.model.IListable;
import es.ucm.ric.model.Protocolo;

public class FragmentListaProtocolos extends FragmentLista{
	
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
	}
}
