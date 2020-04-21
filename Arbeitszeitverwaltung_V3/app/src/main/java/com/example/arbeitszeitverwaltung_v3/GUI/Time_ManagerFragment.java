package com.example.zeitverwaltung.ui.Time_Manager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.zeitverwaltung.R;
import com.example.zeitverwaltung.data.WorkingHour;
import com.example.zeitverwaltung.ui.misc.JsonToWorkingHour;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Time_ManagerFragment extends Fragment implements View.OnClickListener {

    private Time_ManagerViewModel homeViewModel;
    private TextView date1,date2 ;
    private Calendar calendar=null;
    private DatePickerDialog pickerDialog=null;
    private ListView listViewWorkingHours=null;

    private ArrayAdapter adapterListView=null;


    private static final String URL="http://127.0.0.1:3000/api/workingHours/";
    private static final String PORT="3000";
    private int UserId=3;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(Time_ManagerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_time_manager, container, false);
         date1 = root.findViewById(R.id.date1_time_manager);
         date2 = root.findViewById(R.id.date2_time_manager);
         listViewWorkingHours=root.findViewById(R.id.listviewworkinghours);
         calendar=Calendar.getInstance();
         date1.setText(calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR));
         date2.setText(calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR));

         date1.setOnClickListener(this);
         date2.setOnClickListener(this);
        getWorkinghours();
        listViewWorkingHours.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                displayWorkingHourDialog((WorkingHour) listViewWorkingHours.getItemAtPosition(position));
            }
        });
        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

    }

    private void displayWorkingHourDialog(WorkingHour selectedItem) {

        WorkingHourDialog whd=new WorkingHourDialog(selectedItem);
        whd.show(getActivity().getSupportFragmentManager(),"null");
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==date1.getId()){
            getDateFromPicker(3,date1);
        }
        if (v.getId()==date2.getId()){
            getWorkinghours();
            getDateFromPicker(0,date2);
        }
    }

    private void getDateFromPicker(int minusDays, final TextView tv) {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, (-minusDays));

        int month = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.DAY_OF_MONTH);

        pickerDialog = new DatePickerDialog(getView().getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Date d = new Date(year, month, dayOfMonth);
                tv.setText(dayOfMonth + "." + (month + 1) + "." + year);

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        pickerDialog.show();
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
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {

                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                            String s= "{\"WorkingHours\":" +response.body().string()+"}";

                            JSONObject obj=new JSONObject(s);

                            JSONArray Jarray = obj.getJSONArray("WorkingHours");
                            JsonToWorkingHour parser=new JsonToWorkingHour();
                            final ArrayList<WorkingHour> workingHours= new ArrayList<>();
                            for(int i=0;i<Jarray.length();i++){
                                workingHours.add(parser.toWorkingHour(Jarray.getJSONObject(i)));
                            }
                            ArrayAdapter<WorkingHour> arrayAdapter
                                    = new ArrayAdapter<WorkingHour>(getContext(), android.R.layout.simple_list_item_1, workingHours);
                            listViewWorkingHours.setAdapter(arrayAdapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            }
        });

    }


}