package com.twilight.kirshiapps.view.fragment.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginFragmentViewModel extends ViewModel{

    String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    MutableLiveData<String > live = new MutableLiveData<String>();


}



