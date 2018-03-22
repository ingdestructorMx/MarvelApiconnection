package com.example.oscar.marvelheroes.RetrofitService;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Oscar on 21/03/2018.
 */

public interface MarvelService {


//4c2df0c7524e317d173d1a73ca4bcfac
//apikey

    @GET("/v1/public/characters")
    Call<SuperHeroe> getCharacters(@Query("apikey") String apikey , @Query("hash") String hash ,@Query("ts") String timestamp);

    @GET("/v1/public/comics")
    Call<SuperHeroe> getComics(@Query("apikey") String apikey , @Query("hash") String hash ,@Query("ts") String timestamp);



}
