package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import com.mikepenz.materialdrawer.Drawer;

public class LicenseActivity extends OriginActivity
{


    @Override
    public Fragment getFragment() {
        return new LicenseFragment();
    }

    @Override
    public int getFragmentContainerID() {
        return R.id.fragment_container_license;
    }

    Drawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_license);
        setSupportActionBar(toolbar);

        mDrawer = LayoutUtils.setupDrawer(toolbar, R.id.drawer_layout_license, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LayoutUtils.setDrawerItemListener(this);
        mDrawer.setSelection(LayoutUtils.IDENTIFIER_LICENSE, false); //set default selection
        // call setSelection before setDrawerBehavior

        LayoutUtils.setDrawerBehavior(LayoutUtils.BLUE, LayoutUtils.LICENSE);
        LayoutUtils.setRippleColor(LayoutUtils.BLUE, this);
    }
}
