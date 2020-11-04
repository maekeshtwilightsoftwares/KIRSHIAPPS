package com.twilight.kirshiapps.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.twilight.kirshiapps.db.dao.TransactionDao;
import com.twilight.kirshiapps.db.dao.UserDao;
import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.db.entity.UserEntity;

@Database(entities = {UserEntity.class, TransactionEntity.class}, version = 1)
public abstract class KirshDB extends RoomDatabase {

    private static volatile KirshDB INSTANCE;

    public abstract UserDao userDao();

    public abstract TransactionDao transactionDao();

    public static KirshDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (KirshDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            KirshDB.class, "KirshDatabase.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
