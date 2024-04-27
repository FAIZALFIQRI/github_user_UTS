package com.example.github_user.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.github_user.R;
import com.example.github_user.data.Response.GithubUser;
import com.example.github_user.data.Response.ItemsItem;
import com.example.github_user.data.retrofit.ApiConfig;
import com.example.github_user.data.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rview;
    private GithubUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rview = findViewById(R.id.Rview);
        rview.setLayoutManager(new LinearLayoutManager(this));

        ApiService apiService = ApiConfig.getApiService();
        Call<GithubUser> call = apiService.searchUsers("Faiz");

        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ItemsItem> users = response.body().getItems();
                    adapter = new GithubUserAdapter(new ArrayList<ItemsItem>());
                    adapter.setUsers(users);
                    rview.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to get users", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}