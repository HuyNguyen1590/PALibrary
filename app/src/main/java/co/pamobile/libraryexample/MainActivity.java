package co.pamobile.libraryexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DialogExit dialog = new DialogExit(MainActivity.this, listFeatureApp, featureBanner);
//        dialog.setCancelable(true);
//        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        dialog.show();
    }
}
