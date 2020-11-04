package com.twilight.kirshiapps.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TransactionEntity {

    @PrimaryKey
    @NonNull
    public String trID;

    @ColumnInfo(name = "sent_number")
    public String sentNumber;

    @ColumnInfo(name = "transaction_id")
    public String transactionID;

    @ColumnInfo(name = "rec_number")
    public String recNumber;

    @ColumnInfo(name = "credit_type")  // Debit = 0, Credit = 1
    public int creditType;

    @ColumnInfo(name = "amount")
    public String amount;
}
