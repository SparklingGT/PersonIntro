package com.comli.dawnbreaksthrough.personalintro;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import java.util.UUID;

/**
 * Created by SparklingGT on 9/15/2016.
 */
public class DeleteDialog extends DialogFragment
{
    private static final String STRING_PERSON_ID_ARG_KEY = "KeyForPersonID";

    public static DeleteDialog newInstance(UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable(STRING_PERSON_ID_ARG_KEY, uuid);

        DeleteDialog fragment = new DeleteDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        UUID uuid = (UUID) getArguments().getSerializable(STRING_PERSON_ID_ARG_KEY);
        String name = PersonLab.get(getActivity()).getPerson(uuid).getName();
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.dialog_title_delete)
                .titleColor(Color.RED)
                .titleGravity(GravityEnum.CENTER)
                .content(R.string.dialog_content_delete, name)
                .neutralText(android.R.string.yes)
                .onNeutral(new MaterialDialog.SingleButtonCallback()
                {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .positiveText(android.R.string.no)
                .theme(Theme.DARK)
                .show();
    }

    private void sendResult(int resultCode) {
        Intent intent = new Intent();
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
