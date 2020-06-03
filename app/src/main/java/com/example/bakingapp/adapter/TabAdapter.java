package com.example.bakingapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bakingapp.fragment.IngredientFragment;
import com.example.bakingapp.fragment.StepsFragment;

public class TabAdapter extends FragmentPagerAdapter {


    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new IngredientFragment();
                break;
            case 1:
                fragment = new StepsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        //Shows two pages in the layout
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Ingredients";
        } else if (position == 1) {
            title = "Recipe Steps";
        }
        return title;
    }
}
