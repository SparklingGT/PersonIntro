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
public class LayoutUtils
{
    private static final int INT_NULL_POSITION = -1;
    public static final int INT_FROM_HOME = 3258;
    public static final int INT_FROM_CARD = 23579;
    public static final int INT_FROM_PREFS = 23;
    public static final long LONG_HOME_IDENTIFIER = 37529;
    public static final long LONG_PREFS_IDENTIFIER = 329;

    private static PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
            .withDisabledIconColorRes(R.color.icon)
            .withDisabledTextColorRes(R.color.item_text_drawer)
            .withIdentifier(37529)
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_home)
            .withName("Home")
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer);


    private static PrimaryDrawerItem itemSetting = new PrimaryDrawerItem()
            .withDisabledIconColorRes(R.color.icon)
            .withDisabledTextColorRes(R.color.item_text_drawer)
            .withIdentifier(329)
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_settings)
            .withName("Setting")
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer);


    private static SecondaryDrawerItem itemCredit = new SecondaryDrawerItem()
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_loyalty)
            .withName("Credit")
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer)
            .withSelectable(false)
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
            {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                    return false;
                }
            });

    private static SecondaryDrawerItem itemAbout = new SecondaryDrawerItem()
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_info_outline)
            .withName("About")
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer)
            .withSelectable(false)
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
            {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                    return false;
                }
            });

    private static SecondaryDrawerItem itemGitHub = new SecondaryDrawerItem()
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_call_made)
            .withName("gitHub")
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer)
            .withSelectable(false)
            .withOnDrawerItemClickListener(new com.mikepenz.materialdrawer.Drawer.OnDrawerItemClickListener()
            {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    return false;
                }
            });

    private static DividerDrawerItem divider = new DividerDrawerItem();


    public static Drawer prepareDrawer(Toolbar toolbar, final int placeHolder, final Context context) {

        itemHome.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
        {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(context, StartActivity.class);
                context.startActivity(intent);
                return false;
            }
        });

        itemSetting.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
        {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(context, SettingActivity.class);
                context.startActivity(intent);
                return false;
            }
        });

        Drawer drawer = new DrawerBuilder((Activity) context)
                .addDrawerItems(itemHome, divider, itemSetting, divider, itemCredit, itemAbout, itemGitHub)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withRootView(placeHolder)
                .withSliderBackgroundColorRes(R.color.drawer_background)
                .withDrawerWidthDp(150)
                .withSelectedItemByPosition(INT_NULL_POSITION)
                .build();

        return drawer;
    }

    public static void defaultDrawer(int fromWhere) {
        int selectedColor;
        switch (fromWhere) {
            case INT_FROM_HOME:
                selectedColor = R.color.drawer_selected_red;
                itemHome.withEnabled(false);
                itemSetting.withEnabled(true);
                break;
            case INT_FROM_PREFS:
                selectedColor = R.color.drawer_selected_purple;
                itemHome.withEnabled(true);
                itemSetting.withEnabled(false);
                break;
            case INT_FROM_CARD:
                selectedColor = R.color.drawer_selected_green;
                itemHome.withEnabled(true);
                itemSetting.withEnabled(true);
                break;
            default:
                selectedColor = R.color.drawer_selected_red;
                break;
        }
        itemHome.withSelectedColorRes(selectedColor);
        itemSetting.withSelectedColorRes(selectedColor);
    }
}
