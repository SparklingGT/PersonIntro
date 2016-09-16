package com.comli.dawnbreaksthrough.personalintro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

public class StartActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_home)
                .withName("Home")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(R.color.drawer_selected);


        PrimaryDrawerItem itemSetting = new PrimaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_settings)
                .withName("Setting")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(R.color.drawer_selected);

        SecondaryDrawerItem itemCredit = new SecondaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_loyalty)
                .withName("Credit")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(R.color.drawer_selected);

        SecondaryDrawerItem itemAbout = new SecondaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_info_outline)
                .withName("About")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(R.color.drawer_selected);

        SecondaryDrawerItem itemGitHub = new SecondaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_call_made)
                .withName("gitHub")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(R.color.drawer_selected);

        DividerDrawerItem divider = new DividerDrawerItem();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Drawer drawer = new DrawerBuilder(this)
                .addDrawerItems(itemHome, divider, itemSetting, divider, itemCredit, itemAbout, itemGitHub)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withRootView(R.id.drawer_layout)
                .withSliderBackgroundColorRes(R.color.drawer_background)
                .withDrawerWidthDp(150)
                .build();


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment =  fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new PersonListFragment();
        }
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();


    }
}
