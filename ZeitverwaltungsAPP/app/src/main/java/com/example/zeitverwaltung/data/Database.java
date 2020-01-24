package com.example.zeitverwaltung.data;

import com.example.zeitverwaltung.MainActivity;
import com.example.zeitverwaltung.ui.Time_Manager.Time_ManagerFragment;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Database  {
    private static final String URL="127.0.0.1/api/workingHours/";
    private static final String PORT="3000";
    private int UserId=3;


    public Database() {

    }

   public void getWorkinghours(){
       OkHttpClient client = new OkHttpClient();
       Request request=new Request.Builder().url(URL+UserId).build();

       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(@NotNull Call call, @NotNull IOException e) {
               e.printStackTrace();
           }

           @Override
           public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println(response.body().toString());

           }
       });

   }

}
