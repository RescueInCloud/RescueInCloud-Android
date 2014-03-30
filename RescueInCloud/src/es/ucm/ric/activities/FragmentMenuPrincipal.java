package es.ucm.ric.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import es.ucm.ric.R;

public class FragmentMenuPrincipal extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.activity_menu, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
		
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Trykker-Regular.ttf");
        
		TextView titulo = (TextView)getActivity().findViewById(R.id.titulo_protocolo);
		titulo.setTypeface(font);
		
		TextView subtitulo = (TextView)getActivity().findViewById(R.id.subtitulo_protocolo);
		subtitulo.setTypeface(font);
		
//		Typeface fontRoboto = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Medium.ttf");
//        
//		TextView tituloF = (TextView)findViewById(R.id.titulo_farmaco);
//		tituloF.setTypeface(fontRoboto);
//		
//		TextView subtituloF = (TextView)findViewById(R.id.subtitulo_farmaco);
//		subtituloF.setTypeface(fontRoboto);
		
	}
	
	public void onClick(View v){
		Intent intent;
		
		switch (v.getId()){
			case R.id.protocolos:
				Toast.makeText(getActivity(), "Has pulsado protocolos", Toast.LENGTH_SHORT).show();
				break;
			case R.id.farmacos:
				Toast.makeText(getActivity(), "Has pulsado farmacos", Toast.LENGTH_SHORT).show();
				break;
			case R.id.notas:
				intent = new Intent(getActivity(), NotaActivity.class);
				startActivity(intent);
				break;
			case R.id.test:
				intent = new Intent(getActivity(), TestActivity.class);
				startActivity(intent);
				break;
		}
	}

}
