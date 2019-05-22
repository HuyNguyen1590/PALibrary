package co.pamobile.pacore.Utilities;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.format.DateFormat;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Utility class for saving images to external storage
 */
public class ProcessFile {

    //save image with name
    public static String saveImageToFolder(Bitmap bitmap, String folderName, String imgName, Context context) {
        String path = getFolderPath(folderName) + "/" + imgName + ".png";
        try {
            File file = new File(path);
            if(file.exists()){
                path = getFolderPath(folderName) + "/" + imgName+ DateFormat.format("yyyyMMdd_kkmmss", new Date()) + ".png";
            }
            FileOutputStream out = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                final Uri contentUri = Uri.fromFile(file);
                scanIntent.setData(contentUri);
                context.sendBroadcast(scanIntent);
            } else {
                context.sendBroadcast(new Intent(
                        Intent.ACTION_MEDIA_MOUNTED,
                        Uri.parse(path)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    //get folder path in folder Pictures
    public static String getFolderPath(String folderName) {
        String lvl1 = "Pictures";
        String lvl2 = folderName;

        File folderLvl1 = checkPath(Environment.getExternalStorageDirectory(), lvl1);
        File folderLvl2 = checkPath(folderLvl1, lvl2);

        return folderLvl2.getPath();
    }

    private static File checkPath(File pathParent, String childName) {
        File pathLvl2 = new File(pathParent, childName);
        if (!pathLvl2.exists()) {
            pathLvl2.mkdir();
        }
        return pathLvl2;
    }

    //save image with name generate
    public static String saveImageToFolder(Bitmap bitmap, String folderName, Context context) {
        return saveImageToFolder(bitmap, folderName, generateImageFileName(), context);
    }

    //Generate image name
    public static String generateImageFileName() {
        return "IMG_" + DateFormat.format("yyyyMMdd_kkmmss", new Date());
    }

    //open image after save
        public static void openImage(Context context, String path) {
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
//    public static void shareImage(Context context, String uri) {
//        Intent share = new Intent(Intent.ACTION_SEND);
//        share.setType("image/png");
//        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri photoURI = FileProvider.getUriForFile(context,
//                context.getPackageName() + ".provider",
//                new File(uri));
//        share.putExtra(Intent.EXTRA_STREAM, photoURI);
//
//        context.startActivity(Intent.createChooser(share, "Share"));
//    }


}

