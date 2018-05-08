package co.pamobile.libraryexample;

import android.app.Activity;
import android.view.View;

import co.pamobile.pacore.View.ViewPattern;

public class MainView extends ViewPattern{
    public MainView(Activity activity) {
        super(activity);
    }

    public MainView(View v) {
        super(v);
    }
}
