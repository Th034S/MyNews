package com.siadous.thomas.mynews.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.most_popular_list.MostPopularDetailFragment;

import java.util.List;

public class SearchFragment extends Fragment {

    public SearchFragment() { }


    List<String> categories;
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

        view = inflater.inflate(R.layout.search, container, false);

        initUI();

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
        if (!(keyword.equals(" ") && categories.isEmpty())) {
            resultFragment = new ResultFragment();
            Bundle args = new Bundle();
            args.putString("key", keyword);
            resultFragment.setArguments(args);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.linear_layout_search, resultFragment)
                    .addToBackStack(null)
                    .commit();
        }

    }

    private void retrieveCategories() {
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
        editTextSearch = view.findViewById(R.id.edit_text_search);
        politicsCheckBox = view.findViewById(R.id.checkBox_politics);
        sportsCheckBox = view.findViewById(R.id.checkBox_sports);
        travelCheckBox = view.findViewById(R.id.checkBox_travel);
        businessCheckBox = view.findViewById(R.id.checkBox_business);
        artsCheckBox = view.findViewById(R.id.checkBox_arts);
        entrepreneursCheckBox = view.findViewById(R.id.checkBox_entrepreneurs);
    }


}
