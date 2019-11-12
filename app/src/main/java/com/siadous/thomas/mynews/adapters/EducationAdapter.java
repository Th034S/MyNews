package com.siadous.thomas.mynews.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.siadous.thomas.mynews.model.Education.Docs;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.utils.DateUtils;
import com.siadous.thomas.mynews.education_list.EducationFragment;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder>{

    private EducationFragment educationFragment;
    private List<Docs> educationList;
    private Docs education = null;


    public EducationAdapter(EducationFragment educationFragment, List<Docs> educationList) {
        this.educationFragment = educationFragment;
        this.educationList = educationList;
    }

    @NonNull
    @Override
    public TopStoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new TopStoriesAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull final TopStoriesAdapter.MyViewHolder holder, final int position) {
        String category;
        education = educationList.get(position);

        holder.fragmentItemTitle.setText(education.getSnippet());

        if(education.getSection_name() != null && education.getSubsection_name() != null) {
            category = education.getSection_name() + " > " + education.getSubsection_name();
            holder.fragmentCategory.setText(category);
        }
        else {
            category = education.getSection_name();
            holder.fragmentCategory.setText(category);
        }

        holder.fragmentDate.setText(DateUtils.configureFormatDate(education.getPub_date()));

        if (education.getMultimedia().length >= 1) {
            // loading album cover using Glide library
            Glide.with(holder.getContext())
                    .load("https://static01.nyt.com/"
                            + education.getMultimedia()[education.getMultimedia().length - 1].getUrl())
                    .into(holder.fragmentImageView);
        }


    }

    // Le nombre d'éléments à afficher
    @Override
    public int getItemCount() {
        Log.e("testsiadous", String.valueOf(educationList.size()));
        return educationList.size();
    }


    public Docs getArticle(int position){
        return this.educationList.get(position);
    }

    public void updateList( List<Docs> educationList ) {
        this.educationList = educationList;
    }


}
