package com.example.zeitverwaltung.ui.Time_Manager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.zeitverwaltung.R;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Time_ManagerFragment extends Fragment implements View.OnClickListener {

    private Time_ManagerViewModel homeViewModel;
    private TextView date1,date2 ;
    private Calendar calendar=null;
    private DatePickerDialog pickerDialog=null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(Time_ManagerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_time_manager, container, false);
         date1 = root.findViewById(R.id.date1_time_manager);
         date2 = root.findViewById(R.id.date2_time_manager);
         calendar=Calendar.getInstance();
         date1.setText(calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR));
         date2.setText(calendar.get(Calendar.DAY_OF_MONTH)+"."+(calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.YEAR));
         date1.setOnClickListener(this);
         date2.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==date1.getId()){
               getDateFromPicker(3,date1);
        }
        if (v.getId()==date2.getId()){
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


}