package com.siadous.thomas.mynews.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.siadous.thomas.mynews.R;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {


    ArrayList<String> categories;
    EditText editTextSearch;
    CheckBox politicsCheckBox;
    CheckBox sportsCheckBox;
    CheckBox travelCheckBox;
    CheckBox businessCheckBox;
    CheckBox artsCheckBox;
    CheckBox entrepreneursCheckBox;
    Switch aSwitch;
    String keyword = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        configureToolbar();

        initUI();

        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retrieveKeyWord();
                retrieveCategories();
            }
        });
    }




    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        try {
            getSupportActionBar().setTitle("Notifications");

            // display back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        } catch (NullPointerException e) {
            Log.e("your app", e.toString());
        }

    }

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
        editTextSearch = findViewById(R.id.edit_text);
        politicsCheckBox = findViewById(R.id.checkBox_politics);
        sportsCheckBox = findViewById(R.id.checkBox_sports);
        travelCheckBox = findViewById(R.id.checkBox_travel);
        businessCheckBox = findViewById(R.id.checkBox_business);
        artsCheckBox = findViewById(R.id.checkBox_arts);
        entrepreneursCheckBox = findViewById(R.id.checkBox_entrepreneurs);
        aSwitch = findViewById(R.id.switch1);
    }
}


