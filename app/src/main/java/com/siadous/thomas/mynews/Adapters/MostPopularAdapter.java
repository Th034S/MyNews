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
import com.siadous.thomas.mynews.Model.MostPopular.MostPopular;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.Utils.DateUtils;
import com.siadous.thomas.mynews.most_popular_list.MostPopularFragment;

import java.util.List;

public class MostPopularAdapter extends RecyclerView.Adapter<MostPopularAdapter.MyViewHolder> {

    private MostPopularFragment mostPopularFragment;
    private List<MostPopular> mostPopularList;
    private MostPopular mostPopular;



    public MostPopularAdapter(MostPopularFragment mostPopularFragment, List<MostPopular> mostPopularList) {
        this.mostPopularFragment = mostPopularFragment;
        this.mostPopularList = mostPopularList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull final MostPopularAdapter.MyViewHolder holder, final int position) {
        String category;
        mostPopular = mostPopularList.get(position);

        holder.fragmentItemTitle.setText(mostPopular.getTitle());
        if(!mostPopular.getSection().equals("") ) {
            category = mostPopular.getSection();
            holder.fragmentCategory.setText(category);
        }

        holder.fragmentDate.setText(DateUtils.configureFormatDate(mostPopular.getPublished_date()));
        if (mostPopular.getMedia().length >= 1) {
            // loading album cover using Glide library
            Glide.with(holder.getContext())
                    .load(mostPopular.getMedia()[mostPopular.getMedia().length - 1].getMediaMetadata()[0].getUrl())
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
        Log.e("testsiadous", String.valueOf(mostPopularList.size()));
        return mostPopularList.size();
    }

    public MostPopular getArticle(int position){
        return this.mostPopularList.get(position);
    }

    public void updateList( List<MostPopular> mostPopularList ) {
        this.mostPopularList = mostPopularList;
    }

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

}
