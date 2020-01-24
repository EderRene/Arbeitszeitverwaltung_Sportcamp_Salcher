package com.example.zeitverwaltung.ui.Time_Manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.zeitverwaltung.data.WorkingHour;

public class WorkingHourDialog extends AppCompatDialogFragment {

    private WorkingHour wh=null;
    public WorkingHourDialog(WorkingHour selectedItem) {
        wh=selectedItem;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information")
                .setMessage(wh.toString())
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        return builder.create();
    }
}
