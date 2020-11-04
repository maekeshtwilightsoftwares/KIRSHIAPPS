package com.twilight.kirshiapps.view.fragment.transcation;

import androidx.lifecycle.ViewModel;

import com.twilight.kirshiapps.db.entity.TransactionEntity;

import java.util.ArrayList;

public class TranscationListFragmentViewModel extends ViewModel {

    private ArrayList<TransactionEntity> transactionList;

    public ArrayList<TransactionEntity> getTranscationList() {
        transactionList = new ArrayList<>();
        TransactionEntity firstEntity = new TransactionEntity();
        firstEntity.amount = "5000 INR";
        firstEntity.trabsactID = "1478523690";
        firstEntity.userType = 0;

        TransactionEntity secondEntity = new TransactionEntity();
        secondEntity.amount = "1000 INR";
        secondEntity.trabsactID = "0132547896";
        secondEntity.userType = 1;

        TransactionEntity thirdEntity = new TransactionEntity();
        thirdEntity.amount = "10000 INR";
        thirdEntity.trabsactID = "9874563210";
        thirdEntity.userType = 1;

        TransactionEntity fourthEntity = new TransactionEntity();
        fourthEntity.amount = "100 INR";
        fourthEntity.trabsactID = "4563210789";
        fourthEntity.userType = 0;

        TransactionEntity fivethEntity = new TransactionEntity();
        fivethEntity.amount = "1000 INR";
        fivethEntity.trabsactID = "4758963210";
        fivethEntity.userType = 1;

        transactionList.add(firstEntity);
        transactionList.add(secondEntity);
        transactionList.add(thirdEntity);
        transactionList.add(fourthEntity);
        transactionList.add(fivethEntity);

        return transactionList;

    }
}
