package com.siadous.thomas.mynews.Adapters;

import android.app.MediaRouteButton;
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
import com.siadous.thomas.mynews.Model.TopStories;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesFragment;

import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder> {

    private TopStoriesFragment topStoriesFragment;
    private List<TopStories> topStoriesList;
    //private List<TopStories> originalTopStoriesList;


    // Constructeur
    public TopStoriesAdapter(TopStoriesFragment topStoriesFragment, List<TopStories> topStoriesList) {
        this.topStoriesFragment = topStoriesFragment;
        this.topStoriesList = topStoriesList;
       // this.originalTopStoriesList = topStoriesList;
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

        TopStories topStories = topStoriesList.get(position);

        holder.fragmentItemTitle.setText(topStories.getTitle());
        holder.fragmentDate.setText(topStories.getPublished_date());

        // loading album cover using Glide library
        Glide.with(holder.getContext())
                .load(topStories.getMultimedia()[topStories.getMultimedia().length - 1].getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.pbLoadImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pbLoadImage.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions())
                .into(holder.fragmentImageView);

    }

    // Le nombre d'éléments à afficher
    @Override
    public int getItemCount() {
        Log.e("testsiadous", String.valueOf(topStoriesList.size()));
        return topStoriesList.size();
    }


    // On référence les éléments de movie card
    class MyViewHolder extends RecyclerView.ViewHolder {

        ProgressBar pbLoadImage;
        TextView fragmentItemTitle;

        TextView fragmentDate;

        ImageView fragmentImageView;


        MyViewHolder(View itemView) {
            super(itemView);

            fragmentItemTitle = itemView.findViewById(R.id.fragment_item_title);
            fragmentDate = itemView.findViewById(R.id.fragment_date);
            fragmentImageView = itemView.findViewById(R.id.fragment_main_item_image);
            pbLoadImage = itemView.findViewById(R.id.pb_load_image);

        }
        Context getContext() {
            return fragmentItemTitle.getContext();
        }
    }
}
