package com.comli.dawnbreaksthrough.personalintro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateDialog extends DialogFragment
{
    private static final String STRING_ARGS_DATE = "getDateKey";
    private static final String STRING_WHETHER_KEEP_TIME_INFO_ARG = "keepItOrNot";
    public static final int INT_FROM_TIME_PICKER = 234;
    public static final int INT_FROM_LONG_CLICK = 582;

    private DatePicker mDatePicker;

    public static DateDialog newInstance(Date date, int fromWhere) {
        Bundle args = new Bundle();
        boolean keepInfoFromTimePicker = (fromWhere == INT_FROM_TIME_PICKER);
        args.putBoolean(STRING_WHETHER_KEEP_TIME_INFO_ARG, keepInfoFromTimePicker);
        args.putSerializable(STRING_ARGS_DATE, date);


        DateDialog dateDialog = new DateDialog();
        dateDialog.setArguments(args);
        return dateDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        final Date date = (Date) getArguments().getSerializable(STRING_ARGS_DATE);
        final boolean keepInfoFromTimePicker = getArguments().getBoolean(STRING_WHETHER_KEEP_TIME_INFO_ARG);

        final Calendar calendar = Calendar.getInstance();
        mDatePicker = (DatePicker) view.findViewById(R.id.date_picker);

        //// TODO: 9/15/2016  Let user to determine whether they want to able to pick the days in the future.
        mDatePicker.setMaxDate(new Date(System.currentTimeMillis()).getTime());
        mDatePicker.setMinDate(new GregorianCalendar(1900, 0, 1).getTimeInMillis());
        // fixme android:maxDate & minDate does not seem to work ??

        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        if (year < 1900) {
            year = 1900; //corresponds to minDate = 01/01/1900
        }
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);



        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int year = mDatePicker.getYear();
                        int month = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();

                        Date currentExactDate = new Date(System.currentTimeMillis());
                        Calendar rightNowCalender = Calendar.getInstance();
                        rightNowCalender.setTime(currentExactDate);

                        int hr;
                        int min;
                        if (keepInfoFromTimePicker) {
                            hr = calendar.get(Calendar.HOUR_OF_DAY);
                            min = calendar.get(Calendar.MINUTE);
                        } else {
                            hr = rightNowCalender.get(Calendar.HOUR_OF_DAY);
                            min = rightNowCalender.get(Calendar.MINUTE);
                        }

                        Date resultDate = new GregorianCalendar(year, month, day, hr, min).getTime();
                        sendResult(resultDate, Activity.RESULT_OK);
                    }
                })
                .create();
    }

    private void sendResult(Date date, int resultCode) {
        Intent intent = PersonDetailFragment.newIntent(date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

}
