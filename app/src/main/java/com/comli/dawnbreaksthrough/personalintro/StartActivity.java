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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = LayoutUtils.prepareDrawer(toolbar, R.id.drawer_layout_start, this);


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

        LayoutUtils.defaultDrawer(LayoutUtils.RED, LayoutUtils.HOME);
        LayoutUtils.defaultRipple(LayoutUtils.RED, this);
        mDrawer.setSelection(LayoutUtils.LONG_HOME_IDENTIFIER, false); //set default selection
    }
}
