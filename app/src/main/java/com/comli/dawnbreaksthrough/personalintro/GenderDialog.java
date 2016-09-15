package com.comli.dawnbreaksthrough.personalintro;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * Created by SparklingGT on 9/14/2016.
 */
public class GenderDialog extends DialogFragment
{
    public static final int MALE = 2;
    public static final int OTHER = 1;
    public static final int FEMALE = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new MaterialDialog.Builder(getActivity())
                .theme(Theme.DARK)
                .title(R.string.dialog_title_gender)
                .titleColor(Color.RED)
                .titleGravity(GravityEnum.CENTER)
                .itemsGravity(GravityEnum.CENTER)
                .items(R.array.gender_list)
                .itemsCallback(new MaterialDialog.ListCallback()
                {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        switch (position) {
                            case FEMALE:
                                sendResult(FEMALE, Activity.RESULT_OK);
                                break;
                            case OTHER:
                                sendResult(OTHER, Activity.RESULT_OK);
                                break;
                            case MALE:
                                sendResult(MALE, Activity.RESULT_OK);
                                break;
                        }
                    }
                })
                .show();
    }

    private void sendResult(int resultGender, int resultCode) {
        Intent intent = PersonDetailFragment.newIntent(resultGender);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
