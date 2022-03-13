package com.example.encard02.ui.fragment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.common.Resource;

public class WordViewModel extends ViewModel {
    protected LiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();

    public void getMutableLiveData(String word) {
        liveData = App.repository.getImage(word);
    }
}
