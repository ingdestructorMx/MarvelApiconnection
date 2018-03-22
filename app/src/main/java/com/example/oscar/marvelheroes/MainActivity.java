package com.example.oscar.marvelheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.oscar.marvelheroes.RetrofitService.Data;
import com.example.oscar.marvelheroes.RetrofitService.MarvelService;
import com.example.oscar.marvelheroes.RetrofitService.SuperHeroe;


import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    private String BASE_URL = "https://gateway.marvel.com/";
    private String API_KEY = "4c2df0c7524e317d173d1a73ca4bcfac";
    private String API_KEY_PRIVATE = "9adf5f6d832a4f9820285ca544387f29327b9a00";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MarvelService service = retrofit.create(MarvelService.class);



        String hash = MD5(timestamp+API_KEY_PRIVATE+API_KEY);

       Call<SuperHeroe> superHeroeCall = service.getCharacters(API_KEY,hash,timestamp.toString());


       superHeroeCall.enqueue(new Callback<SuperHeroe>() {
           @Override
           public void onResponse(Call<SuperHeroe> call, Response<SuperHeroe> response) {

               Toast.makeText(getApplicationContext(),"Con exito",Toast.LENGTH_SHORT).show();

               SuperHeroe superHeroe = response.body();

               try {
                   JSONObject json = new JSONObject(String.valueOf(superHeroe));
                   Log.e("Response",json.toString());
               } catch (JSONException e) {
                   e.printStackTrace();
               }



           }

           @Override
           public void onFailure(Call<SuperHeroe> call, Throwable t) {
               Toast.makeText(getApplicationContext(),"Con error",Toast.LENGTH_SHORT).show();
               Log.e("Response",call.toString());
           }
       });



    }


    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }


}
