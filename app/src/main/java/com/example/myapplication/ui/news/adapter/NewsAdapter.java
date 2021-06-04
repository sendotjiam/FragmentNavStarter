package com.example.myapplication.ui.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    ArrayList<String> arrayList;
    OnNewsItemClickListener onNewsItemClickListener;

    public NewsAdapter(ArrayList<String> arrayList, OnNewsItemClickListener onNewsItemClickListener) {
        this.arrayList = arrayList;
        this.onNewsItemClickListener = onNewsItemClickListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsAdapter.NewsViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        holder.newsTitleTxt.setText(arrayList.get(position));
        holder.itemView.setOnClickListener(v -> {
            onNewsItemClickListener.onNewsItemClicked(v, arrayList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView newsTitleTxt;
        public NewsViewHolder(View view) {
            super(view);
            newsTitleTxt = view.findViewById(R.id.newsTitleTxt);
        }
    }
}
