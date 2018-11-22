package co.pamobile.libraryexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import co.pamobile.pacore.Dialog.AppItem;
import co.pamobile.pacore.Dialog.DialogExit;
import co.pamobile.pacore.Dialog.FeatureBanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DialogExit dialog = new DialogExit(MainActivity.this, new ArrayList<AppItem>(), new FeatureBanner());
        dialog.setCancelable(true);
        dialog.show();

        //DialogBuilder.getInstance().DialogExit(this);
    }
}
