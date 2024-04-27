package com.example.github_user.data.retrofit;

import com.example.github_user.data.Response.GithubSearchResponse;
import com.example.github_user.data.Response.GithubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search/users")
    @Headers({"Authorization: token" + TOKEN})
    Call<GithubUser> searchUsers(@Query("q") String username);

    @GET("users/{username}")
    @Headers({"Authorization: token" + TOKEN})
    Call<GithubSearchResponse> getUser(@Path("username") String username);

    String TOKEN = "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";


}
