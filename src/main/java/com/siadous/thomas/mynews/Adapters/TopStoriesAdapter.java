package com.siadous.thomas.mynews.Adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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
import com.siadous.thomas.mynews.Utils.ApiClient;
import com.siadous.thomas.mynews.top_stories_list.TopStoriesFragment;


import java.util.List;
import java.util.Locale;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.MyViewHolder> {

    private TopStoriesFragment topStoriesFragment;
    private List<TopStories> topStoriesList;
    private List<TopStories> originalTopStoriesList;


    // Constructeur
    public TopStoriesAdapter(TopStoriesFragment topStoriesFragment, List<TopStories> topStoriesList) {
        this.topStoriesFragment = topStoriesFragment;
        this.topStoriesList = topStoriesList;
        this.originalTopStoriesList = topStoriesList;
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
        Glide.with(TopStoriesFragment)
                .load(ApiClient.IMAGE_BASE_URL + topStories.getThumbPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.pbLoadImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.fragmentImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topStoriesFragment.onMovieItemClick(position);
            }
        });

    }

    // Le nombre d'éléments à afficher
    @Override
    public int getItemCount() {
        return topStoriesList.size();
    }


    // On référence les éléments de movie card
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fragmentItemTitle;

        public TextView fragmentDate;

        public ImageView fragmentImageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            fragmentItemTitle = itemView.findViewById(R.id.fragment_item_title);
            fragmentDate = itemView.findViewById(R.id.fragment_date);
            fragmentImageView = itemView.findViewById(R.id.fragment_main_item_image);

        }
    }
}
