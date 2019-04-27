package com.siadous.thomas.mynews.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.siadous.thomas.mynews.Model.TopStories.TopStories;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesFragment;

import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder> {

    private TopStoriesFragment topStoriesFragment;
    private List<TopStories> topStoriesList;
    private TopStories topStories;



    public TopStoriesAdapter(TopStoriesFragment topStoriesFragment, List<TopStories> topStoriesList) {
        this.topStoriesFragment = topStoriesFragment;
        this.topStoriesList = topStoriesList;

    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        String category;
        topStories = topStoriesList.get(position);

        holder.fragmentItemTitle.setText(topStories.getTitle());
        if(!topStories.getSection().equals("") && !topStories.getSubsection().equals("")) {
            category = topStories.getSection() + " > " + topStories.getSubsection();
            holder.fragmentCategory.setText(category);
        }
        else {
            category = topStories.getSection();
            holder.fragmentCategory.setText(category);
        }
        holder.fragmentDate.setText(configureFormatDate());
        if (topStories.getMultimedia().length >= 1) {
            // loading album cover using Glide library
            Glide.with(holder.getContext())
                    .load(topStories.getMultimedia()[topStories.getMultimedia().length - 1].getUrl())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                           // holder.pbLoadImage.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                           // holder.pbLoadImage.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .apply(new RequestOptions())
                    .into(holder.fragmentImageView);
        }


    }



    // Le nombre d'éléments à afficher
    @Override
    public int getItemCount() {
        Log.e("testsiadous", String.valueOf(topStoriesList.size()));
        return topStoriesList.size();
    }

    public String configureFormatDate() {
        String date = topStories.getPublished_date();
        String[] split = date.split("T");
        date = split[0];
        split = date.split("-");
        String[] year = split[0].split("0");
        date = split[2] + "/" + split[1] + "/" + year[1];
        return date;
    }

    public TopStories getArticle(int position){
        return this.topStoriesList.get(position);
    }

    public void updateList( List<TopStories> topStoriesList) {
        this.topStoriesList = topStoriesList;
    }

    // On référence les éléments de movie card
    class MyViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoadImage;
        TextView fragmentItemTitle;
        TextView fragmentDate;
        ImageView fragmentImageView;
        TextView fragmentCategory;



        MyViewHolder(View itemView) {
            super(itemView);

            fragmentItemTitle = itemView.findViewById(R.id.fragment_item_title);
            fragmentDate = itemView.findViewById(R.id.fragment_date);
            fragmentImageView = itemView.findViewById(R.id.fragment_main_item_image);
            fragmentCategory = itemView.findViewById(R.id.fragment_category);
            //pbLoadImage = itemView.findViewById(R.id.pb_load_image);


        }
        Context getContext() {
            return fragmentItemTitle.getContext();
        }
    }


}
