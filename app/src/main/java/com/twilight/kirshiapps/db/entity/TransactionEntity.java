package com.twilight.kirshiapps.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionEntity {

    @PrimaryKey
    public String trabsactID;

    @ColumnInfo(name = "sent_number")
    public String sentNumber;

    @ColumnInfo(name = "rec_number")
    public String recNumber;

    @ColumnInfo(name = "credit_type")
    public int userType;

    @ColumnInfo(name = "amount")
    public String amount;
}
