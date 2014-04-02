package es.ucm.ric.activities.adapters;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import es.ucm.ric.R;
import es.ucm.ric.model.IListable;

public class MiAdapter extends ArrayAdapter<IListable>{

	private LayoutInflater inflater = null;
	private ArrayList<IListable> lista;
	private int[] imagenes_por_defecto;
	
	public MiAdapter(Activity context, ArrayList<IListable> lista, int[] imagenes_por_defecto) {
		super(context, R.layout.item_lista, lista);
		this.lista = lista;
		this.inflater = context.getLayoutInflater();
		this.imagenes_por_defecto = imagenes_por_defecto;
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
		IListable f = lista.get(position);
	    holder.titulo.setText(f.getTitulo());
	    holder.subtitulo.setText(f.getSubtitulo());
	    
	    boolean imagen_cargada = false;
	    if(f.getRuta()!=null){
	    	File imgFile = new  File(f.getRuta());
	    	if(imgFile.exists()){

	    	    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
	    	    holder.imagen.setImageBitmap(myBitmap);
	    	    imagen_cargada = true;
	    	}
	    }
	    
	    if(!imagen_cargada){
		    holder.imagen.setImageResource(this.imagenes_por_defecto[position%2]);
	    }
	    
        return v;
    }
	
	
	static class ViewHolder {
	    public TextView titulo;
	    public TextView subtitulo;
	    public ImageView imagen;
	}

	

}
