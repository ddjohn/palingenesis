package se.avelon.daidalos;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import se.avelon.daidalos.fragments.AbstractFragment;
import se.avelon.daidalos.fragments.AudioFragment;
import se.avelon.daidalos.fragments.BatteryFragment;
import se.avelon.daidalos.fragments.BluetoothFragment;
import se.avelon.daidalos.fragments.CameraFragment;
import se.avelon.daidalos.fragments.CellularFragment;
import se.avelon.daidalos.fragments.MediaFragment;
import se.avelon.daidalos.fragments.DebugFragment;
import se.avelon.daidalos.fragments.MapFragment;
import se.avelon.daidalos.fragments.NavigationFragment;
import se.avelon.daidalos.fragments.SensorFragment;
import se.avelon.daidalos.fragments.ScreenFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private AbstractFragment[] fragments= {
            new DebugFragment(),
            new ScreenFragment(),
            new AudioFragment(),
            new BatteryFragment(),
            new BluetoothFragment(),
            new CameraFragment(),
            new CellularFragment(),
            new MapFragment(),
            new MediaFragment(),
            new NavigationFragment(),
            new SensorFragment(),
    };

    public PagerAdapter(Activity activity, FragmentManager fm) {
        super(fm);

        TabLayout tabLayout = (TabLayout)activity.findViewById(R.id.tab_layout);
        for(AbstractFragment fragment : fragments) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setIcon(fragment.getIcon());
            //tab.setText(fragment.getTitle());
            tabLayout.addTab(tab);
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)activity.findViewById(R.id.pager);
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}