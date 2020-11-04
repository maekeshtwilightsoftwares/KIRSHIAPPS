package com.twilight.kirshiapps.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.twilight.kirshiapps.db.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM userentity")
    List<UserEntity> getAll();

    @Query("SELECT * FROM userentity WHERE uid IN (:userIds)")
    List<UserEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM userentity WHERE phone_number LIKE :phoneNumber LIMIT 1")
    UserEntity findByPhoneNumber(String phoneNumber);

    @Insert
    void insertAll(UserEntity... users);

    @Delete
    void delete(UserEntity user);
}
