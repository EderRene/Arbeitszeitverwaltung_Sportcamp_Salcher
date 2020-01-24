package com.example.zeitverwaltung.ui.work_Overview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.zeitverwaltung.R;

public class Work_OverviewFragment extends Fragment {

    private Work_OverviewViewModel workOverviewViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        workOverviewViewModel =
                ViewModelProviders.of(this).get(Work_OverviewViewModel.class);
        View root = inflater.inflate(R.layout.fragment_work_overview, container, false);
        final TextView textView = root.findViewById(R.id.text_work_overview);
        workOverviewViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}