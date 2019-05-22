package co.pamobile.pacore.Utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by tuongvan on 3/2/18.
 */

public class BitmapClass {

    public static Bitmap getBitmapFromAssets(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        Bitmap bitmap = null;
        InputStream istr = null;
        try {
            istr = assetManager.open(path);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            try {
                bitmap = new AsyncGettingBitmapFromUrl().execute(path).get();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
        }

        return bitmap;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    //get bitmap from view
    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }

    //get bitmap from file
    public static Bitmap loadBitmapFromFile(String path) {
        File image = new File(path);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
        return bitmap;
    }


    public static class AsyncGettingBitmapFromUrl extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

            System.out.println("doInBackground");

            Bitmap bitmap = null;

            bitmap = getBitmapFromURL(params[0]);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            System.out.println("bitmap" + bitmap);

        }
    }

}
