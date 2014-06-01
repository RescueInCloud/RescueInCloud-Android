package es.ucm.ric.activities.fragments.lists;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import es.ucm.ric.R;
import es.ucm.ric.activities.DrawerActivity;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.activities.listeners.ICambiarFragmentListener;
import es.ucm.ric.activities.peticiones.SincronizarFarmacosIntentService;
import es.ucm.ric.activities.peticiones.SincronizarProtocolosIntentService;
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
		int[] imagenes_por_defecto = {R.drawable.diagnostico, R.drawable.diagnostico};
		adapter = new MiAdapter(this.getActivity(), lista, imagenes_por_defecto);
		
		listView = (ListView) getView().findViewById(R.id.mi_lista);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(this);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(SincronizarProtocolosIntentService.ACTION_FIN);
		getActivity().registerReceiver(mHandleMessageReceiver, filter);
	}
	
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		 
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        if(intent.getAction().equals(SincronizarFarmacosIntentService.ACTION_FIN)) {
	            //BBDD actualizada -> Actualizar la lista;
	        	MiAdapter adapter = (MiAdapter) listView.getAdapter();
	        	adapter.clear();
	        	
	        	ProtocoloDAO dao = new ProtocoloDAO();
	    		ArrayList<Protocolo> lista_protocolos= dao.getListaProtocolo();
	    		
	    		lista.clear();
	    		lista = new ArrayList<IListable>();
	    		for (Protocolo f : lista_protocolos) {
	    			lista.add(f);
	    		}
	    		
	    		for (IListable item : lista) {
	    			adapter.add(item);
				}
	    		
	    		adapter.notifyDataSetChanged();
	        	
	        }
	    }
	};
	
	@Override
	public void onResume(){
		getActivity().registerReceiver(mHandleMessageReceiver, new IntentFilter(SincronizarFarmacosIntentService.ACTION_FIN));
		super.onResume();
	}

	@Override
	public void onPause(){
		try {
			getActivity().unregisterReceiver(mHandleMessageReceiver);
            
        } catch (Exception e) {
            Log.e("UnRegister Receiver Error", "> " + e.getMessage());
        }
        super.onPause();
	}

	@Override
	public void onDestroy() {
        try {
        	getActivity().unregisterReceiver(mHandleMessageReceiver);
        } catch (Exception e) {
            Log.e("UnRegister Receiver Error", "> " + e.getMessage());
        }
        super.onDestroy();
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
