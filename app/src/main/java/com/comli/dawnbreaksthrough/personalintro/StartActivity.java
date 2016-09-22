package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import com.mikepenz.materialdrawer.Drawer;

public class StartActivity extends OriginActivity
{
    Drawer mDrawer;

    @Override
    public Fragment getFragment() {
        return new PersonListFragment();
    }

    @Override
    public int getFragmentContainerID() {
        return R.id.fragment_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

//        FragmentManager fragmentManager = getSupportFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = LayoutUtils.setupDrawer(toolbar, R.id.drawer_layout_start, this);

//        Fragment fragment =  fragmentManager.findFragmentById(R.id.fragment_container);
//        if (fragment == null) {
//            fragment = new PersonListFragment();
//            fragmentManager.beginTransaction()
//                    .add(R.id.fragment_container, fragment)
//                    .commit();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LayoutUtils.setDrawerItemListener(this);
        mDrawer.setSelection(LayoutUtils.IDENTIFIER_HOME, false); //set default selection
        // call setSelection before setDrawerBehavior

        LayoutUtils.setDrawerBehavior(LayoutUtils.RED, LayoutUtils.HOME);
        LayoutUtils.setRippleColor(LayoutUtils.RED, this);

    }
}
