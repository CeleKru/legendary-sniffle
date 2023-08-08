package com.example.naujas;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

    int tabcount;

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new LTFragment();
            case 2:
                return new SportsFragment();
            case 3:
                return new HealthFragment();
            case 4:
                return new EntertainmentFragment();
            case 5:
                return new TechnologyFragment();

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
