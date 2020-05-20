package com.example.omdb_clientapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omdb_clientapp.model.SearchResult;

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
        holder.imdbview.setText(result.getImdbID());
        holder.typeview.setText(result.getType());
        holder.yearview.setText(result.getYear());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleview;
        TextView yearview;
        TextView imdbview;
        TextView typeview;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleview = itemView.findViewById(R.id.item_search_results_title_view);
            yearview = itemView.findViewById(R.id.item_search_results_year_view);
            typeview = itemView.findViewById(R.id.item_search_results_type_view);
            imdbview = itemView.findViewById(R.id.item_search_results_rating_view);
        }
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}
