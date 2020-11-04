package com.twilight.kirshiapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.twilight.kirshiapps.db.KirshDB;

public class BaseActivity extends AppCompatActivity {

    public static KirshDB kirshDB = null;
    public static String phoneNumberS = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initDB();
    }

    private void initDB() {
        kirshDB =  KirshDB.getInstance(getApplicationContext());
    }
}