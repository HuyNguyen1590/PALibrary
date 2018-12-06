package co.pamobile.pacore.Permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import co.pamobile.pacore.Dialog.Policy;
import co.pamobile.pacore.R;
import co.pamobile.pacore.Utilities.ArrayConvert;
import co.pamobile.pacore.Utilities.Utils;
import rebus.permissionutils.AskAgainCallback;
import rebus.permissionutils.FullCallback;
import rebus.permissionutils.PermissionEnum;
import rebus.permissionutils.PermissionManager;
import rebus.permissionutils.PermissionUtils;

public class CheckPermission {
    private static CheckPermission INSTANCE = null;
    public static CheckPermission getInstance(Activity activity){
        if(INSTANCE == null){
            INSTANCE = new CheckPermission(activity);
        }
        return INSTANCE;
    }

    private CheckPermission(Activity activity){
        this.activity = activity;
    }
    boolean isGrantedAll = false;
    Activity activity;
    AlertDialog permissionDialog;
    public void RequestPermission(final String... permissions) {
        PermissionEnum[] permissionEnums = new PermissionEnum[permissions.length];
        for(int i = 0;i< permissions.length;i++ ) {
            permissionEnums[i] = PermissionEnum.fromManifestPermission(permissions[i]);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionManager.Builder()
                    .key(2100)
                    .permission(permissionEnums)
                    .askAgain(true)
                    .callback(new FullCallback() {
                        @Override
                        public void result(ArrayList<PermissionEnum> permissionsGranted, ArrayList<PermissionEnum> permissionsDenied, ArrayList<PermissionEnum> permissionsDeniedForever, ArrayList<PermissionEnum> permissionsAsked) {
                            if (permissionsDeniedForever.size() > 0) {
                                needPermissionDialog(permissionsDenied);
                            }
                            if (permissionsDeniedForever.size() == 0 && permissionsDenied.size() > 0) {
                                RequestPermission(permissions);
                            }
                            if (permissionsGranted.size() == permissionsAsked.size()) {
                                isGrantedAll = true;
                            }
                        }
                    })
                    .askAgainCallback(new AskAgainCallback() {
                        @Override
                        public void showRequestPermission(UserResponse response) {
                            Log.e("EEE", "AskAgainCallback");
                            showDialog(response);
                        }
                    })
                    .ask(activity);
        }
    }

    void needPermissionDialog(ArrayList<PermissionEnum> permissionsDenied) {
        StringBuilder permissionName = new StringBuilder();

        permissionName.append("This app realy need to use");
        for (int i = 0; i < permissionsDenied.size(); i++) {
            if (i == permissionsDenied.size() - 1) {
                permissionName.append(" ").append("<a href='#'>" + permissionsDenied.get(i).name() + "</a>");
            } else {
                permissionName.append(" ").append("<a href='#'>" + permissionsDenied.get(i).name() + "</a>").append(",");
            }

        }
        permissionName.append(" permission. You need accept to use app");
        permissionDialog = PermissionDialog(permissionName).create();
        permissionDialog.show();
    }

    private AlertDialog.Builder PermissionDialog(StringBuilder permissionName) {
        TextView view = new TextView(activity);
        view.setText(Html.fromHtml(permissionName.toString()));
        view.setMovementMethod(LinkMovementMethod.getInstance());
        view.setPadding((int) Utils.convertDpToPixel(16), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(0), (int) Utils.convertDpToPixel(0));

        return new AlertDialog.Builder(activity)
                .setTitle("Permission needed")
                .setView(stripUnderlines(view))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivityForResult(PermissionUtils.openApplicationSettings(activity.getPackageName()), 1001);
                    }
                })
                .setCancelable(false);

    }
    private TextView stripUnderlines(TextView textView) {
        Spannable s = new SpannableString(textView.getText());
        URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
        for (URLSpan span : spans) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
        textView.setText(s);
        return textView;
    }

    private class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }

    }

    private void showDialog(final AskAgainCallback.UserResponse response) {
        new AlertDialog.Builder(activity)
                .setTitle("Permission needed")
                .setMessage("This app realy need to use this permission, you want to authorize it?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        response.result(true);
                    }
                })
                .setCancelable(false)
                .show();
    }
}
