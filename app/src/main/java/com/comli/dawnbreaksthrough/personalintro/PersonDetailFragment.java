package com.comli.dawnbreaksthrough.personalintro;


import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.comli.dawnbreaksthrough.personalintro.Elements.Age;
import com.comli.dawnbreaksthrough.personalintro.Elements.Gender;
import com.comli.dawnbreaksthrough.personalintro.Elements.Thumbnail;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonDetailFragment extends Fragment implements
        View.OnClickListener, View.OnLongClickListener,
        View.OnFocusChangeListener
{
    private static final String TAG = "sparkling";
    private static final String STRING_ARGS_PERSON_ID = "PersonID";
    private static final String STRING_CLIPBOARD_NAME = "CopyName";
    private static final String STRING_CLIPBOARD_GENDER = "CopyGender";
    private static final String STRING_CLIPBOARD_BIRTH = "CopyBirthday";
    private static final String STRING_CLIPBOARD_INTRO = "CopyIntro";
    private static final String STRING_CLIPBOARD_ASSOCIATION = "CopyAssociation";
    private static final String STRING_CLIPBOARD_NOTE = "CopyNote";
    private static final String STRING_DATE_DIALOG_TAG = "DateDialog";
    private static final String STRING_TIME_DIALOG_TAG = "TimeDialog";
    private static final String STRING_GENDER_DIALOG_TAG = "GenderDialog";
    private static final String STRING_DELETE_DIALOG_TAG = "DeleteDialog";
    private static final String STRING_PHOTO_DIALOG_TAG = "PhotoDialog";
    private static final String ERR_PATH_NOT_FOUND_TAG = "Err_pathNotFound";
    private static final String ERR_NO_CAMERA_TAG = "Err_noCamera";
    private static final String ERR_NO_IMAGE_VIEWER_TAG = "Err_noCamera";
    private static final String STRING_EXTRA_DATE_TIME_INTENT = "com.comli.dawnbreaksthrough.personalintro.DateTimeResult";
    private static final String STRING_EXTRA_INT_INTENT = "com.comli.dawnbreaksthrough.personalintro.Num";
    private static final int INT_DATE_REQUEST = 38;
    private static final int INT_TIME_REQUEST = 25;
    private static final int INT_GENDER_REQUEST = 2985;
    private static final int INT_DELETE_REQUEST = 752;
    private static final int INT_CAMERA_ACTION_REQUEST = 4572;
    private static final int INT_PLACE_HOLDER = 2475293;

    private TextView mIntroExpand;
    private TextView mAssociationExpand;
    private boolean mIntroVisible;
    private boolean mAssociationVisible;
    private EditText mName;
    private EditText mGender;
    private EditText mBirth;
    private EditText mIntro;
    private ImageView mPhoto;
    private TextView mCamera;
//    private ImageButton mCamera;
    private Person mPerson;
    private KeyListener mKeyListener;
    private PackageManager mPackageManager;
    private File mPhotoFile;
    private boolean longClick;

    public static PersonDetailFragment newInstance(UUID personId) {
        Bundle args = new Bundle();
        args.putSerializable(STRING_ARGS_PERSON_ID, personId);

        PersonDetailFragment personDetailFragment = new PersonDetailFragment();
        personDetailFragment.setArguments(args);
        return personDetailFragment;
    }

    public static Intent newIntent(Date date) {
        Intent intent = new Intent();
        intent.putExtra(STRING_EXTRA_DATE_TIME_INTENT, date);
        return intent;
    }

    public static Intent newIntent(int num) {
        Intent intent = new Intent();
        intent.putExtra(STRING_EXTRA_INT_INTENT, num);
        return intent;
    }

    public PersonDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person_detail, container, false);
        UUID personId = (UUID) getArguments().getSerializable(STRING_ARGS_PERSON_ID);
        mPerson = PersonLab.get(getActivity()).getPerson(personId);

        mIntroExpand = (TextView) view.findViewById(R.id.person_intro_expand);
        mAssociationExpand = (TextView) view.findViewById(R.id.person_relationship_expand);
        mName = (EditText) view.findViewById(R.id.edit_view_person_name);
        mGender = (EditText) view.findViewById(R.id.edit_view_person_gender);
        mBirth = (EditText) view.findViewById(R.id.edit_view_person_birth);
        mIntro = (EditText) view.findViewById(R.id.edit_view_person_intro);
        mPhoto = (ImageView) view.findViewById(R.id.image_view_person_photo);
