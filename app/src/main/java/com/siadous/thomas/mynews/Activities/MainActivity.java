package com.siadous.thomas.mynews.Activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.siadous.thomas.mynews.Fragments.HomeFragment;
import com.siadous.thomas.mynews.Fragments.SearchFragment;
import com.siadous.thomas.mynews.R;



public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    SearchFragment searchFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/**
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
**/

        this.configureAndShowHomeFragment();


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                launchSearchFragment();
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void launchSearchFragment() {
        searchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_main , searchFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }



    private void configureAndShowHomeFragment(){
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
