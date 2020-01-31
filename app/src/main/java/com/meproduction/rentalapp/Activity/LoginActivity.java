package com.meproduction.rentalapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.meproduction.rentalapp.Database.DatabaseHelper;
import com.meproduction.rentalapp.R;

public class LoginActivity extends Activity {

    DatabaseHelper db;
    EditText e1,e2;
    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.text_Email);
        e2 = (EditText) findViewById(R.id.text_Password);
        b1 = (Button) findViewById(R.id.btn_login);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();

                Boolean validasi = db.toLogin(email,password);
                if (validasi==true) {
                    Intent in = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(in);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Email dan Password Salah", Toast.LENGTH_LONG).show();
                }
            }
        });
        b2 = findViewById(R.id.btn_toRegist);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(in);
            }
        });
    }
}
