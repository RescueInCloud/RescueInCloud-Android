package es.ucm.ric.activities.fragments;

import es.ucm.ric.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentInit extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_init, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
	}
	
	public void onClick(View v){
		
		
	}

}
