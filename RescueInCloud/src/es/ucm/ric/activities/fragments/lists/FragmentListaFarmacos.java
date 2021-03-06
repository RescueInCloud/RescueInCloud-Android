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
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.activities.fragments.details.FragmentActivityDetalleFarmaco;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.model.IListable;
import es.ucm.ric.peticiones.SincronizarFarmacosIntentService;

public class FragmentListaFarmacos extends FragmentLista
	implements OnItemClickListener{
	
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
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(SincronizarFarmacosIntentService.ACTION_FIN);
		getActivity().registerReceiver(mHandleMessageReceiver, filter);
	}
	
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		 
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        if(intent.getAction().equals(SincronizarFarmacosIntentService.ACTION_FIN)) {
	            //BBDD actualizada -> Actualizar la lista;
	        	MiAdapter adapter = (MiAdapter) listView.getAdapter();
	        	adapter.clear();
	        	
	        	FarmacoDAO dao = new FarmacoDAO();
	    		ArrayList<Farmaco> lista_farmacos = dao.getListaFarmacos();
	    		
	    		lista.clear();
	    		lista = new ArrayList<IListable>();
	    		for (Farmaco f : lista_farmacos) {
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
	public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {

		Intent myIntent = new Intent(this.getActivity(), FragmentActivityDetalleFarmaco.class); 
	    myIntent.putExtra("id", ""+id);
	    startActivity(myIntent);
	}

}
