package com.twilight.kirshiapps.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.twilight.kirshiapps.utils.Constants.KIRSHI_PREFS_NAME;
import static com.twilight.kirshiapps.utils.Constants.PHONE_NUMBER;

public class KirshiSharedPreference {

    private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;

    public KirshiSharedPreference(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KIRSHI_PREFS_NAME, MODE_PRIVATE);
        edit= sharedPreferences.edit();
    }

    public void storePhoneNumber(String data) {
        edit.putString(PHONE_NUMBER, data);
        edit.commit();
    }

    public String getUserPhoneNumber(){
      return sharedPreferences.getString(PHONE_NUMBER, "");
    }

    public void clearSharedPreference(){
        edit.clear();
        edit.apply();
    }



}
