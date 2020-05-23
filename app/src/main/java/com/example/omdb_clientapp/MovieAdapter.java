package com.example.omdb_clientapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omdb_clientapp.model.SearchResult;
import com.example.omdb_clientapp.ui.MovieDataFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<SearchResult> results;

    public MovieAdapter(List<SearchResult> results) {
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_results, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SearchResult result = results.get(position);
        holder.titleview.setText(result.getTitle());
        holder.typeview.setText(result.getType());
        holder.yearview.setText(result.getYear());
        Picasso.get().load(result.getPoster()).into(holder.imageview);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemView.getContext();
                if(context instanceof MainActivity){
                    MainActivity activity = (MainActivity) context;
                    activity.navigateTo(new MovieDataFragment(result.getImdbID()),true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleview;
        TextView yearview;
        TextView typeview;
        ImageView imageview;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleview = itemView.findViewById(R.id.item_search_results_title_view);
            yearview = itemView.findViewById(R.id.item_search_results_year_view);
            typeview = itemView.findViewById(R.id.item_search_results_type_view);
            imageview = itemView.findViewById(R.id.item_search_results_image_view);
        }
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}
