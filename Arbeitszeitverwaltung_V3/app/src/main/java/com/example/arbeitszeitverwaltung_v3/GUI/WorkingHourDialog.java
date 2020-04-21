package com.example.arbeitszeitverwaltung_v3.GUI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.arbeitszeitverwaltung_v3.Data.WorkingHour;

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
                .setMessage(wh.allInfos())
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });


        return builder.create();
    }
}
