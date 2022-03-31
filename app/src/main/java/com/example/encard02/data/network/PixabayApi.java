package com.example.encard02.data.network;

import com.example.PixabayResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {

    @GET("/api?key=26067581-e80ef8988a7a7d1873f0e3bf0")
    Call<PixabayResponse> getImages(@Query("q") String keyWord , @Query("page") int page,
                                    @Query("per_page") int pageCount);

}
