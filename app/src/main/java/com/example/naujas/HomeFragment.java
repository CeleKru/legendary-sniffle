package com.example.naujas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    String api ="74daee441d8d43aaa1f77bada423ce02";
    ArrayList<ModelClass> modelClassesList;
    Adapter adapter;
    String country="us";
    private RecyclerView recyclerViewHome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.homefragment,null);
        recyclerViewHome=v.findViewById(R.id.homeRecycler);
        modelClassesList=new ArrayList<>();
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelClassesList);
        recyclerViewHome.setAdapter(adapter);
        findNews();

        return v;
    }

    private void findNews() {
        Objects.requireNonNull(ApiUtilities.getApiInterface()).getNews(country,100,api).enqueue(new Callback<mainNews>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<mainNews> call, @NonNull Response<mainNews> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    modelClassesList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}