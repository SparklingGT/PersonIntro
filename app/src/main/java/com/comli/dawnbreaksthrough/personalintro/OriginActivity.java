package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by SparklingGT on 9/22/2016.
 */

public abstract class OriginActivity extends AppCompatActivity
{
    public abstract Fragment getFragment();

    public abstract int getFragmentContainerID();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentContainerID());
        if (fragment == null) {
            fragment = getFragment();
            fm.beginTransaction().replace(getFragmentContainerID(), fragment).commit();
        }
    }
}
