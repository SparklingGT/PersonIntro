package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.mikepenz.materialdrawer.Drawer;

public class SettingActivity extends AppCompatActivity
{
    public static class SettingFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }


    Drawer mDrawer;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.PrefsTheme);
        setContentView(R.layout.activity_setting);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_prefs);
        setSupportActionBar(mToolbar);

        mDrawer = LayoutUtils.setupDrawer(mToolbar, R.id.drawer_layout_prefs, this);

        if (getFragmentManager().findFragmentById(R.id.fragment_container_prefs) == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_prefs, new SettingFragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LayoutUtils.setupDrawerItemListener(this);
        mDrawer.setSelection(LayoutUtils.IDENTIFIER_PREFS, false); // set default selection
        // call setSelection before setDrawerBehavior

        LayoutUtils.setDrawerBehavior(LayoutUtils.PURPLE, LayoutUtils.PREFS);
        LayoutUtils.setRippleColor(LayoutUtils.PURPLE, LayoutUtils.PREFS, this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    // Reserved //


//    private void setStatusBarColor() {
//        Window window = getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(ContextCompat.getColor(this, R.color.prefsPrimaryDark));
//    }
}
