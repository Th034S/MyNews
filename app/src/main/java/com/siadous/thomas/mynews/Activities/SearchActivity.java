package com.siadous.thomas.mynews.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.siadous.thomas.mynews.Fragments.SearchFragment;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.result_list.ResultActivity;
import com.siadous.thomas.mynews.result_list.ResultFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

    SearchFragment searchFragment;

    ArrayList<String> categories;
    Button searchButton;
    EditText editTextSearch;
    CheckBox politicsCheckBox;
    CheckBox sportsCheckBox;
    CheckBox travelCheckBox;
    CheckBox businessCheckBox;
    CheckBox artsCheckBox;
    CheckBox entrepreneursCheckBox;
    String keyword = " ";
    ResultFragment resultFragment;
    Spinner beginDateSpinner;
    Spinner endDateSpinner;
    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

         Log.d("TAG", "on create SearchActivity");

        configureToolbar();

        initUI();
/**
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(
                getApplicationContext(), SearchActivity.this, year, month, day);


        beginDateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });
**/

        searchButton.setEnabled(false);

        enableSearchButton();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveKeyWord();
                retrieveCategories();
                launchResultFragmentWithBundle();
            }
        });

        configureAndShowSearchFragment();
    }


   private void configureToolbar() {
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


    private void launchResultFragmentWithBundle() {

            Intent intent= new Intent(SearchActivity.this ,ResultActivity.class);

            intent.putExtra("keyword", keyword);
            intent.putExtra("categories", categories);

            startActivity(intent);


    }

    private void enableSearchButton() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void retrieveCategories() {
        categories = new ArrayList<>();
        if (politicsCheckBox.isChecked()) {
            categories.add("politics");
        }
        else if (artsCheckBox.isChecked()) {
            categories.add("arts");
        }
        else if(entrepreneursCheckBox.isChecked()) {
            categories.add("entrepreneurs");
        }
        else if(businessCheckBox.isChecked()) {
            categories.add("business");
        }
        else if(travelCheckBox.isChecked()) {
            categories.add("travel");
        }
        else if(sportsCheckBox.isChecked()) {
            categories.add("sports");
        }
    }

    private void retrieveKeyWord() {
        keyword = editTextSearch.getText().toString();
    }


    private void initUI() {
        searchButton = findViewById(R.id.search_button_fragment);
        editTextSearch = findViewById(R.id.edit_text);
        politicsCheckBox = findViewById(R.id.checkBox_politics);
        sportsCheckBox = findViewById(R.id.checkBox_sports);
        travelCheckBox = findViewById(R.id.checkBox_travel);
        businessCheckBox = findViewById(R.id.checkBox_business);
        artsCheckBox = findViewById(R.id.checkBox_arts);
        entrepreneursCheckBox = findViewById(R.id.checkBox_entrepreneurs);
        beginDateSpinner = findViewById(R.id.spinner_begin_date);
        endDateSpinner = findViewById(R.id.spinner_end_date);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
