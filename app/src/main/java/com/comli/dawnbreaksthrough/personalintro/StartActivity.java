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
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawer = LayoutUtils.setupDrawer(mToolbar, R.id.drawer_layout_start, this);

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
        super.onResume();

        LayoutUtils.setDrawerItemListener(this);
        mDrawer.setSelection(LayoutUtils.IDENTIFIER_HOME, false); //set default selection
        // call setSelection before setDrawerBehavior

        LayoutUtils.setDrawerBehavior(LayoutUtils.RED, LayoutUtils.HOME);
        LayoutUtils.setRippleColor(LayoutUtils.RED, LayoutUtils.HOME, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
