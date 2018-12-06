package co.pamobile.pacore.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.pamobile.pacore.Dialog.Policy;
import co.pamobile.pacore.R;
import co.pamobile.pacore.Storage.SharedPreference;

/**
 * Created by tuongvan on 3/2/18.
 */

public class DefaultFunction {
    Activity mActivity;
    SharedPreference sharedPreferences;
    public  String URL_GOOGLEPLAY = "https://play.google.com/store/apps/details?id=";
    private static DefaultFunction INSTANCE = null;
    public static DefaultFunction getInstance(Activity mActivity){
        if(INSTANCE == null){
            INSTANCE = new DefaultFunction(mActivity);
        }
        return INSTANCE;
    }

    public DefaultFunction(Activity mActivity){
        this.mActivity = mActivity;
        sharedPreferences = new SharedPreference(mActivity);
    }

    //check package name if wrong then close app
    public void checkPackageName(String packageName){
        if(!mActivity.getPackageName().equals(packageName)){
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(URL_GOOGLEPLAY + mActivity.getPackageName()));
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    }

    public void rateApp() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mActivity);
        boolean rate_status = prefs.getBoolean("rate_status", false);
        final SharedPreferences.Editor editor = prefs.edit();
        int count = prefs.getInt("viewCount", 0);
        if (count % 3 == 0) {
            if (!rate_status) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("Thank you");
                builder.setMessage(R.string.message_rate_app);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL_GOOGLEPLAY + mActivity.getPackageName())));
                        //never show again
                        editor.putBoolean("rate_status", true);
                        editor.apply();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("LATER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.GRAY);
                //overrideFonts(mActivity,alertDialog.getWindow().getDecorView());
            }
        }
        count++;
        editor.putInt("viewCount", count);
        editor.apply();
    }

    public void overrideFonts(final View v,Typeface typeface) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child,typeface);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(typeface);
            }
        } catch (Exception ignored) {
        }
    }

    //check new version code
    public void checkCodeVersion(int versionCode) {
        try {
            PackageInfo pInfo = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), 0);
            int currentCodeVersion = pInfo.versionCode;
            if (currentCodeVersion < versionCode) {

                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage(R.string.message_new_version);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(URL_GOOGLEPLAY + mActivity.getPackageName()));
                        mActivity.startActivity(intent);
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.GRAY);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void confirmExit() {
        new AlertDialog.Builder(mActivity)
                .setCancelable(true)
                .setTitle("Confirm")
                .setMessage(R.string.message_exit)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mActivity.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

}
