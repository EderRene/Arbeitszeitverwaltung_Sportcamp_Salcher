package com.example.zeitverwaltung.ui.work_Overview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Work_OverviewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Work_OverviewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is work_Overview fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}