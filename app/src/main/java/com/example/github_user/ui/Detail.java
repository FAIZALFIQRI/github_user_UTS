package com.example.github_user.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.github_user.R;
import com.example.github_user.data.Response.GithubSearchResponse;
import com.example.github_user.data.retrofit.ApiConfig;
import com.example.github_user.data.retrofit.ApiService;
import com.example.github_user.databinding.ActivityDetailBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String username = getIntent().getStringExtra(EXTRA_USERNAME);
        System.out.println("ini" + username);

        if (username != null){
            ApiService apiService = ApiConfig.getApiService();
            Call<GithubSearchResponse> userCall = apiService.getUser(username);


            userCall.enqueue(new Callback<GithubSearchResponse>() {
                @Override
                public void onResponse(@NonNull Call<GithubSearchResponse> call, @NonNull Response<GithubSearchResponse> response) {
                    if (response.isSuccessful()){

                        GithubSearchResponse user = response.body();
                        if (user != null){
                            binding.name.setText(user.getLogin());
                            binding.Bio.setText(user.getBio()); // Set the bio text
                            Glide.with(Detail.this)
                                    .load(user.getAvatarUrl())
                                    .into(binding.GambarItem);
                        }else {
                            Toast.makeText(Detail.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<GithubSearchResponse> call, @NonNull Throwable t) {
                    Toast.makeText(Detail.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}