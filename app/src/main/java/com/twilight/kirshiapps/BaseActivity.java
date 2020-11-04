package com.twilight.kirshiapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.twilight.kirshiapps.db.KirshDB;
import com.twilight.kirshiapps.db.dao.UserDao;
import com.twilight.kirshiapps.db.entity.UserEntity;

import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BaseActivity extends AppCompatActivity {

    public static KirshDB kirshDB = null;
    public static String phoneNumberS = null;
    public static int userType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initDB();
        seedData();
    }

    private void initDB() {
        kirshDB =  KirshDB.getInstance(getApplicationContext());
    }

    private void seedData(){

        kirshDB.userDao().getAll().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<UserEntity>>() {
                    @Override
                    public void onSuccess(@NonNull List<UserEntity> userEntities) {

                        if(userEntities.size() < 1){
                            startSeeding();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        startSeeding();
                    }
                });
    }

    private void startSeeding(){

        UserEntity userEntityinsert = new UserEntity();
        userEntityinsert.uid = 10000 + new Random().nextInt(90000);
        userEntityinsert.phonenumber = "97909790847";
        userEntityinsert.userType = 1;
        userEntityinsert.walletAmount = "0";

        UserEntity userEntityinsert1 = new UserEntity();
        userEntityinsert1.uid = 10000 + new Random().nextInt(90000);
        userEntityinsert1.phonenumber = "9983456875";
        userEntityinsert1.userType = 1;
        userEntityinsert1.walletAmount = "0";

        UserEntity userEntityinsert2 = new UserEntity();
        userEntityinsert2.uid = 10000 + new Random().nextInt(90000);
        userEntityinsert2.phonenumber = "9953367888";
        userEntityinsert2.userType = 1;
        userEntityinsert2.walletAmount = "0";

        UserEntity[] userEntities = new UserEntity[]{userEntityinsert,userEntityinsert1,userEntityinsert2};

        UserDao userDao = kirshDB.userDao();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                userDao.insert(userEntities);
            }
        });
    }
}