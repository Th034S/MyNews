package com.siadous.thomas.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.siadous.thomas.mynews.Fragments.SearchFragment;
import com.siadous.thomas.mynews.R;

public class SearchActivity extends AppCompatActivity {


    SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

         Log.d("TAG", "on create SearchActivity");


         Toolbar toolbar = findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);


        try {
            getSupportActionBar().setTitle("Search Articles");

            // display back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);


        } catch (NullPointerException e) {
            Log.e("your app", e.toString());
        }


         configureAndShowSearchFragment();

    }

    private void configureAndShowSearchFragment() {
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.linear_layout_search_activity);

        if (searchFragment == null) {

            searchFragment = new SearchFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.linear_layout_search_activity, searchFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SearchActivity.this, MainActivity.class));
        finish();
    }
}
