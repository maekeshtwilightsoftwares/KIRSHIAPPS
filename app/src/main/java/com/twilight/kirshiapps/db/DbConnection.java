package com.twilight.kirshiapps.db;

import android.content.Context;

import androidx.room.Room;

public class DbConnection {

    static DbConnection dbConnection;
    private KirshDB kirshDB;

    private DbConnection(){
    }

    static public DbConnection getInstance(){
        if(dbConnection != null)
            return dbConnection;
        else
            return new DbConnection();
    }

    public void setupDB(Context context){
        kirshDB = Room.databaseBuilder(context,KirshDB.class, "kirishDB").build();
    }

    public KirshDB getKirshDB() {
        return kirshDB;
    }
}
