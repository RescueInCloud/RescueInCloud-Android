package es.ucm.ric.activities.adapters;

import es.ucm.ric.R;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DrawerAdapter extends ArrayAdapter<String>{

	private LayoutInflater inflater = null;
	private String[] opciones;
	
	public DrawerAdapter(Activity context, int resource, String[] objects) {
		super(context, resource, objects);
		this.opciones = objects;
		this.inflater = context.getLayoutInflater();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		View v = convertView;
		
		if(v==null){
			 v = inflater.inflate(R.layout.activity_drawer, null);
		}

        String cat = opciones[position];
        
        switch(position){
        	case 0 : 
        }
        
        
//            v = inflater.inflate(R.layout.activity_drawer, null);
//            TextView tt = (TextView) v.findViewById(R.id.title);
                  
        

        return v;
    }
	

	

}
