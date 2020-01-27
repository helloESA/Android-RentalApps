package com.meproduction.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        e1 = findViewById(R.id.textEmail);
        e2 = findViewById(R.id.textPassword);
        e3 = findViewById(R.id.textConfirmPassword);
        b1 = findViewById(R.id.btn_register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String t1 = e1.getText().toString();
                String t2 = e2.getText().toString();
                String t3 = e3.getText().toString();
                if (t1.equals("")||t2.equals("")||t3.equals("")){
                    Toast.makeText(getApplicationContext(),"Form Masih Kosong", Toast.LENGTH_LONG).show();
                } else{
                    if(t2.equals(t3)){
                        Boolean cekemail = db.cekEmail(t1);
                        if(cekemail == true){
                            Boolean insert = db.insert(t1,t2);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(),"Berhasil Mendaftar", Toast.LENGTH_LONG).show();
                            }
                        } else{
                            Toast.makeText(getApplicationContext(),"Email Sudah Tersedia", Toast.LENGTH_LONG).show();
                        }
                    } Toast.makeText(getApplicationContext(),"Password Tidak Sesuai", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
