package com.twilight.kirshiapps.view.fragment.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


class LoginFragmentViewModel extends ViewModel{

    public MutableLiveData<String> getLive() {
        return live;
    }

    MutableLiveData<String > live = new MutableLiveData<String>();

}


