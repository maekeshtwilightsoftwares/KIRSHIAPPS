package com.twilight.kirshiapps.view.fragment.home;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.db.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.twilight.kirshiapps.BaseActivity.kirshDB;

public class HomeFragmentViewModel extends ViewModel {

    private String phoneNumber;
    String currAmount;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    MutableLiveData<String > live = new MutableLiveData<String>();
    MutableLiveData<String > error = new MutableLiveData<String>();
    MutableLiveData<List<TransactionEntity> > transList = new MutableLiveData<List<TransactionEntity>>();
    MutableLiveData<Integer> userType = new MutableLiveData<Integer>();

    public void getAccount(){

        kirshDB.userDao().findByPhoneNumber(phoneNumber).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<UserEntity>() {
                    @Override
                    public void onSuccess(@NonNull UserEntity userEntity) {
                        if(userEntity != null){
                            live.postValue(userEntity.walletAmount);
                            userType.postValue(userEntity.userType);
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

    public void checkNumber(String phoneNumber,SetOnPhoneNumberCheck setOnPhoneNumberCheck ){

        kirshDB.userDao().findByPhoneNumber(phoneNumber).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<UserEntity>() {
                    @Override
                    public void onSuccess(@NonNull UserEntity userEntity) {
                        if(userEntity != null){
                            setOnPhoneNumberCheck.OnPhoneNumberCheck(true);
                        }
                        else {
                            setOnPhoneNumberCheck.OnPhoneNumberCheck(false);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        setOnPhoneNumberCheck.OnPhoneNumberCheck(false);

                    }
                });

    }


    public void getTransList(){

        kirshDB.transactionDao().findByPhoneNumber(phoneNumber).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<TransactionEntity>>() {
                    @Override
                    public void onSuccess(@NonNull List<TransactionEntity> transactionEntities) {
                        transList.postValue(transactionEntities);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public void createTransactionDebit(String amount, String phoneNumberTo){

        String transactionID = "TRNX" + new Random().nextInt(10000);

        TransactionEntity transactionEntityDebit = new TransactionEntity();
        transactionEntityDebit.transactionID = transactionID;
        transactionEntityDebit.trID = String.valueOf(new Random().nextInt(10000));
        transactionEntityDebit.amount = amount;
        transactionEntityDebit.sentNumber = phoneNumber;
        transactionEntityDebit.creditType = 0;

        TransactionEntity transactionEntityCredit = new TransactionEntity();
        transactionEntityCredit.transactionID = transactionID;
        transactionEntityCredit.trID = String.valueOf(new Random().nextInt(10000));
        transactionEntityCredit.amount = amount;
        transactionEntityCredit.sentNumber = phoneNumberTo;
        transactionEntityCredit.creditType = 1;

        TransactionEntity[] transactionEntityList = new TransactionEntity[]{transactionEntityCredit,transactionEntityDebit};


                kirshDB.transactionDao().insertAll(transactionEntityList).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
//                                live.postValue(amount);
                                getAccount();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                e.printStackTrace();
                                error.postValue("Amount send fail, Try again");
                            }
                        });


    }

    interface SetOnPhoneNumberCheck{

        public void OnPhoneNumberCheck(boolean isValid);
    }
}
