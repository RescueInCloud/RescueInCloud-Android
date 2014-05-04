package es.ucm.ric.activities.fragments.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import es.ucm.ric.dao.FarmacoDAO;
import es.ucm.ric.model.Farmaco;

public class FragmentDetalleFarmaco extends FragmentDetalle{

	private String id;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		
		id = getArguments().getString("ID");
		
		return v;
	}
	
	
	@Override
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
		
		FarmacoDAO dao = new FarmacoDAO();
		Farmaco f = dao.get(id);
	}
}
