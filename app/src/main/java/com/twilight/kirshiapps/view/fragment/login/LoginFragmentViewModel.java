package com.twilight.kirshiapps.view.fragment.login;

import androidx.lifecycle.ViewModel;

class LoginFragmentViewModel extends ViewModel{

    String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
