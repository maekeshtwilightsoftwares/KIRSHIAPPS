package com.twilight.kirshiapps.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.twilight.kirshiapps.db.entity.UserEntity;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class KirshDB extends RoomDatabase {

    public abstract UserEntity userEntity();
}
