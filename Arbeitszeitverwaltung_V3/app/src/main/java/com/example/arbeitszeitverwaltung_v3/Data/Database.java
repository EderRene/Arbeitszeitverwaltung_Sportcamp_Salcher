package com.example.arbeitszeitverwaltung_v3.Data;

import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

import com.example.arbeitszeitverwaltung_v3.GUI.Time_ManagerFragment;
import com.example.arbeitszeitverwaltung_v3.Misc.JsonToWorkingHour;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Database{
    private static final String URL = "http://salcher.synology.me:8888/api/workingHours/";
    private static final String PORT = "8888";
    private int UserId = 3;
    private static Database db;

    private Database() {
    }

    public static Database getInstance() {
        if(db==null){
            db=new Database();
        }
        return db;
    }

    public ArrayList<WorkingHour> getWorkinghours(final Date fromDate, final Date toDate){
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().url(URL+UserId).build();
        final ArrayList<WorkingHour> workingHours= new ArrayList<>();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                try {
                    String s= "{\"WorkingHours\":" +response.body().string()+"}";

                    JSONObject obj=new JSONObject(s);

                    JSONArray Jarray = obj.getJSONArray("WorkingHours");
                    JsonToWorkingHour parser=new JsonToWorkingHour();

                    for(int i=0;i<Jarray.length();i++){
                        workingHours.add(parser.toWorkingHour(Jarray.getJSONObject(i)));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (toDate != null) {

            workingHours.removeIf(new Predicate<WorkingHour>() {
                @Override
                public boolean test(WorkingHour w) {
                    if(w.getWorkingDate().compareTo(toDate)>0){
                        return true;
                    }else {
                        return false;
                    }
                }
            });
        }
        if (fromDate != null) {
            workingHours.removeIf(new Predicate<WorkingHour>() {
                @Override
                public boolean test(WorkingHour w) {
                    if(w.getWorkingDate().compareTo(fromDate)<0){
                        return true;
                    }else {
                        return false;
                    }
                }
            });
        }
        return workingHours;
    }


}
