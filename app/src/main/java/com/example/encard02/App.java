package com.example.encard02;

import android.app.Application;

import com.example.encard02.data.network.PixabayApi;
import com.example.encard02.data.network.RetrofitClient;
import com.example.encard02.repository.MainRepository;

public class App extends Application {

    RetrofitClient retrofitClient;
    public static PixabayApi pixabayApi;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        pixabayApi = retrofitClient.getApi();

        repository = new MainRepository(pixabayApi);



    }
}
