package com.comli.dawnbreaksthrough.personalintro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeDialog extends DialogFragment implements DialogInterface.OnClickListener
{
    private static final String STRING_DATE_N_TIME_ARG = "getTimeNDate";

    private TimePicker mTimePicker;
    private int mYear;
    private int mMonth;
    private int mDay;

    public static TimeDialog newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(STRING_DATE_N_TIME_ARG, date);

        TimeDialog timeDialog = new TimeDialog();
        timeDialog.setArguments(args);
        return timeDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_time, null);
        Date date = (Date) getArguments().getSerializable(STRING_DATE_N_TIME_ARG);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int hr = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        mTimePicker = (TimePicker) view.findViewById(R.id.time_picker);
        mTimePicker.setHour(hr);
        mTimePicker.setMinute(min);
        mTimePicker.setIs24HourView(false); //this has to go after the set method!!!!
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, this)
                .setNegativeButton(getString(R.string.time_picker_back), this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        int hr;
        int min;
        if (Build.VERSION.SDK_INT < 23) {
            hr = mTimePicker.getCurrentHour();
            min = mTimePicker.getCurrentMinute();
        } else {
            hr = mTimePicker.getHour();
            min = mTimePicker.getMinute();
        }
        Date dateResult = new GregorianCalendar(mYear, mMonth, mDay, hr, min).getTime();

        int resultCode;
        if (which == DialogInterface.BUTTON_POSITIVE) {
            resultCode = Activity.RESULT_OK;
        } else {
            resultCode = Activity.RESULT_CANCELED;
        }

        sendResult(dateResult, resultCode);
    }

    private void sendResult(Date date,int resultCode) {
        Intent intent = PersonDetailFragment.newIntent(date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
