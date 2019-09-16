package com.siadous.thomas.mynews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.result_list.ResultFragment;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    public SearchFragment() { }


    ArrayList<String> categories;
    View view;
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);

        initUI();

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

        return view;
    }

    private void launchResultFragmentWithBundle() {
        if (!(categories.isEmpty())) {
            resultFragment = new ResultFragment();
            Bundle args = new Bundle();
            args.putString("keyword", keyword);
            args.putStringArrayList("categories", categories);
            resultFragment.setArguments(args);

            FragmentManager fm = getActivity().getSupportFragmentManager();

            for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
                fm.popBackStack();
            }

            fm.beginTransaction()
                    .replace(R.id.frame_layout_search_fragment, resultFragment)
                    .addToBackStack(null)
                    .commit();
        }

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
        searchButton = view.findViewById(R.id.search_button_fragment);
        editTextSearch = view.findViewById(R.id.edit_text);
        politicsCheckBox = view.findViewById(R.id.checkBox_politics);
        sportsCheckBox = view.findViewById(R.id.checkBox_sports);
        travelCheckBox = view.findViewById(R.id.checkBox_travel);
        businessCheckBox = view.findViewById(R.id.checkBox_business);
        artsCheckBox = view.findViewById(R.id.checkBox_arts);
        entrepreneursCheckBox = view.findViewById(R.id.checkBox_entrepreneurs);

    }


}
