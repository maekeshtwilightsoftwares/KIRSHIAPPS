package com.twilight.kirshiapps.utils;

import android.text.TextUtils;
import android.widget.EditText;

import static com.twilight.kirshiapps.utils.Constants.MAX_PHONE_NUMBER;

public class Validation {



    public static Boolean isNotEmpty(String data) {
        return !TextUtils.isEmpty(data);
    }

    public static Boolean isValidNumber(String data) {
        return data.length()==10;
    }
}
