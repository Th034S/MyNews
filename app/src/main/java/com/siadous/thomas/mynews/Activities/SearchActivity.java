package com.siadous.thomas.mynews.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.result_list.ResultActivity;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

  //  SearchFragment searchFragment;

    String categories = "";
    Button searchButton;
    EditText editTextSearch;
    CheckBox politicsCheckBox;
    CheckBox sportsCheckBox;
    CheckBox travelCheckBox;
    CheckBox businessCheckBox;
    CheckBox artsCheckBox;
    CheckBox entrepreneursCheckBox;
    String keyword = " ";
   // ResultFragment resultFragment;
    ConstraintLayout constraintLayoutBeginDate;
    ConstraintLayout constraintLayoutEndDate;
    DatePickerDialog datePickerDialog;
    int beginDate = 0;
    int endDate = 0;
    int day;
    int month;
    int year;
    int day2;
    int month2;
    int year2;
    DatePickerDialog datePickerDialogBegin;
    DatePickerDialog datePickerDialogEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

         Log.d("TAG", "on create SearchActivity");

        configureToolbar();

        initUI();


        configureClickDate();


        searchButton.setEnabled(false);

        enableSearchButton();

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveKeyWord();
                retrieveCategories();
                launchResultActivity();
            }
        });

        //configureAndShowSearchFragment();
    }


    private void configureClickDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialogBegin = new DatePickerDialog(
                SearchActivity.this,R.style.DialogTheme , SearchActivity.this, year, month, day);


        final Calendar c1 = Calendar.getInstance();
        year2 = c1.get(Calendar.YEAR);
        month2 = c1.get(Calendar.MONTH);
        day2 = c1.get(Calendar.DAY_OF_MONTH);

        datePickerDialogEnd = new DatePickerDialog(
                SearchActivity.this, R.style.DialogTheme, SearchActivity.this, year2, month2, day2);

        constraintLayoutBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogBegin.show();
            }
        });

        constraintLayoutEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialogEnd.show();
            }
        });
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


/**
    private void configureAndShowSearchFragment() {
        searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.linear_layout_search_activity);

        if (searchFragment == null) {

            searchFragment = new SearchFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.linear_layout_search_activity, searchFragment)
                    .commit();
        }
    }
**/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void launchResultActivity() {

            Intent intent= new Intent(SearchActivity.this ,ResultActivity.class);

            intent.putExtra("keyword", keyword);
            intent.putExtra("categories", categories);
            if(beginDate!= 0 && endDate !=0) {
                intent.putExtra("begin_date", beginDate);
                intent.putExtra("end_date", endDate);
            }

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

        if (politicsCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "politics";
            } else {
                categories = categories + ", politics";
            }
        }
        if (artsCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "arts";
            } else {
                categories = categories + ", arts";
            }
        }
        if(entrepreneursCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "entrepreneurs";
            } else {
                categories = categories + ", entrepreneurs";
            }
        }
        if(businessCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "business";
            } else {
                categories = categories + ", business";
            }
        }
        if(travelCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "travel";
            } else {
                categories = categories + ", travel";
            }
        }
        if(sportsCheckBox.isChecked()) {
            if (categories.equals("")) {
                categories = "sports";
            } else {
                categories = categories + ", sports";
            }
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
        constraintLayoutBeginDate = findViewById(R.id.constraint_layout_begin_date);
        constraintLayoutEndDate = findViewById(R.id.constraint_layout_end_date);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        beginDate = year + month + day;
        endDate = year2 + month2 + day2;
    }
}
