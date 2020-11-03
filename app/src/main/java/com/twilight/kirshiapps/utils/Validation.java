package com.twilight.kirshiapps.utils;

import android.text.TextUtils;
import android.widget.EditText;

import static com.twilight.kirshiapps.utils.Constants.MAX_PHONE_NUMBER;

class Validation {



    public Boolean isEmptyOrNot(String data) {
        return TextUtils.isEmpty(data);
    }

    public Boolean isValidNumber(String data) {
        return data.length()>=MAX_PHONE_NUMBER;
    }
}
