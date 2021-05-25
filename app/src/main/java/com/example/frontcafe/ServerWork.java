package com.example.frontcafe;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ServerWork {
    private static String defaultHost = "https://00630754df0a.ngrok.io/";
    private static SharedPreferences mSittings;
    private static final String AUTHTOHOST = "AuthToHost";
    private static String root = "";
    private static Context context;
    private final static String APP_PREFERENCES = "mysettings";

    public ServerWork(Context context){
        this.context = context;
        root = String.valueOf(context.getFilesDir());

    }

    public int signUpServer(String login, String password)
    {
        //Готовим запрос
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(defaultHost)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ServerApi signUpApi = retrofit.create(ServerApi.class);
        Call<String> request;
        request = signUpApi.signUpPerson(login,password);


        String req;

        //Делаем запрос
        try {
            req = request.execute().body();
            if(req == null) return 0;
            String[] requests = req.split(":");
            //Проверяем код ответа сервера
            if(!requests[0].equals("1"))
                return Integer.parseInt(requests[0]);

            //Шифруем файл для автоавторизации
            //req = LocalBase.encode(requests[1]);
            //Cохраняем файл для автоавторизации
            mSittings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSittings.edit();
            editor.putString(AUTHTOHOST,requests[1]);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}