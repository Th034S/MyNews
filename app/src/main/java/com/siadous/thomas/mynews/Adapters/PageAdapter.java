package com.siadous.thomas.mynews.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siadous.thomas.mynews.Fragments.BusinessFragment;
import com.siadous.thomas.mynews.Fragments.MostPopularFragment;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesFragment;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return BusinessFragment.newInstance();
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Top Stories";
            case 1:
                return "Most Popular";
            case 2:
                return "Business";
            default:
                return null;
        }
    }
}
