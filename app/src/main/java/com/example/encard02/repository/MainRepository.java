package com.example.encard02.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.PixabayResponse;
import com.example.encard02.App;
import com.example.encard02.common.Resource;
import com.example.encard02.data.network.PixabayApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private PixabayApi api;
    private final int pageCount = 10;

    @Inject
    public MainRepository(PixabayApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<PixabayResponse>> getImage(String word, int page) {
        MutableLiveData<Resource<PixabayResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getImages(word, page, pageCount ).enqueue(new Callback<PixabayResponse>() {
            @Override
            public void onResponse(Call<PixabayResponse> call, Response<PixabayResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));

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
