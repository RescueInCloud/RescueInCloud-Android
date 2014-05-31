package es.ucm.ric.activities.fragments.lists;

import java.util.ArrayList;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MiAdapter;
import es.ucm.ric.activities.fragments.details.FragmentActivityDetalleFarmaco;
import es.ucm.ric.activities.listeners.UpdateListener;
import es.ucm.ric.activities.peticiones.LocalService;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;
import es.ucm.ric.model.IListable;

public class FragmentListaFarmacos extends FragmentLista
	implements OnItemClickListener,UpdateListener{
	
	private boolean mIsBound;
	
	private LocalService mBoundService;

	private ServiceConnection mConnection = new ServiceConnection() {
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // This is called when the connection with the service has been
	        // established, giving us the service object we can use to
	        // interact with the service.  Because we have bound to a explicit
	        // service that we know is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        mBoundService = ((LocalService.LocalBinder)service).getService();

	        // Tell the user about this for our demo.
	        Toast.makeText(FragmentListaFarmacos.this.getActivity(), "Conectando...",
	                Toast.LENGTH_SHORT).show();
	    }

	    public void onServiceDisconnected(ComponentName className) {
	        // This is called when the connection with the service has been
	        // unexpectedly disconnected -- that is, its process crashed.
	        // Because it is running in our same process, we should never
	        // see this happen.
	        mBoundService = null;
	        Toast.makeText(FragmentListaFarmacos.this.getActivity(), "Desconectado..",
	                Toast.LENGTH_SHORT).show();
	    }
	};

	void doBindService() {
	    // Establish a connection with the service.  We use an explicit
	    // class name because we want a specific service implementation that
	    // we know will be running in our own process (and thus won't be
	    // supporting component replacement by other applications).
	    getActivity().bindService(new Intent(FragmentListaFarmacos.this.getActivity(), 
	            LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
	    mIsBound = true;
	}

	void doUnbindService() {
	    if (mIsBound) {
	        // Detach our existing connection.
	    	getActivity().unbindService(mConnection);
	        mIsBound = false;
	    }
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    doUnbindService();
	}
	
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

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position,long id) {

		Intent myIntent = new Intent(this.getActivity(), FragmentActivityDetalleFarmaco.class); 
	    myIntent.putExtra("id", ""+id);
	    startActivity(myIntent);
	}

	@Override
	public void onUpdate(boolean updating) {
		// TODO Auto-generated method stub
		
	}
}
