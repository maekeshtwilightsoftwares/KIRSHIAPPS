package com.twilight.kirshiapps.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.db.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface TransactionDao {

    @Query("SELECT * FROM transactionentity")
    Single<List<TransactionEntity>> getAll();

    @Query("SELECT * FROM transactionentity WHERE trID IN (:trID)")
    List<TransactionEntity> loadAllByIds(String trID);

    @Query("SELECT * FROM transactionentity WHERE sent_number = :phoneNumber")
    Single<List<TransactionEntity>> findByPhoneNumber(String phoneNumber);

    @Insert
    Completable insertAll(TransactionEntity... users);

    @Insert
    void insert(TransactionEntity... users);

    @Delete
    void delete(TransactionEntity user);
}
