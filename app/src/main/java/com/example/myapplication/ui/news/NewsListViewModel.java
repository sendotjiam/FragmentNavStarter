package com.example.myapplication.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsListViewModel extends ViewModel {

    private final MutableLiveData<String> _newsFeedback = new MutableLiveData<String>();
    final LiveData<String> newsFeedback = _newsFeedback;

    public NewsListViewModel() {
    }

    public void sendFeedback(String feedback){
        _newsFeedback.setValue(feedback);
    }
}

