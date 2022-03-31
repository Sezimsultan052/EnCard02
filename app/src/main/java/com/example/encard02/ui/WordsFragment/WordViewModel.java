package com.example.encard02.ui.WordsFragment;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Hit;
import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.common.Resource;
import com.example.encard02.data.model.WordEntity;
import com.example.encard02.repository.MainRepository;
import com.example.encard02.repository.RoomRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WordViewModel extends ViewModel {

    private MainRepository repository;
    private RoomRepository roomRepository;

    protected LiveData<Resource<PixabayResponse>> liveData;


    public void loadImage(String word, String category) {
        liveData =  repository.getImage(word, category);
    }

    public LiveData<List<WordEntity>> getWords(String category) {
        return roomRepository.getWord(category);
    }

    @Inject
    public WordViewModel(MainRepository repository, RoomRepository roomRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
        liveData = new MutableLiveData<>();
    }

}
