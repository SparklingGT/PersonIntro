package com.comli.dawnbreaksthrough.personalintro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by SparklingGT on 9/17/2016.
 */
public class UIUtils
{
    public static void setupDrawer(Toolbar toolbar, int placeHolder, final Context context) {

        int selectedColor;
        switch (placeHolder) {
            case R.id.drawer_layout_start:
                selectedColor = R.color.drawer_selected_red;
                break;
            case R.id.drawer_layout_view_pager:
                selectedColor = R.color.drawer_selected_green;
                break;
            default:
                selectedColor = R.color.drawer_selected_red;
                break;
        }

        int defaultPosition = (placeHolder == R.id.drawer_layout_view_pager) ? -1 : 0;

        PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_home)
                .withName("Home")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(selectedColor)
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = StartActivity.newIntent(context);
                        context.startActivity(intent);
                        return false;
                    }
                });


        PrimaryDrawerItem itemSetting = new PrimaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_settings)
                .withName("Setting")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(selectedColor)
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        return false;
                    }
                });

        SecondaryDrawerItem itemCredit = new SecondaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_loyalty)
                .withName("Credit")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(selectedColor)
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        return false;
                    }
                });

        SecondaryDrawerItem itemAbout = new SecondaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_info_outline)
                .withName("About")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(selectedColor)
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        return false;
                    }
                });

        SecondaryDrawerItem itemGitHub = new SecondaryDrawerItem()
                .withIconColorRes(R.color.icon)
                .withSelectedIconColorRes(R.color.icon)
                .withIcon(GoogleMaterial.Icon.gmd_call_made)
                .withName("gitHub")
                .withTextColorRes(R.color.item_text_drawer)
                .withSelectedTextColorRes(R.color.item_text_drawer)
                .withSelectedColorRes(selectedColor)
                .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
                {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        return false;
                    }
                });

        DividerDrawerItem divider = new DividerDrawerItem();

        Drawer drawer = new DrawerBuilder((Activity) context)
                .addDrawerItems(itemHome, divider, itemSetting, divider, itemCredit, itemAbout, itemGitHub)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withRootView(placeHolder)
                .withSliderBackgroundColorRes(R.color.drawer_background)
                .withDrawerWidthDp(150)
                .withSelectedItemByPosition(defaultPosition)
                .build();

    }
}
