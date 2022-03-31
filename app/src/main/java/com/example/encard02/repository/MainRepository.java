package com.example.encard02.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.common.Resource;
import com.example.encard02.data.model.WordEntity;
import com.example.encard02.data.network.PixabayApi;
import com.example.encard02.db.WordDao;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private PixabayApi api;
    private WordDao dao;

    @Inject
    public MainRepository(PixabayApi api, WordDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resource<PixabayResponse>> getImage(String word, String category) {
        MutableLiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getImages(word).enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                Log.e("word", response.body() +" ");
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                    String imgUrl = response.body().getHits().get(0).getLargeImageURL();
                    dao.addWord(new WordEntity(category, imgUrl, word));
                } else {
                    liveData.setValue(Resource.error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<PixabayResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage()));
            }
        });
        return liveData;
    }
}
