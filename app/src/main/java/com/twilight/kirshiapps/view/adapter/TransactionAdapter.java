package com.twilight.kirshiapps.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.db.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<TransactionEntity> dataList;

    public TransactionAdapter(List<TransactionEntity> list) {
        this.dataList = list;
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_transaction, parent, false);
        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionEntity data = dataList.get(position);
        holder.tvTranscationNumber.setText(data.transactionID);
        holder.tvAmount.setText(data.amount);
        holder.rv_transaction_phone_number.setText(data.sentNumber);
        if (data.creditType == 0) {
            holder.tvTranscationType.setText(holder.tvTranscationType.getContext().getString(R.string.debit));
            holder.tvTranscationType.setBackgroundResource(R.color.red);
        } else {
            holder.tvTranscationType.setText(holder.tvTranscationType.getContext().getString(R.string.credit));
            holder.tvTranscationType.setBackgroundResource(R.color.green);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvTranscationNumber, tvAmount, tvTranscationType, rv_transaction_phone_number;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTranscationNumber = itemView.findViewById(R.id.rv_transaction_number);
            tvAmount = itemView.findViewById(R.id.rv_transaction_amount);
            tvTranscationType = itemView.findViewById(R.id.tv_transaction_type);
            rv_transaction_phone_number = itemView.findViewById(R.id.rv_transaction_phone_number);
        }
    }
}
