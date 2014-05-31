package es.ucm.ric.activities.fragments.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import es.ucm.ric.R;
import es.ucm.ric.dao.NotasDAO;
import es.ucm.ric.model.Nota;
import es.ucm.ric.tools.BaseActivity;
import es.ucm.ric.tools.JustifiedTextView;

public class FragmentNota extends BaseActivity{
	
	private String id;

	
	@Override
	public void onCreate(Bundle state) { 
		super.onCreate(state);
		setContentView(R.layout.fragment_nota);

	    Intent intent = getIntent();
	    id = intent.getStringExtra("id");
	    
	    if(id!=null){

			NotasDAO dao = new NotasDAO();
			Nota n = dao.get(id);
			
			String myText = n.getDescripcion();
			
	        JustifiedTextView jtv= new JustifiedTextView(this, myText);
	//        LinearLayout place = (LinearLayout) findViewById(R.id.notas_lo);
	//        place.addView(jtv);
	        ScrollView place = (ScrollView) findViewById(R.id.notas_lo);
	        place.addView(jtv);
	    }
        
	}
	
}
