package com.example.encard02.ui.WordsFragment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.common.Resource;
import com.example.encard02.repository.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordViewModel extends ViewModel {

    public MainRepository repository;

    protected LiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();

    //used in 2nd HW and used in di
    public void getMutableLiveData(String word, int page) {
        liveData = repository.getImage(word, page);
    }

    public MutableLiveData<Resource<PixabayResponse>> getHitLiveData(String word, int page){
        return repository.getImage(word, page);
    }

    @Inject
    public WordViewModel(MainRepository repository) {
        this.repository = repository;
        liveData = new MutableLiveData<>();
    }

}
