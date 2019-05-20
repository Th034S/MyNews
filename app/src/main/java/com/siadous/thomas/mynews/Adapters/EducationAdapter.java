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

import com.siadous.thomas.mynews.Model.Education.EducationResponse;
import com.siadous.thomas.mynews.R;
import com.siadous.thomas.mynews.education_list.EducationFragment;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.MyViewHolder>{

    private EducationFragment educationFragment;
    private List<EducationResponse> educationList;
    private EducationResponse education;



    public EducationAdapter(EducationFragment educationFragment, List<EducationResponse> educationList) {
        this.educationFragment = educationFragment;
        this.educationList = educationList;
    }


    @NonNull
    @Override
    public EducationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull final EducationAdapter.MyViewHolder holder, final int position) {
        String category;
        education = educationList.get(position);

        holder.fragmentItemTitle.setText(education.getSnippet());
        if(!education.getSection_name().equals("") && !education.getSubsection_name().equals("")) {
            category = education.getSection_name() + " > " + education.getSubsection_name();
            holder.fragmentCategory.setText(category);
        }
        else {
            category = education.getSection_name();
            holder.fragmentCategory.setText(category);
        }
        holder.fragmentDate.setText(configureFormatDate());
        if (education.getMultimedia().length >= 1) {
            // loading album cover using Glide library
            Glide.with(holder.getContext())
                    .load(education.getMultimedia()[education.getMultimedia().length - 1].getUrl())  // PEUT ETRE UNE ERREUR
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
        Log.e("testsiadous", String.valueOf(educationList.size()));
        return educationList.size();
    }

    public String configureFormatDate() {
        String date = education.getPub_date();
        String[] split = date.split("T");
        date = split[0];
        split = date.split("-");
        String[] year = split[0].split("0");
        date = split[2] + "/" + split[1] + "/" + year[1];
        return date;
    }

    public EducationResponse getArticle(int position){
        return this.educationList.get(position);
    }

    public void updateList( List<EducationResponse> educationList ) {
        this.educationList = educationList;
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
