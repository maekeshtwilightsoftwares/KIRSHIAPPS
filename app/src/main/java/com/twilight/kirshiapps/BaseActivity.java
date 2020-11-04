package com.twilight.kirshiapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.twilight.kirshiapps.db.DbConnection;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initDB();
    }

    private void initDB() {
        DbConnection.getInstance().setupDB(getApplicationContext());
    }
}