package es.ucm.ric.activities;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import es.ucm.ric.R;
import es.ucm.ric.tools.BaseActivity;
import es.ucm.ric.tools.JustifiedTextView;

public class NotaActivity extends BaseActivity{


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_nota);
		

        //some random long text
        String myText="Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. " +
        		"Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, " +
        		"porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna." +
        		"Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, Ricardo " +
        		"is es a un awesome tío guy genial."+
        		"\nDuis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia " +
        		"odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. " +
        		"Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. " +
        		"Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, " +
        		"porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna." +
        		"\nDuis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia " +
        		"odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. " +
        		"porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna." +
        		"\nDuis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia " +
        		"odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. " +
        		"Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh."+
        		"Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.";

        JustifiedTextView jtv= new JustifiedTextView(getApplicationContext(), myText);
//        LinearLayout place = (LinearLayout) findViewById(R.id.notas_lo);
//        place.addView(jtv);
        ScrollView place = (ScrollView) findViewById(R.id.notas_lo);
        place.addView(jtv);
        
       
        
		
	}
}
