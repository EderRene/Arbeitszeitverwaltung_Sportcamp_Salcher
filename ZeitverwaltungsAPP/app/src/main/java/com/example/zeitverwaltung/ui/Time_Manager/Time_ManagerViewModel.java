package com.example.zeitverwaltung.ui.Time_Manager;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Time_ManagerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Time_ManagerViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}