package com.example.encard02.ui.WordsFragment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.common.Resource;

public class WordViewModel extends ViewModel {


    protected LiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();

    //used in 2nd HW
    public void getMutableLiveData(String word) {
        liveData = App.repository.getImage(word);
    }

    public MutableLiveData<Resource<PixabayResponse>> getHitLiveData(String word){
        return App.repository.getImage(word);
    }
}
