package com.siadous.thomas.mynews.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siadous.thomas.mynews.education_list.EducationFragment;
import com.siadous.thomas.mynews.most_popular_list.MostPopularFragment;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesFragment;

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }

    // Display each fragment for each tab
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return EducationFragment.newInstance();
            default:
                return null;
        }
    }

    // How many tabs do i want
    @Override
    public int getCount() {
        return 3;
    }

    // Add title to each tab
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Top Stories";
            case 1:
                return "Most Popular";
            case 2:
                return "Education";
            default:
                return null;
        }
    }
}
