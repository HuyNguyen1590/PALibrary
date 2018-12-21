package co.pamobile.pacore.Dialog;

import android.app.Activity;
import android.graphics.Color;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import co.pamobile.pacore.R;
import co.pamobile.pacore.Storage.SharedPreference;

public class Policy {
    private Activity context;
    public static Policy getInstance(Activity context){
        return new Policy(context);
    }
    OnListenerAccept onListenerAccept;

    public Policy setOnListenerAccept(OnListenerAccept onListenerAccept) {
        this.onListenerAccept = onListenerAccept;
        return this;
    }

    private Policy(Activity context){
        this.context = context;
    }

    int rawID = R.raw.privacy_policy;

    public Policy setRawID(int rawID) {
        this.rawID = rawID;
        return this;
    }

    private String getPrivacyPolicy() {
        InputStream inputStream = context.getResources().openRawResource(rawID);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = buffReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return "";
        }
        return text.toString();
    }

    public void loadPrivacyPolicy() {
        final SharedPreference sharedPreference = new SharedPreference(context);
        if (sharedPreference.getPrivacyPolicyAcceptance(context).equals("")) {
            MaterialDialog dialog = new MaterialDialog.Builder(context)
                    .title("Privacy Policy")
                    .content(getPrivacyPolicy())
                    .positiveText("ACCEPT")
                    .negativeText("DECLINE").negativeColor(Color.GRAY)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            //accepted, then save date
                            sharedPreference.setPrivacyPolicyAcceptance(context);
                            super.onPositive(dialog);
                            if(onListenerAccept!=null){
                                onListenerAccept.onAccept();
                            }

                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            //decline, then exit app
                            super.onNegative(dialog);
                            context.finish();
                        }
                    }).build();


            dialog.show();
        } else {
            if(onListenerAccept!=null){
                onListenerAccept.onAccept();
            }
        }
    }

    public void showAcceptedPolicy(){
        final SharedPreference sharedPreference = new SharedPreference(context);
        new MaterialDialog.Builder(context)
                .title("Privacy Policy")
                .content(getPrivacyPolicy())
                .positiveText("You accepted on " + sharedPreference.getPrivacyPolicyAcceptance(context))
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.dismiss();
                        super.onPositive(dialog);
                    }
                })
                .show();
    }

    public interface OnListenerAccept{
        void onAccept();
    }
}
