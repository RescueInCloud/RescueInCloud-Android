package es.ucm.ric.activities;

import es.ucm.ric.R;
import es.ucm.ric.tools.JustifiedTextView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class FragmentNota extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, 
			                 ViewGroup container, 
			                 Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.activity_nota_titulo_fijo, container, false);
	}
	
	public void onActivityCreated(Bundle state) { 
		super.onActivityCreated(state);
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

        JustifiedTextView jtv= new JustifiedTextView(getActivity(), myText);
//        LinearLayout place = (LinearLayout) findViewById(R.id.notas_lo);
//        place.addView(jtv);
        ScrollView place = (ScrollView) getActivity().findViewById(R.id.notas_lo);
        place.addView(jtv);
        
	}
	
}
