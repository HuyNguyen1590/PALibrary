package co.pamobile.libraryexample;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import java.util.Arrays;
import java.util.List;

import co.pamobile.pacore.BaseLeftMenuActivity;
import co.pamobile.pacore.Dialog.AppItem;
import co.pamobile.pacore.Dialog.DialogExit;
import co.pamobile.pacore.Dialog.FeatureBanner;
import co.pamobile.pacore.Dialog.Policy;
import co.pamobile.pacore.MoreApp.MoreAppFragment;
import co.pamobile.pacore.Utilities.ArrayConvert;
import co.pamobile.pacore.Utilities.JsonConvert;

public class MainActivity extends BaseLeftMenuActivity {
    List<AppItem> listFeatureApp;
    FeatureBanner featureBanner;

    @Override
    protected int setViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppItem[] arrApp = JsonConvert.gson.fromJson(FEATURE_APPS, AppItem[].class);
        listFeatureApp = Arrays.asList(arrApp);
        featureBanner = JsonConvert.gson.fromJson(FEATURE_BANNER, FeatureBanner.class);
        loadNavigation(R.array.nav_drawer_items,R.array.nav_drawer_icons);

        navDrawerListAdapter.setMoreAppItems(ArrayConvert.toArrayList(listFeatureApp));

        MoreAppFragment moreAppFragment = new MoreAppFragment();
        moreAppFragment.Config(this,listFeatureApp);
        //moreAppFragment.getMoreApp();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_main, moreAppFragment).commit();
        //CheckPermission.getInstance(this).RequestPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
    @Override
    protected void onResume() {
        super.onResume();
        Policy.getInstance(this).setOnListenerAccept(() -> {}).loadPrivacyPolicy();

    }

    public static final String FEATURE_APPS = "[{\"icon\":\"https://lh3.googleusercontent.com/ju-FPEkXNJIY9MX43m51VJNQxfJEtRiBFaFoSaQ0eCPxAVyn5fym3EAQAR6n2jUopA\",\"name\":\"Dinosaur Sim\",\"packageName\":\"co.pamobile.gamestudio.jurassicdinosaurs\"},{\"icon\":\"https://lh3.googleusercontent.com/oI1GZ83i_lPljk-j0LAm51regePegG8gj0ran0I-81zOiZR_Cs0XeomQUyep85DU3bs\",\"name\":\"Fast Battery Charger\",\"packageName\":\"co.pamobile.fastbatterycharger\"},{\"icon\":\"https://lh3.googleusercontent.com/9ldqrfQx3Y1lJZOCMGOrUz80dmmcIImFKjiAwGWK55bnMZ7hxcp6WY3Ow-vymZXXcA\",\"name\":\"AddOns Maker\",\"packageName\":\"co.pamobile.mcpe.addonsmaker\"}]";

    public static final String FEATURE_BANNER = "{   \"url\": \"https://i0.wp.com/ae01.alicdn.com/kf/HTB1p8XlPFXXXXbLXFXXq6xXFXXXY.jpg?size=248769&height=300&width=1000&hash=c350934380fc94daa15b8f2a43d9cef6\",   \"destination\": \"https://play.google.com/store/apps/details?id=co.pamobile.gamelauncher\",   \"type\": \"google\" }";

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        DialogExit dialog = new DialogExit(MainActivity.this, listFeatureApp, featureBanner);
        dialog.setCancelable(true);
        if(!isFinishing()){
            dialog.show();
        }
        dialog.getBtnCancel().setTextColor(Color.parseColor("#000000"));
        dialog.getBtnExit().setTextColor(Color.parseColor("#FF0000"));
    }
}
