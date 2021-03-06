package com.comli.dawnbreaksthrough.personalintro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
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
    private static final int NULL_POSITION = -1;
    private static final String ERR_NO_BROWSER_DIALOG = "ERR_NoBrowser";
    private static final String ABOUT_DIALOG_TAG = "aboutDialog";
    private static final String CREDIT_DIALOG_TAG = "creditDialog";

    // from where?
    public static final int HOME = 3258;
    public static final int CARD = 23579;
    public static final int PREFS = 23;
    public static final int LICENSE = 1111;

    // color theme
    public static final int RED = 342;
    public static final int GREEN = 4;
    public static final int PURPLE = 11;
    public static final int BLUE = 12415;

    // identifier
    public static final long IDENTIFIER_HOME = 37529;
    public static final long IDENTIFIER_PREFS = 329;
    public static final long IDENTIFIER_LICENSE = 111;
    public static final long IDENTIFIER_DIVIDER = 357;

    private static PrimaryDrawerItem itemHome = new PrimaryDrawerItem()
            .withDisabledIconColorRes(R.color.icon)
            .withDisabledTextColorRes(R.color.item_text_drawer)
            .withIdentifier(IDENTIFIER_HOME)
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_home)
            .withName(R.string.item_home)
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer);


    private static PrimaryDrawerItem itemSetting = new PrimaryDrawerItem()
            .withDisabledIconColorRes(R.color.icon)
            .withDisabledTextColorRes(R.color.item_text_drawer)
            .withIdentifier(IDENTIFIER_PREFS)
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_settings)
            .withName(R.string.item_setting)
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer);


    private static SecondaryDrawerItem itemCredit = new SecondaryDrawerItem()
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_loyalty)
            .withName(R.string.item_credit)
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
            .withName(R.string.item_about)
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
            .withName(R.string.item_github)
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer)
            .withSelectable(false);

    private static PrimaryDrawerItem itemLicense = new PrimaryDrawerItem()
            .withDisabledIconColorRes(R.color.icon)
            .withDisabledTextColorRes(R.color.item_text_drawer)
            .withIdentifier(IDENTIFIER_LICENSE)
            .withIconColorRes(R.color.icon)
            .withSelectedIconColorRes(R.color.icon)
            .withIcon(GoogleMaterial.Icon.gmd_favorite)
            .withName(R.string.item_license)
            .withTextColorRes(R.color.item_text_drawer)
            .withSelectedTextColorRes(R.color.item_text_drawer);

    private static DividerDrawerItem divider = new DividerDrawerItem()
            .withIdentifier(IDENTIFIER_DIVIDER)
            .withSelectable(false);


    // -----------------Method starts here------------------------------//


    public static void setDrawerItemListener(final Context context) {

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

        itemGitHub.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
        {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                PackageManager packageManager = context.getPackageManager();
                Intent intentBrowse = new Intent(Intent.ACTION_VIEW
                        , Uri.parse("https://github.com/SparklingGT/PersonIntro"));

                if (intentBrowse.resolveActivity(packageManager) == null) {
                    BasicDialog dialog = BasicDialog.newInstance(BasicDialog.ERR_NO_BROWSER);
                    dialog.show(((AppCompatActivity)context).getSupportFragmentManager(), ERR_NO_BROWSER_DIALOG);

                } else {
                    context.startActivity(intentBrowse);
                }
                return false;
            }
        });

        itemAbout.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
        {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                BasicDialog dialog = BasicDialog.newInstance(BasicDialog.ABOUT);
                dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), ABOUT_DIALOG_TAG);
                return false;
            }
        });

        itemCredit.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
        {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                BasicDialog dialog = BasicDialog.newInstance(BasicDialog.CREDIT);
                dialog.show(((AppCompatActivity) context).getSupportFragmentManager(), CREDIT_DIALOG_TAG);
                return false;
            }
        });

        itemLicense.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener()
        {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Intent intent = new Intent(context, LicenseActivity.class);
                context.startActivity(intent);
                return false;
            }
        });

    }

    public static Drawer setupDrawer(Toolbar toolbar, final int placeHolder, final Context context) {

        Drawer drawer = new DrawerBuilder((Activity)context)
                .addDrawerItems(itemHome, divider, itemSetting, divider, itemCredit, itemAbout, itemGitHub, divider, itemLicense)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withRootView(placeHolder)
                .withSliderBackgroundColorRes(R.color.drawer_background)
                .withDrawerWidthDp(150)
                .withSelectedItemByPosition(NULL_POSITION)
                .build();

        return drawer;
    }

    /**
     *
     * @param color what color would you like ?
     * @param fromWhere where do you call this method ?
     */
    public static void setDrawerBehavior(int color, int fromWhere) {
        int selectedColor;
        switch (color) {
            case RED:
                selectedColor = R.color.drawer_selected_red;
                break;
            case GREEN:
                selectedColor = R.color.drawer_selected_green;
                break;
            case PURPLE:
                selectedColor = R.color.drawer_selected_purple;
                break;
            case BLUE:
                selectedColor = R.color.drawer_selected_blue;
                break;
            default:
                selectedColor = R.color.drawer_selected_red;
                break;
        }
        itemHome.withSelectedColorRes(selectedColor);
        itemSetting.withSelectedColorRes(selectedColor);
        itemLicense.withSelectedColorRes(selectedColor);

        switch (fromWhere) {
            case HOME:
                itemHome.withEnabled(false);
                itemSetting.withEnabled(true);
                itemLicense.withEnabled(true);
                break;
            case PREFS:
                itemHome.withEnabled(true);
                itemSetting.withEnabled(false);
                itemLicense.withEnabled(true);
                break;
            case CARD:
                itemHome.withEnabled(true);
                itemSetting.withEnabled(true);
                itemLicense.withEnabled(true);
                break;
            case LICENSE:
                itemHome.withEnabled(true);
                itemSetting.withEnabled(true);
                itemLicense.withEnabled(false);
                break;
        }

    }

    public static void setRippleColor(int color, Context context) {
        Resources.Theme theme = context.getTheme();
        switch (color) {
            case RED:
                theme.applyStyle(R.style.RippleRed, true);
                break;
            case GREEN:
                theme.applyStyle(R.style.RippleGreen, true);
                break;
            case PURPLE:
                theme.applyStyle(R.style.RipplePurple, true);
                break;
            case BLUE:
                theme.applyStyle(R.style.RippleBlue, true);
                break;
        }
    }
}
