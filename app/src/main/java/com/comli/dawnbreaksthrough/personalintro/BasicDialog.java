package com.comli.dawnbreaksthrough.personalintro;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * Created by SparklingGT on 9/18/2016.
 */
public class BasicDialog extends DialogFragment
{
    private static final String IDENTIFIER_SOURCE = "Identifier";

    public static final int ERR_NO_BROWSER = 7;
    public static final int ERR_NO_VIEWER = 34;
    public static final int ERR_PATH_NOT_FOUND = 341;
    public static final int ERR_NO_CAMERA = 1;
    public static final int ABOUT = 32;

    /**
     *
     * @param identifier Identifier constant from BasicDialog
     * @return fragment
     */
    public static BasicDialog newInstance(int identifier) {

        Bundle args = new Bundle();
        args.putInt(IDENTIFIER_SOURCE, identifier);

        BasicDialog fragment = new BasicDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int identifier = getArguments().getInt(IDENTIFIER_SOURCE);

        MaterialDialog dialog;

        switch (identifier) {
            // Use different theme for error.
            case ERR_NO_BROWSER:case ERR_NO_CAMERA:case ERR_NO_VIEWER:case ERR_PATH_NOT_FOUND:
                dialog = new MaterialDialog.Builder(getActivity())
                        .theme(Theme.DARK)
                        .titleColor(Color.RED)
                        .title(getString(R.string.all_error))
                        .build();
                break;
            default:
                dialog = new MaterialDialog.Builder(getActivity())
                        .build();
                break;
        }


        int content = 0;
        switch (identifier) {
            case ERR_NO_BROWSER:
                content = R.string.err_no_browser;
                break;
            case ERR_NO_CAMERA:
                content = R.string.err_no_camera_app;
                break;
            case ERR_NO_VIEWER:
                content = R.string.err_no_image_viewer_app;
                break;
            case ERR_PATH_NOT_FOUND:
                content = R.string.err_no_pic_dir;
                break;
            case ABOUT:

                break;
        }
        dialog.setContent(getString(content));


        return dialog;
    }

}
