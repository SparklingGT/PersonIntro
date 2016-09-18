package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        setStatusBarColor();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_prefs);
        setSupportActionBar(toolbar);
        mDrawer = LayoutUtils.prepareDrawer(toolbar, R.id.drawer_layout_prefs, this);

        if (getFragmentManager().findFragmentById(R.id.fragment_container_prefs) == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_prefs, new SettingFragment()).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        LayoutUtils.defaultDrawer(LayoutUtils.PURPLE, LayoutUtils.PREFS);
        LayoutUtils.defaultRipple(LayoutUtils.PURPLE, this);
        mDrawer.setSelection(LayoutUtils.LONG_PREFS_IDENTIFIER, false); // set default selection
    }

    private void setStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.prefsPrimaryDark));
    }
}
