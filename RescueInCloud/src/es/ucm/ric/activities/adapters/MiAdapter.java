package es.ucm.ric.activities.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.model.Farmaco;

public class MiAdapter extends ArrayAdapter<Farmaco>{

	private LayoutInflater inflater = null;
	private ArrayList<Farmaco> lista;
	
	public MiAdapter(Activity context, ArrayList<Farmaco> lista) {
		super(context, R.layout.item_lista, lista);
		this.lista = lista;
		this.inflater = context.getLayoutInflater();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		View v = convertView;
		
		//re-uso de views
		if(v==null){
			 v = inflater.inflate(R.layout.item_lista, null);
			 
			 ViewHolder viewHolder = new ViewHolder();
		     viewHolder.titulo = (TextView) v.findViewById(R.id.titulo);
		     viewHolder.subtitulo = (TextView) v.findViewById(R.id.subtitulo);
		     viewHolder.imagen = (ImageView) v.findViewById(R.id.imagen);
		     v.setTag(viewHolder);
		}

		//asignando valores
		ViewHolder holder = (ViewHolder) v.getTag();
		Farmaco f = lista.get(position);
	    holder.titulo.setText(f.getNombre_farmaco());
	    holder.subtitulo.setText(f.getDescripcion_farmaco());
	    
	    if( (position%2)==0)
	    	holder.imagen.setImageResource(R.drawable.frasco_medicina_roja);
	    else
	    	holder.imagen.setImageResource(R.drawable.frasco_medicina_verde);
                  
	    
        return v;
    }
	
	
	static class ViewHolder {
	    public TextView titulo;
	    public TextView subtitulo;
	    public ImageView imagen;
	}

	

}
