package com.twilight.kirshiapps.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.twilight.kirshiapps.db.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface UserDao {

    @Query("SELECT * FROM userentity")
    Single<List<UserEntity>> getAll();

    @Query("SELECT * FROM userentity WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM userentity WHERE phone_number = :phoneNumber")
    Single<UserEntity> findByPhoneNumber(String phoneNumber);

    @Insert
    Completable insertAll(UserEntity users);

    @Insert
    void insert(UserEntity users);

    @Delete
    void delete(UserEntity user);
}
