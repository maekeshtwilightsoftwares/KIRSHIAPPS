package com.twilight.kirshiapps.view.fragment.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twilight.kirshiapps.db.entity.UserEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.twilight.kirshiapps.BaseActivity.kirshDB;

public class LoginFragmentViewModel extends ViewModel{

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    MutableLiveData<String > live = new MutableLiveData<String>();
    MutableLiveData<String > error = new MutableLiveData<String>();

    public void loginAccount(){

        kirshDB.userDao().findByPhoneNumber(phoneNumber).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<UserEntity>() {
                    @Override
                    public void onSuccess(@NonNull UserEntity userEntity) {
                        if(userEntity != null){
                            live.postValue(userEntity.phonenumber);
                        }
                        else {
                            error.postValue("User Not Found");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        error.postValue("User Not Found");

                    }
                });


    }


}



