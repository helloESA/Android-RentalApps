package com.meproduction.rentalapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.meproduction.rentalapp.R;

public class splashScreen extends Activity {

    //set waktu splash screen tersebut
    private static int lama_tampil = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent roadto = new Intent(splashScreen.this,LoginActivity.class);
                startActivity(roadto);

                finish();
            }
        },lama_tampil);


    }
}
