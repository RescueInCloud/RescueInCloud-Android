package es.ucm.ric.activities.fragments;


import es.ucm.ric.R;
import es.ucm.ric.activities.adapters.MyFragmentPagerAdapter;
import es.ucm.ric.activities.animations.DepthPageTransformer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentPaginador extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = inflater.inflate(R.layout.fragment_paginador, container,
				false);
		ViewPager pager = (ViewPager) result.findViewById(R.id.pager);
		pager.setPageTransformer(true, new DepthPageTransformer());
		pager.setAdapter(buildAdapter());

		return (result);
	}

	private PagerAdapter buildAdapter() {
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getChildFragmentManager());
		adapter.addFragment(new FragmentNuevoProtocolo());
		adapter.addFragment(new FragmentMenuPrincipal());
		return adapter;
	}
}