package com.comli.dawnbreaksthrough.personalintro;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.comli.dawnbreaksthrough.personalintro.Elements.Thumbnail;

import java.io.File;
import java.util.UUID;

/**
 * Created by SparklingGT on 9/15/2016.
 */
public class PhotoDialog extends DialogFragment
{
    private static final String STRING_PERSON_ID_ARG_KEY = "personIdentifier";

    public static PhotoDialog newInstance(UUID uuid) {

        Bundle args = new Bundle();
        args.putSerializable(STRING_PERSON_ID_ARG_KEY, uuid);

        PhotoDialog fragment = new PhotoDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        UUID uuid = (UUID) getArguments().getSerializable(STRING_PERSON_ID_ARG_KEY);
        Person person = PersonLab.get(getActivity()).getPerson(uuid);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_photo, null);
        ImageView photo = (ImageView) view.findViewById(R.id.image_view_photo_dialog);

        RoundedBitmapDrawable drawable;
        int thumbnailLargeSizeInDp = 350;
        //// TODO: 9/15/2016 350dp might not work on all devices, let user pick!

        File thumbnailCache = PersonLab.get(getActivity()).getThumbnailFile(person, Thumbnail.Size.LARGE);
        if (thumbnailCache.exists()) {
            drawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), thumbnailCache.getPath());
        } else {
            Bitmap bitmap = PictureUtils
                    .getScaleBitmap(person, thumbnailLargeSizeInDp, Thumbnail.Size.LARGE, getActivity());
            drawable = RoundedBitmapDrawableFactory.create(getActivity().getResources(), bitmap);
        }

        int photoSideLenInPixel = PictureUtils.dipToPixel(thumbnailLargeSizeInDp, getActivity());
        photo.getLayoutParams().height = photoSideLenInPixel;
        photo.getLayoutParams().width = photoSideLenInPixel;
        photo.setBackground(new ColorDrawable(Color.TRANSPARENT));

        //// TODO: 9/17/2016  Maybe let user pick the radius here
        drawable.setCornerRadius(PictureUtils.dipToPixel(40, getActivity()));

        photo.setImageDrawable(drawable);
        AlertDialog photoDialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        photoDialog.show();
        photoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return photoDialog;
    }

}
