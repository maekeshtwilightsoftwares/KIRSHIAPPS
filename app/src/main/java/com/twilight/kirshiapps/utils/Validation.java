package com.twilight.kirshiapps.utils;

import android.text.TextUtils;
import android.widget.EditText;

import static com.twilight.kirshiapps.utils.Constants.MAX_PHONE_NUMBER;
import static com.twilight.kirshiapps.utils.Constants.MIN_PHONE_NUMBER;

public class Validation {

    public static Boolean isEmptyOrNot(String data) {
        return TextUtils.isEmpty(data);
    }

    public static Boolean isValidNumber(String data) {
        return data.length()>=MIN_PHONE_NUMBER && data.length()<MAX_PHONE_NUMBER;
    }
}
