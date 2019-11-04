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
import android.widget.TextView;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.result_list.ResultActivity;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String categories = "";
    Button searchButton;
    EditText editTextSearch;
    CheckBox politicsCheckBox;
    CheckBox sportsCheckBox;
    CheckBox travelCheckBox;
    CheckBox businessCheckBox;
    CheckBox artsCheckBox;
    CheckBox entrepreneursCheckBox;
    TextView textViewBeginSetText;
    TextView textViewEndSetText;
    String keyword = " ";
    ConstraintLayout constraintLayoutBeginDate;
    ConstraintLayout constraintLayoutEndDate;
    int beginDate = 0;
    int endDate = 0;
    int day;
    int month;
    int year;

    DatePickerDialog datePickerDialogBegin;
    DatePickerDialog datePickerDialogEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        configureToolbar();

        initUI();

        configureDatePickerAndClickDate();

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

    }


    private void configureDatePickerAndClickDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        long timeNowInmillis = c.getTimeInMillis() + 86_400_000;

        datePickerDialogBegin = new DatePickerDialog(
                SearchActivity.this,R.style.DialogTheme , SearchActivity.this, year, month, day);

        datePickerDialogBegin.getDatePicker().setMaxDate(timeNowInmillis);


        datePickerDialogEnd = new DatePickerDialog(
                SearchActivity.this, R.style.DialogTheme, SearchActivity.this, year, month, day);

        datePickerDialogEnd.getDatePicker().setMaxDate(timeNowInmillis);



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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void launchResultActivity() {
        
            Intent intent= new Intent(SearchActivity.this ,ResultActivity.class);
            intent.putExtra("keyword", keyword);
            intent.putExtra("categories", categories);
            if(beginDate != 0 && endDate != 0) {
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
        textViewBeginSetText = findViewById(R.id.text_view_begin_set_text);
        textViewEndSetText = findViewById(R.id.text_view_end_set_text);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int ye, int mo, int d) {
        Log.d("Search", " on DateSet GOOD !");

        storeDateAndDisplayDate(ye, mo, d);
    }

    private void storeDateAndDisplayDate(int ye, int mo, int d){

        int m = mo + 1;
        int o = 0;

        if(datePickerDialogBegin.isShowing()) {
            if (d < 10 || m < 10)
            {
                if (m < 10) {
                    beginDate = Integer.valueOf("" + ye + o + m + d);
                    textViewBeginSetText.setText(d + "/" + o + m + "/" + ye);
                }
                if (d < 10) {
                    beginDate = Integer.valueOf("" + ye + m + o + d);
                    textViewBeginSetText.setText("" + o + d + "/" + m + "/" + ye);
                }
                if(d < 10 && m < 10) {
                    beginDate = Integer.valueOf("" + ye + o + m + o + d);
                    textViewBeginSetText.setText("" + o + d + "/" + o + m + "/" + ye);
                }
            } else
            {
                beginDate = Integer.valueOf("" + ye + m + d);
                textViewBeginSetText.setText("" + d + "/" + m + "/" + ye);
            }

        }
        if(datePickerDialogEnd.isShowing()) {
            if (d < 10 || m < 10) {
                if (m < 10) {
                    endDate = Integer.valueOf("" + ye + o + m + d);
                    textViewEndSetText.setText(d + "/" + o + m + "/" + ye);
                }
                if (d < 10) {
                    endDate = Integer.valueOf("" + ye + m + o + d);
                    textViewEndSetText.setText("" + o + d + "/" + m + "/" + ye);
                }
                if (d < 10 && m < 10) {
                    endDate = Integer.valueOf("" + ye + o + m + o + d);
                    textViewEndSetText.setText("" + o + d + "/" + o + m + "/" + ye);
                }
            } else
            {
                endDate = Integer.valueOf("" + ye + m + d);
                textViewEndSetText.setText("" + d + "/" + m + "/" + ye);
            }

        }
    }



}
