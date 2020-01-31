package com.meproduction.rentalapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.meproduction.rentalapp.Database.DatabaseHelper;
import com.meproduction.rentalapp.R;

public class DaftarMobilActivity extends AppCompatActivity {

    String[] daftar;
    ListView listView;
    Menu menu;
    protected Cursor cursor;
    DatabaseHelper dbData;
    public static DaftarMobilActivity m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_mobil);

        m=this;
        dbData = new DatabaseHelper(this);

        refreshList();
    }

    private void refreshList() {
        SQLiteDatabase db = dbData.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mobil", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            daftar[i] = cursor.getString(0);
        }
        listView = findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                Intent i = new Intent(DaftarMobilActivity.this, ListDetailActivity.class);
                i.putExtra("merk", selection);
                startActivity(i);
            }
        });

        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
