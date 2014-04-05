package es.ucm.ric.activities.adapters;

import java.util.ArrayList;
import java.util.List;
 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
/**
 * 
 * @author Ricardo Champa
 * 
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
 
    // List of fragments which are going to set in the view pager widget
    private List<Fragment> fragments;
 
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
    }
 
    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }
 
    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }
 
    @Override
    public int getCount() {
        return this.fragments.size();
    }
 
}