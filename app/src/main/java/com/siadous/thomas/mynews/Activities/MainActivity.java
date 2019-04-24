package com.siadous.thomas.mynews.Activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.siadous.thomas.mynews.Fragments.HomeFragment;
import com.siadous.thomas.mynews.R;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.configureAndShowMainFragment();

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


    private void configureAndShowMainFragment(){
        // A - Get FragmentManager (Support) and Try to find existing instance of fragment in FrameLayout container
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        if (homeFragment == null) {
            // B - Create new main fragment
            homeFragment = new HomeFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_main, homeFragment)
                    .commit();
        }
    }

}
