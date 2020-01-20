package com.siadous.thomas.mynews.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siadous.thomas.mynews.model.ArticleSearch.Docs;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.utils.DateUtils;
import com.siadous.thomas.mynews.result_list.ResultActivity;

import java.util.List;

public class ResultAdapter  extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder> {


    private ResultActivity resultActivity;
    private List<Docs> resultList;
    private Docs result = null;


    public ResultAdapter(ResultActivity resultActivity, List<Docs> resultList) {
        this.resultActivity = resultActivity;
        this.resultList = resultList;
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
        result = resultList.get(position);

        holder.fragmentItemTitle.setText(result.getSnippet());

        if(result.getSection_name() != null && result.getSubsection_name() != null) {
            category = result.getSection_name() + " > " + result.getSubsection_name();
            holder.fragmentCategory.setText(category);
        }
        else {
            category = result.getSection_name();
            holder.fragmentCategory.setText(category);
        }

        holder.fragmentDate.setText(DateUtils.configureFormatDate(result.getPub_date()));

        if (result.getMultimedia().length >= 1) {
            // loading album cover using Glide library
            Glide.with(holder.getContext())
                    .load("https://static01.nyt.com/"
                            + result.getMultimedia()[result.getMultimedia().length - 1].getUrl())
                    .into(holder.fragmentImageView);
        }

    }


    // Le nombre d'éléments à afficher
    @Override
    public int getItemCount() {
        Log.e("testsiadous", String.valueOf(resultList.size()));
        return resultList.size();
    }


    public Docs getArticle(int position){
        return this.resultList.get(position);
    }

    public void updateList( List<Docs> resultList ) {
        this.resultList = resultList;
    }
/*
    class MyViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoadImage;
        TextView fragmentItemTitle;
        TextView fragmentDate;
        ImageView fragmentImageView;
        TextView fragmentCategory;



        MyViewHolder(View view) {
            super(view);

            fragmentItemTitle = view.findViewById(R.id.fragment_item_title);
            fragmentDate = view.findViewById(R.id.fragment_date);
            fragmentImageView = view.findViewById(R.id.fragment_main_item_image);
            fragmentCategory = view.findViewById(R.id.fragment_category);
            //pbLoadImage = itemView.findViewById(R.id.pb_load_image);


        }
        Context getContext() {
            return fragmentItemTitle.getContext();
        }
    }
*/
}
