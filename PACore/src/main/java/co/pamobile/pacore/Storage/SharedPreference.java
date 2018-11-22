package co.pamobile.pacore.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import co.pamobile.pacore.Dialog.Policy;

/**
 * Created by Dev04 on 8/25/2016.
 */
public class SharedPreference<T> {
    public static final String PREFS_NAME = "APP_NAME";
    public static final String CARD = "CARD";
    public static final String RATED_ITEM = "RATED_ITEM";
    public static final String UPLOAD_ITEM = "UPLOAD_ITEM";
    public static final String NUM_OPEN = "NUMBER OPEN";
    public static final String TIME_UPLOAD = "TIME_UPLOAD";
    public static final String ACCEPT_POLICY = "ACCEPT POLICY";
    Context context;

    public SharedPreference(Context context) {
        super();
        this.context = context;
    }

    // This four methods are used for maintaining card.

    public void saveCard(List<T> itemList, String KEY) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonCard = gson.toJson(itemList);
        editor.putString(KEY, jsonCard);
        editor.commit();
    }

    public void addCard(T item, String KEY) {
        List<T> card = getCard(KEY);
        if (card == null)
            card = new ArrayList<T>();
        card.add(item);
        saveCard(card, KEY);
    }

    public void removeCard(T item, String KEY) {
        ArrayList<T> cards = getCard(KEY);
        if (cards != null) {
            for (T c : cards) {
                if (c.equals(item)) {
                    cards.remove(c);
                    break;
                }
            }
            saveCard(cards, KEY);
        }
    }

    public ArrayList<T> getCard(String KEY) {
        SharedPreferences settings;
        List<T> cards;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(KEY)) {
            String jsonCard = settings.getString(KEY, null);
            Type collectionType = new TypeToken<Collection<T>>() {
            }.getType();
            Gson gson = new Gson();
            List<T> cardItems = gson.fromJson(jsonCard, collectionType);

            cards = cardItems;
            cards = new ArrayList<T>(cards);
        } else
            return null;

        return (ArrayList<T>) cards;
    }


    //these methods are used for showing privacy policy
    public void setPrivacyPolicyAcceptance(Context context){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());

        editor.putString(ACCEPT_POLICY, date);

        editor.apply();
    }

    public String getPrivacyPolicyAcceptance(Context context){
        SharedPreferences settings;
        String acceptedDate;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        acceptedDate = settings.getString(ACCEPT_POLICY, "");

        return acceptedDate;
    }

    public void saveDate(Context context, Date num){
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putLong("Date", num.getTime());

        editor.apply();
    }

    public Date getDate(Context context){
        SharedPreferences settings;
        long num;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        num = settings.getLong("Date", 0);
        Date date = new Date();
        date.setTime(num);
        return date;
    }
}





