package com.example.brewery.Service;


import static com.example.brewery.MainActivity.password;
import static com.example.brewery.MainActivity.phone;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AuthAsyncTask extends AsyncTask<Void, Void, String> {

    APIInterface api;
    public static String gotToken;
    public static boolean Connection = false;
    @Override
    protected String doInBackground(Void... voids) {
        // Здесь выполняется сетевая операция
        // и возвращается результат в виде строки
        String token = "";
        api = RequestBuilder.buildRequest().create(APIInterface.class);
        Call<ResponseBody> call = api.authUser(phone, password);
        try {
            Response<ResponseBody> response = call.execute();
            gotToken = response.body().string();
            token = gotToken;
            Connection = true;
        } catch (IOException e) {
            Log.e("ашыпка: ", e.toString());
        }
        return token;
    }

    @Override
    protected void onPostExecute(String token) {
        // Здесь вы можете обработать результат сетевой операции
        // например, записать токен в строку или обновить пользовательский интерфейс
        Log.d("token", token);
    }
}
