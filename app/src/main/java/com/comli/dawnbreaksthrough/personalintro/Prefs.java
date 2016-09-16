//package com.comli.dawnbreaksthrough.personalintro;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
///**
// * Created by SparklingGT on 9/16/2016.
// */
//public class Prefs
//{
//    private static final String STRING_PREFS_NAME = "Preferences";
//    private static final String STRING_THUMBNAIL_SMAILL = "profileNThumbnail";
//    private static final String STRING_THUMBNAIL_LARGE = "photoDialogSize";
//    private static final String STRING_PROFILE_TEXT_SIZE = "profileTextSize";
//    private static final String STRING_DETAIL_TEXT_SIZE = "detailTextSize";
//    private static final String STRING_FUTURE_SELECT_DATE = "future";
//
//
//    private static Prefs sPrefs;
//    private static int thumbnailDpS = 80;
//    private static int thumbnailDpL = 350;
//    private static boolean futureSelectDatepicker = false;
//
//    private static SharedPreferences mPrefs;
//    private static SharedPreferences.Editor mEditor;
//
//
//
//    public static Prefs get(Context context) {
//        if (sPrefs == null) {
//            sPrefs = new Prefs(context);
//        }
//        return sPrefs;
//    }
//
//    private Prefs(Context context) {
//        mPrefs = context.getSharedPreferences(STRING_PREFS_NAME, Context.MODE_PRIVATE);
//        mEditor = mPrefs.edit();
//    }
//
//    public static int getThumbnailDpS() {
//        thumbnailDpS = mPrefs.getInt(STRING_THUMBNAIL_SMAILL, thumbnailDpS);
//        return thumbnailDpS;
//    }
//
//    public static void setThumbnailDpS(int thumbnailDpS) {
//        mEditor.putInt(STRING_THUMBNAIL_SMAILL, thumbnailDpS);
//        mEditor.apply();
//    }
//
//    public static int getThumbnailDpL() {
//        thumbnailDpL = mPrefs.getInt(STRING_THUMBNAIL_LARGE, thumbnailDpL);
//        return thumbnailDpL;
//    }
//
//    public static void setThumbnailDpL(int thumbnailDpL) {
//        mEditor.putInt(STRING_THUMBNAIL_LARGE, thumbnailDpL);
//        mEditor.apply();
//    }
//
//    public static boolean isFutureSelectDatepicker() {
//        futureSelectDatepicker = mPrefs.getBoolean(STRING_FUTURE_SELECT_DATE, futureSelectDatepicker);
//        return futureSelectDatepicker;
//    }
//
//    public static void setFutureSelectDatepicker(boolean futureSelectDatepicker) {
//        mEditor.putBoolean(STRING_FUTURE_SELECT_DATE, futureSelectDatepicker);
//        mEditor.apply();
//    }
//}
