package com.puppy.movieshow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.puppy.movieshow.R;
import com.puppy.movieshow.bean.MovieData;
import com.puppy.movieshow.util.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<MovieData> dataSet;
    private OnItemClickListener listener;

    public MovieAdapter(Context context, List<MovieData> dataSet, OnItemClickListener listener){
        this.dataSet = new ArrayList<>();
        this.dataSet.addAll(dataSet);
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(MovieData item);
    }
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_card_view, parent, false);
        MovieViewHolder pvh = new MovieViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(dataSet.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvRated, tvPopularity;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.tvTitle =  (TextView) itemView.findViewById(R.id.title);
            this.ivImage =  (ImageView) itemView.findViewById(R.id.image);
            this.tvRated = (TextView) itemView.findViewById(R.id.isAdult);
            this.tvPopularity = (TextView) itemView.findViewById(R.id.popularity);
        }

        public void bind(final MovieData item, final OnItemClickListener listener) {
            tvTitle.setText(item.getTitle());
            tvRated.setText(item.isAdult()? "Rated: R" : "Rated: PG");
            tvPopularity.setText("Popularity: " + item.getPopularity());
            GlideApp.with(itemView.getContext())
                    .load(item.getPosterPath())
                    .placeholder(R.mipmap.placeholder)
                    .error(R.mipmap.placeholder)
                    .into(ivImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public void addToList(List<MovieData> newData){
        dataSet.addAll(newData);
        notifyDataSetChanged();
    }

}
