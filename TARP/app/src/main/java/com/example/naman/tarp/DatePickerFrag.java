package com.example.naman.tarp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.Calendar;


/**
 * Created by naman on 10-Feb-18.
 */

public class DatePickerFrag extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
    }
}
