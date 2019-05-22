package co.pamobile.pacore.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dev04 on 8/25/2016.
 */
public class SharedPreference<T> {
    public static final String PREFS_NAME = "APP_NAME";
    public static final String RATED_ITEM = "RATED_ITEM";
    public static final String UPLOAD_ITEM = "UPLOAD_ITEM";
    public static final String NUM_OPEN = "NUMBER OPEN";
    public static final String TIME_UPLOAD = "TIME_UPLOAD";
    public static final String ACCEPT_POLICY = "ACCEPT POLICY";
    public static final String RATE_STATS = "RATE_STATS";
    public static final String VIEW_COUNT = "VIEW_COUNT";

    Context context;

    private static SharedPreference INSTANCE = null;

    public static SharedPreference getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = new SharedPreference(context);
        }
        return INSTANCE;
    }

    public SharedPreference(Context context) {
        super();
        this.context = context;
    }

    //these methods are used for showing privacy policy
    public void setPrivacyPolicyAcceptance(){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());

        editor.putString(ACCEPT_POLICY, date);

        editor.apply();
    }

    public String getPrivacyPolicyAcceptance(){
        SharedPreferences settings;
        String acceptedDate;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        acceptedDate = settings.getString(ACCEPT_POLICY, "");

        return acceptedDate;
    }

    public void saveDate(String key,Date date){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putLong(key, date.getTime());
        editor.apply();
    }

    public Date getDate(String key){
        SharedPreferences settings;
        long num;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        num = settings.getLong(key, 0);
        Date date = new Date();
        date.setTime(num);
        return date;
    }

    public void saveInt( String key, int num){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putInt(key, num);
        editor.apply();
    }

    public int getInt(String key){
        SharedPreferences settings;
        int num;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        num = settings.getInt(key, 0);
        return num;
    }

    public void saveBoolean( String key, boolean value){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key){
        SharedPreferences settings;
        boolean num;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        num = settings.getBoolean(key, false);
        return num;
    }

    public void saveString( String key, String value){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key){
        SharedPreferences settings;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        return settings.getString(key, null);
    }
}







