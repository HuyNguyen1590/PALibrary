package co.pamobile.pacore.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.pamobile.pacore.R;
import co.pamobile.pacore.Storage.SharedPreference;

/**
 * Created by tuongvan on 3/2/18.
 */

public class DefaultFunction {
    Activity mActivity;

    private static DefaultFunction INSTANCE = null;

    public static DefaultFunction getInstance(Activity mActivity){
        if(INSTANCE == null){
            INSTANCE = new DefaultFunction(mActivity);
        }
        return INSTANCE;
    }

    public DefaultFunction(Activity mActivity){
        this.mActivity = mActivity;
    }

    //check package name if wrong then close app
    public void checkPackageName(String packageName){
        if(!mActivity.getPackageName().equals(packageName)){
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(Constant.URL_GOOGLEPLAY + mActivity.getPackageName()));
            mActivity.startActivity(intent);
            mActivity.finish();
        }
    }

    //Check show RateAppDialog when every three times open the app
    public void checkRateApp() {
        final SharedPreference sharedPreference = new SharedPreference(mActivity);
        boolean rate_status = sharedPreference.getBoolean(sharedPreference.RATE_STATS);
        int count = sharedPreference.getInt(sharedPreference.VIEW_COUNT);
        if (count % 3 == 0) {
            if (!rate_status) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setTitle("Thank you");
                builder.setMessage(R.string.message_rate_app);
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.URL_GOOGLEPLAY + mActivity.getPackageName())));
                        //never show again
                        sharedPreference.saveBoolean(sharedPreference.RATE_STATS, true);
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
        sharedPreference.saveInt(sharedPreference.VIEW_COUNT, count);
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
                                Uri.parse(Constant.URL_GOOGLEPLAY + mActivity.getPackageName()));
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

    //show dialog before exit
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

    //override all font in view
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
}
