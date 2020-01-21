package com.example.zeitverwaltung.ui.Time_Manager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Time_ManagerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Time_ManagerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Time_Manager fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}