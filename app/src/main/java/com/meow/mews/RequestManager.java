package com.meow.mews;

import android.content.Context;
import android.widget.Toast;

import com.meow.mews.Models.NewsAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getNewsHeadlines(OnFetchDataListener listener, String category, String query) {
        CallNewsAPI callNewsAPI = retrofit.create(CallNewsAPI.class);
        Call<NewsAPIResponse> call = callNewsAPI.callHeadlines("in", category, query, context.getString(R.string.api_key));

        try {
            call.enqueue(new Callback<NewsAPIResponse>() {
                @Override
                public void onResponse(Call<NewsAPIResponse> call, Response<NewsAPIResponse> response) {
                    if (!response.isSuccessful()) Toast.makeText(context, "Meowrror!!", Toast.LENGTH_SHORT).show();

                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsAPIResponse> call, Throwable t) {
                    listener.onError("Request Failed!");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface CallNewsAPI {
        @GET("top-headlines")
        Call<NewsAPIResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apikey
        );
    }
}
