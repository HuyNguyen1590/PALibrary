package co.pamobile.pacore.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.util.TimeZone;

/**
 * Created by Dev04 on 12/9/2016.
 */

public class Utils {
    public static void showMessage(Activity mActivity, String mMessage) {
        Toast.makeText(mActivity, mMessage, Toast.LENGTH_LONG).show();
    }
    public static long my_time_in() {
        TimeZone london = TimeZone.getTimeZone("Europe/London");
        long now = System.currentTimeMillis();
        return now + london.getOffset(now);
    }

    public static void openFacebookURL(Activity mActivity, String fbURL) {
        try {
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = Utils.getFacebookPageURL(mActivity, fbURL);
            facebookIntent.setData(Uri.parse(facebookUrl));
            mActivity.startActivity(facebookIntent);
        } catch (Exception e) {
            e.printStackTrace();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbURL));
            mActivity.startActivity(intent);
        }
    }
    public static void openYouTubeURL(Activity mActivity, String YouTubeURL) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.youtube");
            intent.setData(Uri.parse(YouTubeURL));
            mActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(YouTubeURL));
            mActivity.startActivity(intent);
        }
    }
    public static String getFacebookPageURL(Context context, String fbURL) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + fbURL;
            } else { //older versions of fb app
                return fbURL;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return fbURL; //normal web url
        }
    }

    public static void showDialog(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }


}
