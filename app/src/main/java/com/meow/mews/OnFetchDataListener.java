package com.meow.mews;

import com.meow.mews.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsAPIResponse> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
