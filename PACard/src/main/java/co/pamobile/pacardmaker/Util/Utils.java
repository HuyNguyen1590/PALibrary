package co.pamobile.pacardmaker.Util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimeZone;


/**
 * Created by Dev04 on 12/9/2016.
 */

public class Utils {
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

    public static Bitmap getBitmapFromAssets(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        Bitmap bitmap = null;
        InputStream istr = null;
        try {
            istr = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException ignored) {

        }
        return bitmap;
    }

    public static long my_time_in() {
        TimeZone london = TimeZone.getTimeZone("Europe/London");
        long now = System.currentTimeMillis();
        return now + london.getOffset(now);
    }


    //open image after save
    public static void gotoImage(Context context, String path) {
        final Intent intent = new Intent(Intent.ACTION_VIEW)//
                .setDataAndType(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                                android.support.v4.content.FileProvider.getUriForFile(context, context.getPackageName() + ".provider", new File(path)) : Uri.fromFile(new File(path)),
                        "image/*").addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException ignore) {
            ignore.fillInStackTrace();
            Toast.makeText(context, "Error! Go to Gallery to view !!!", Toast.LENGTH_SHORT).show();
        }

    }

    //share image from uri
    public static void ShareImage(Context context, String uri) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri photoURI = FileProvider.getUriForFile(context,
                context.getPackageName() + ".provider",
                new File(uri));
        share.putExtra(Intent.EXTRA_STREAM, photoURI);

        context.startActivity(Intent.createChooser(share, "Share"));
    }


}
