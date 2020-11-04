package com.twilight.kirshiapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.android.material.textfield.TextInputLayout;
import com.twilight.kirshiapps.db.KirshDB;
import com.twilight.kirshiapps.utils.KirshiSharedPreference;

public class BaseActivity extends AppCompatActivity {

    public static KirshDB kirshDB = null;
    public static String phoneNumberS = null;
    public static KirshiSharedPreference sharedPreference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initDB();
        checkLogin();
    }

    private void checkLogin() {
        sharedPreference = new KirshiSharedPreference(this);
        if (!TextUtils.isEmpty(sharedPreference.getUserPhoneNumber())){
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            NavController navController = navHostFragment.getNavController();
            NavGraph navGraph = navController.getNavInflater().inflate(R.navigation.nav_base_graph);
            navGraph.setStartDestination(R.id.homeFragment);
            navController.setGraph(navGraph);
        }
    }

    private void initDB() {
        kirshDB =  KirshDB.getInstance(getApplicationContext());
    }
}