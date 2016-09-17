package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.mikepenz.materialdrawer.Drawer;

public class StartActivity extends AppCompatActivity
{
    Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        FragmentManager fragmentManager = getSupportFragmentManager();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawer = LayoutUtils.prepareDrawer(mToolbar, R.id.drawer_layout_start, this);


        Fragment fragment =  fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new PersonListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        LayoutUtils.defaultDrawer(LayoutUtils.INT_FROM_HOME);
        mDrawer.setSelection(LayoutUtils.LONG_HOME_IDENTIFIER, false);
        super.onResume();
    }
}
