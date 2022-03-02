package com.example.qunatuminternshala;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{

    Context context;


    public Adapter(Context context, ArrayList<NewsDetails> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }



    ArrayList<NewsDetails>modelClassArrayList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item , null , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context , WebView.class);
                intent.putExtra("url",modelClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.date.setText("Published At:- " +modelClassArrayList.get(position).getPublishedAt());
        holder.by.setText(modelClassArrayList.get(position).getAuthor());
        holder.content.setText(modelClassArrayList.get(position).getDescription());
        holder.mainheading.setText(modelClassArrayList.get(position).getTitle());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.imageview);


    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView mainheading , by , date ,content;
        CardView cardView;
        ImageView imageview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            mainheading = itemView.findViewById(R.id.mainheading);
            cardView = itemView.findViewById(R.id.cardview);
            by = itemView.findViewById(R.id.by);
            content = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
            imageview = itemView.findViewById(R.id.imageview);

        }
    }
}
