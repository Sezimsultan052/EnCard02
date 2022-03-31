package com.example.encard02.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.encard02.data.network.PixabayApi;
import com.example.encard02.db.CategoryDao;
import com.example.encard02.db.CategoryDatabase;
import com.example.encard02.db.WordDao;
import com.example.encard02.repository.MainRepository;
import com.example.encard02.repository.RoomRepository;
import com.example.encard02.ui.Prefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static PixabayApi provideRetrofit(OkHttpClient client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(PixabayApi.class);
    }

    @Provides
    @Singleton
    public static OkHttpClient provideClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    public static MainRepository provideMainRepository(PixabayApi api, WordDao dao) {
        return new MainRepository(api, dao);
    }

    @Provides
    public SharedPreferences provideSharedPrefs(@ApplicationContext Context context) {
        return context.getApplicationContext()
                .getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    @Provides
    public Prefs providePref(SharedPreferences preferences) {
        return new Prefs(preferences);
    }

    @Provides
    @Singleton
    public static CategoryDatabase providerCategoryDb(@ApplicationContext Context context) {
        return Room.databaseBuilder(context,
                CategoryDatabase.class,
                "DB_NAME")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static CategoryDao providerDao(CategoryDatabase database) {
        return database.getDao();
    }

    @Provides
    @Singleton
    public static WordDao providerWordDao(CategoryDatabase databasea) {
        return databasea.getWordDao();
    }

    @Provides
    @Singleton
    public static RoomRepository providerRoomRep(CategoryDao dao, WordDao wordDao) {
        return new RoomRepository(dao, wordDao);
    }

}
