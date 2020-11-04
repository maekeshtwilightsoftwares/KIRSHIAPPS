package com.twilight.kirshiapps.view.fragment.register;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twilight.kirshiapps.db.dao.UserDao;
import com.twilight.kirshiapps.db.entity.UserEntity;

import java.util.List;
import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.observers.ResourceCompletableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.twilight.kirshiapps.BaseActivity.kirshDB;

public class RegisterFragmentViewModel extends ViewModel{

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    MutableLiveData<String > live = new MutableLiveData<String>();
    MutableLiveData<String > error = new MutableLiveData<String>();

    public void registerAccount(){

        UserEntity userEntityinsert = new UserEntity();
        userEntityinsert.uid = 10000 + new Random().nextInt(90000);
        userEntityinsert.phonenumber = phoneNumber;
        userEntityinsert.userType = 0;
        userEntityinsert.walletAmount = "0";

        UserDao userDao = kirshDB.userDao();

        userDao.findByPhoneNumber(phoneNumber).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<UserEntity>() {
                    @Override
                    public void onSuccess(@NonNull UserEntity userEntity) {

                        if(userEntity != null){
                            error.postValue("User already exist");
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

//                        Completable.fromAction(() -> userDao.insertAll(userEntityinsert)).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
//                            @Override
//                            public void onSubscribe(@NonNull Disposable d) {
//                                live.postValue("on subscribe");
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                live.postValue("on complete");
//                            }
//
//                            @Override
//                            public void onError(@NonNull Throwable e) {
//                                live.postValue("on error");
//                            }
//                        });

                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {

                                userDao.insert(userEntityinsert);
                            }
                        });

                        live.postValue("Registered Successfully");

                    }

                });
    }

    public void getAll(){

        kirshDB.userDao().getAll().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<UserEntity>>() {
                    @Override
                    public void onSuccess(@NonNull List<UserEntity> userEntities) {
                        live.postValue("Registered Successfully : "+userEntities.size());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        live.postValue("No record");
                    }
                });

    }




}



