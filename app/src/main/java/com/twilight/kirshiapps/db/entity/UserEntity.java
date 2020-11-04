package com.twilight.kirshiapps.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {

    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "phone_number")
    public String phonenumber;

    @ColumnInfo(name = "user_type")
    public String userType;

    @ColumnInfo(name = "wallet_amount")
    public String walletAmount;
}
