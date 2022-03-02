package com.example.qunatuminternshala;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {

    String api = "5fa9914cd3054cda8f0f7ea98d3f3c8a ";
    ArrayList<NewsDetails> newsDetailsArrayList;
    Adapter adapter;
    ApiInterface apiInterface;
    String country = "in";
    RecyclerView recyclerviewhome;

    public NewsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerviewhome = v.findViewById(R.id.recyclerviewhome);
          newsDetailsArrayList= new ArrayList<>();
        recyclerviewhome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(), newsDetailsArrayList);
        recyclerviewhome.setAdapter(adapter);

        findNews();
        return v;

    }

    private void findNews() {

        AppUtilities.getApiInterface().getNews(country , 100 , api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful())
                {
                    newsDetailsArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}