//        mCamera = (ImageButton) view.findViewById(R.id.button_camera_action);
        mCamera = (TextView) view.findViewById(R.id.button_camera_action);


        mIntroVisible = false;
        mIntro.setVisibility(View.INVISIBLE);
        mAssociationVisible = false;
        //// TODO: 9/13/2016  make association's content invisible

        longClick = false;
        mPackageManager = getActivity().getPackageManager();
        mPhotoFile = PersonLab.get(getActivity()).getPhotoFile(mPerson);


        mIntroExpand.setOnClickListener(this);
        mAssociationExpand.setOnClickListener(this);
        mName.setOnClickListener(this);
        mGender.setOnClickListener(this);
        mBirth.setOnClickListener(this);
        mIntro.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
        mCamera.setOnClickListener(this);

        mName.setOnLongClickListener(this);
        mGender.setOnLongClickListener(this);
        mBirth.setOnLongClickListener(this);
        mIntro.setOnLongClickListener(this);
        mPhoto.setOnLongClickListener(this);
        mCamera.setOnLongClickListener(this);

        mName.setOnFocusChangeListener(this);
        mGender.setOnFocusChangeListener(this);
        mBirth.setOnFocusChangeListener(this);
        mIntro.setOnFocusChangeListener(this);


        mKeyListener = mName.getKeyListener();
        mName.setKeyListener(null);
        mGender.setKeyListener(null);
        mBirth.setKeyListener(null);
        mIntro.setKeyListener(null);

        mName.setText(mPerson.getName());
        mName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String name = charSequence.toString();
                if (charSequence.toString().equals("")) {
                    name = null;
                }
                // the default name is null
                mPerson.setName(name);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        switch (mPerson.getGender()) {
            case Gender.MALE:
                mGender.setText(R.string.gender_male);
                break;
            case Gender.OTHER:
                mGender.setText(R.string.gender_other);
                break;
            case Gender.FEMALE:
                mGender.setText(R.string.gender_female);
                break;
        }


        if (mPerson.getDate().getTime() > System.currentTimeMillis() - Age.ONE_HUNDRED_N_FIFTY_YEARS) {
            // Here we assume no one is older than 150 years old
            mBirth.setText(getFormattedDate(mPerson.getDate()));
        }

        mIntro.setText(mPerson.getIntro());
        mIntro.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mPerson.setIntro(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        final View activityRootView = view.findViewById(R.id.person_detail_root);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(rect);

                int totalHeight = activityRootView.getRootView().getHeight();
                int keyboardHeight = totalHeight - rect.bottom;
//                Log.i(TAG, "totalHeight = " + totalHeight);
//                Log.i(TAG, "keyboardHeight = " + keyboardHeight);
                if (totalHeight < keyboardHeight / 0.18f) {
                    //// TODO: 9/13/2016 let user change the ratio (default is 0.18
                } else {
                    clearFocus();
                    PersonLab.get(getActivity()).updateDatabases(mPerson);
                }
            }
        });

        setHasOptionsMenu(true);
        updatePhoto();

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        clearFocus();
        PersonLab.get(getActivity()).updateDatabases(mPerson);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_person_detail_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete_person:
                DeleteDialog deleteDialog = DeleteDialog.newInstance(mPerson.getUUID());
                deleteDialog.setTargetFragment(this, INT_DELETE_REQUEST);
                deleteDialog.show(getFragmentManager(), STRING_DELETE_DIALOG_TAG);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_view_person_name:
                break;
            case R.id.edit_view_person_gender:

                break;
            case R.id.edit_view_person_birth:

                break;

            case R.id.edit_view_person_intro:

                break;
            case R.id.person_intro_expand:
                mIntroVisible = !mIntroVisible;
                if (mIntroVisible) {
                    mIntro.setVisibility(View.VISIBLE);
                } else {
                    mIntro.setVisibility(View.GONE);
                }
                break;
            case R.id.button_camera_action:
                Intent intentLaunchCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (mPhotoFile == null) {
                    BasicDialog dialog = BasicDialog.newInstance(BasicDialog.ERR_PATH_NOT_FOUND);
                    dialog.show(getFragmentManager(), ERR_PATH_NOT_FOUND_TAG);
                } else if (intentLaunchCamera.resolveActivity(mPackageManager) == null) {
                    BasicDialog dialog = BasicDialog.newInstance(BasicDialog.ERR_NO_CAMERA);
                    dialog.show(getFragmentManager(), ERR_NO_CAMERA_TAG);
                } else {
                    Uri uri = Uri.fromFile(mPhotoFile);
                    intentLaunchCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intentLaunchCamera, INT_CAMERA_ACTION_REQUEST);
                }

                break;
            case R.id.image_view_person_photo:
                PhotoDialog photoDialog = PhotoDialog.newInstance(mPerson.getUUID());
                photoDialog.show(getFragmentManager(), STRING_PHOTO_DIALOG_TAG);
                break;

        }
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.edit_view_person_name:
                longClick = true;
                mName.setKeyListener(mKeyListener);
                mName.requestFocus();
                break;
            case R.id.edit_view_person_gender:
                GenderDialog genderDialog = new GenderDialog();
                genderDialog.setTargetFragment(this, INT_GENDER_REQUEST);
                genderDialog.show(getFragmentManager(), STRING_GENDER_DIALOG_TAG);
                //// TODO: 9/13/2016  Dialog thing
                break;
            case R.id.edit_view_person_birth:
                showDateDialog(mPerson.getDate(), DateDialog.INT_FROM_LONG_CLICK);
                break;
            case R.id.edit_view_person_intro:
                longClick = true;
                mIntro.setKeyListener(mKeyListener);
                mIntro.requestFocus();
                break;
            case R.id.button_camera_action:

                break;
            case R.id.image_view_person_photo:
                if (mPhotoFile != null && mPhotoFile.exists()) {
                    Intent intentLosslessPhoto = new Intent(Intent.ACTION_VIEW);
                    intentLosslessPhoto.setDataAndType(Uri.fromFile(mPhotoFile), "image/*");
                    // You CANNOT call setData and setType separately,
                    // Accordingly to the android dev guide, they would nullify each other,
                    // further more, from Javadoc : setType would clear whatever
                    // that was previously set (e.g. setData(Uri))
                    if (intentLosslessPhoto.resolveActivity(mPackageManager) == null) {
                        BasicDialog dialog = BasicDialog.newInstance(BasicDialog.ERR_NO_VIEWER);
                        dialog.show(getFragmentManager(), ERR_NO_IMAGE_VIEWER_TAG);
                    } else {
                        startActivity(intentLosslessPhoto);
                    }
                }
                break;

        }
        //// TODO: 9/13/2016  allow edit 
        return false;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        Log.i(TAG, "onFocusChange");
        switch (view.getId()) {
            case R.id.edit_view_person_name:
                if (b) {
                    if (!longClick) {
                        copyToClipboard(STRING_CLIPBOARD_NAME);
                    }
                    longClick = false;
                } else {
                    mName.setKeyListener(null);
                    hideKeyboard(view);
                }
                break;
            case R.id.edit_view_person_gender:
                if (b) {
                    if (mPerson.getGender() == Gender.FEMALE ||
                        mPerson.getGender() == Gender.OTHER ||
                        mPerson.getGender() == Gender.MALE) {
                        copyToClipboard(STRING_CLIPBOARD_GENDER);
                    }
                }
                break;
            case R.id.edit_view_person_birth:
                if (b) {
                    if (mPerson.getDate().getTime() > System.currentTimeMillis() - Age.ONE_HUNDRED_N_FIFTY_YEARS) {
                        copyToClipboard(STRING_CLIPBOARD_BIRTH);
                    }
                }
                break;
            case R.id.edit_view_person_intro:
                if (b) {
                    if (!longClick) {
                        copyToClipboard(STRING_CLIPBOARD_INTRO);
                    }
                    longClick = false;
                } else {
                    mIntro.setKeyListener(null);
                    hideKeyboard(view);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        switch (requestCode) {
            case INT_DATE_REQUEST:
                if (dataIntent == null || resultCode != Activity.RESULT_OK) {
                    return;
                } else if (resultCode == Activity.RESULT_OK) {
                    Date date = (Date) dataIntent.getSerializableExtra(STRING_EXTRA_DATE_TIME_INTENT);
                    TimeDialog timeDialog = TimeDialog.newInstance(date);
                    timeDialog.setTargetFragment(this, INT_TIME_REQUEST);
                    timeDialog.show(getFragmentManager(), STRING_TIME_DIALOG_TAG);
                } else {
                    return;
                }
                break;
            case INT_TIME_REQUEST:
                if (dataIntent == null) {
                    return;
                }
                Date date = (Date) dataIntent.getSerializableExtra(STRING_EXTRA_DATE_TIME_INTENT);
                if (resultCode == Activity.RESULT_OK) {
                    mPerson.setDate(date);
                    mBirth.setText(getFormattedDate(date));
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    showDateDialog(date, DateDialog.INT_FROM_TIME_PICKER);
                } else {
                    return;
                }
                break;
            case INT_GENDER_REQUEST:
                if (resultCode != Activity.RESULT_OK || dataIntent == null) {
                    return;
                } else if (resultCode == Activity.RESULT_OK) {
                    int genderResult = dataIntent.getIntExtra(STRING_EXTRA_INT_INTENT, INT_PLACE_HOLDER);
                    switch (genderResult) {
                        case GenderDialog.FEMALE:
                            genderResult = R.string.gender_female;
                            mPerson.setGender(Gender.FEMALE);
                            break;
                        case GenderDialog.OTHER:
                            genderResult = R.string.gender_other;
                            mPerson.setGender(Gender.OTHER);
                            break;
                        case GenderDialog.MALE:
                            genderResult = R.string.gender_male;
                            mPerson.setGender(Gender.MALE);
                            break;
                    }
                    mGender.setText(genderResult);
                    mGender.clearFocus();
                } else {
                    return;
                }
                break;
            case INT_DELETE_REQUEST:
                if (resultCode == Activity.RESULT_OK && dataIntent != null) {
                    PersonLab.get(getActivity()).deletePerson(mPerson.getUUID());
                    getActivity().finish();
                } else {
                    return;
                }
                break;
            case INT_CAMERA_ACTION_REQUEST:
                updatePhoto();
                break;
        }
    }

    private void copyToClipboard(String tag) {
        ClipboardManager clipboardManager = (ClipboardManager)
                getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("placeHolder", "");
        switch (tag) {
            case STRING_CLIPBOARD_NAME:
                clipData = ClipData.newPlainText(STRING_CLIPBOARD_NAME, mPerson.getName());
                break;
            case STRING_CLIPBOARD_GENDER:
                String gender = "";
                if (mPerson.getGender() == Gender.OTHER) {
                    gender = getString(R.string.gender_other);
                } else if (mPerson.getGender() == Gender.FEMALE) {
                    gender = getString(R.string.gender_female);
                } else if (mPerson.getGender() == Gender.MALE) {
                    gender = getString(R.string.gender_male);
                }
                clipData = ClipData.newPlainText(STRING_CLIPBOARD_GENDER, gender);
                break;
            case STRING_CLIPBOARD_BIRTH:
                clipData =
                        ClipData.newPlainText(STRING_CLIPBOARD_BIRTH, getFormattedDate(mPerson.getDate()));
                break;
            case STRING_CLIPBOARD_INTRO:
                clipData = ClipData.newPlainText(STRING_CLIPBOARD_INTRO, mPerson.getIntro());
                break;
            case STRING_CLIPBOARD_ASSOCIATION:
                //// TODO: 9/13/2016  get the list.
//                clipData = ClipData.newPlainText(STRING_CLIPBOARD_ASSOCIATION, mPerson.getName());
                break;
            case STRING_CLIPBOARD_NOTE:
                //// TODO: 9/13/2016  get the side note.
//                clipData = ClipData.newPlainText(STRING_CLIPBOARD_NOTE, mPerson.getName());
                break;
        }
        clipboardManager.setPrimaryClip(clipData);
    }

    private void hideKeyboard(View view) {
        InputMethodManager imm =
                (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private String getFormattedDate(Date date) {
        String dateFormatted = DateFormat.format("dd MMM, yyyy,\nhh:mm a", date).toString();
        return dateFormatted;
    }

    private void showDateDialog(Date date, int fromWhere) {
        DateDialog dateDialog = DateDialog.newInstance(date, fromWhere);
        dateDialog.setTargetFragment(this, INT_DATE_REQUEST);
        dateDialog.show(getFragmentManager(), STRING_DATE_DIALOG_TAG);
    }

    private void clearFocus() {
        mName.setKeyListener(null);
        mName.clearFocus();
        mIntro.setKeyListener(null);
        mIntro.clearFocus();
    }



    private void updatePhoto() {
        //// TODO: 9/15/2016  Let User decide the size
        //// TODO: 9/15/2016  SharedPreferences
        int thumbnailSizeInDP = 80;
        int thumbnailSizeInPixel = PictureUtils.dipToPixel(thumbnailSizeInDP, getActivity());
        mPhoto.getLayoutParams().height = thumbnailSizeInPixel;
        mPhoto.getLayoutParams().width = thumbnailSizeInPixel;

        File thumbnailCache =
                PersonLab.get(getActivity()).getThumbnailFile(mPerson, Thumbnail.Size.SMALL);
        if (!thumbnailCache.exists()) {
            if (!mPhotoFile.exists() || mPhotoFile == null) {
                mPhoto.setImageDrawable(null);
            } else {
                Bitmap bitmap = PictureUtils.
                        getScaleBitmap(mPerson, thumbnailSizeInDP, Thumbnail.Size.SMALL, getActivity());
                mPhoto.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
                mPhoto.setImageBitmap(bitmap);
            }
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(thumbnailCache.getPath());
            mPhoto.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.background));
            mPhoto.setImageBitmap(bitmap);
        }
    }
}
