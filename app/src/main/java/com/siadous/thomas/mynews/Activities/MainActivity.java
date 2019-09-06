package com.siadous.thomas.mynews.Activities;

import android.content.Intent;
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

        configureToolbar();

        this.configureAndShowHomeFragment();


    }


    // What to do when click on Search and params button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //3 - Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_activity_main_params:
                Toast.makeText(this, "Il n'y a rien à paramétrer ici, passez votre chemin...", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_activity_main_search:
                launchSearchActivity();
                Toast.makeText(this, "Recherche indisponible, demandez plutôt l'avis de Google, c'est mieux et plus rapide.", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void configureToolbar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    private void launchSearchFragment() {
        searchFragment = new SearchFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout_main , searchFragment)
                .addToBackStack(null)
                .commit();

    }


    private void launchSearchActivity() {
        Intent myIntent = new Intent(MainActivity.this, SearchActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


    // Add menu in Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }



    private void configureAndShowHomeFragment(){
        homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        if (homeFragment == null) {

            homeFragment = new HomeFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_main, homeFragment)
                    .commit();
        }
    }

}
