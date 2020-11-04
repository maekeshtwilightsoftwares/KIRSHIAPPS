package com.twilight.kirshiapps.view.fragment.transcation;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.twilight.kirshiapps.db.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.twilight.kirshiapps.BaseActivity.kirshDB;
import static com.twilight.kirshiapps.BaseActivity.phoneNumberS;

public class TranscationListFragmentViewModel extends ViewModel {

    MutableLiveData<List<TransactionEntity> > transList = new MutableLiveData<List<TransactionEntity>>();

    public void getTransList(){

        kirshDB.transactionDao().findByPhoneNumber(phoneNumberS).subscribeOn(Schedulers.computation())
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
}
