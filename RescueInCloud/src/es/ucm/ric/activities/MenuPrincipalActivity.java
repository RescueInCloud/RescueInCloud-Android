package es.ucm.ric.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import es.ucm.ric.R;
import es.ucm.ric.tools.BaseActivity;

public class MenuPrincipalActivity extends BaseActivity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/Trykker-Regular.ttf");
        
		TextView titulo = (TextView)findViewById(R.id.titulo_protocolo);
		titulo.setTypeface(font);
		
		TextView subtitulo = (TextView)findViewById(R.id.subtitulo_protocolo);
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
		
		switch (v.getId()){
			case R.id.protocolos:
				Toast.makeText(this, "Has pulsado protocolos", Toast.LENGTH_SHORT).show();
				break;
			case R.id.farmacos:
				Toast.makeText(this, "Has pulsado farmacos", Toast.LENGTH_SHORT).show();
				break;
			case R.id.notas:
				Intent intent = new Intent(this, NotaActivity.class);
				startActivity(intent);
				break;
		}
	}

}
