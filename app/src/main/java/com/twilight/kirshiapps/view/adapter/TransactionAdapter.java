package com.twilight.kirshiapps.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.view.interfaces.TransactionInterface;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private ArrayList<TransactionEntity> dataList;
    private TransactionInterface listeners;

    public TransactionAdapter(ArrayList<TransactionEntity> list, TransactionInterface listener) {
        this.dataList = list;
        this.listeners = listener;
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
        holder.tvTranscationNumber.setText(data.trabsactID);
        holder.tvAmount.setText(data.amount);
        if (data.userType == 0) {
            holder.tvTranscationType.setText(holder.tvTranscationType.getContext().getString(R.string.debit));
            holder.tvTranscationType.setBackgroundResource(R.drawable.ic_debit);
        } else {
            holder.tvTranscationType.setText(holder.tvTranscationType.getContext().getString(R.string.credit));
            holder.tvTranscationType.setBackgroundResource(R.drawable.ic_credit);
        }
        holder.itemViewContainer.setOnClickListener(v->{
            listeners.onItemClick(data);
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tvTranscationNumber, tvAmount, tvTranscationType;
        RelativeLayout itemViewContainer;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTranscationNumber = itemView.findViewById(R.id.rv_transaction_number);
            tvAmount = itemView.findViewById(R.id.rv_transaction_amount);
            tvTranscationType = itemView.findViewById(R.id.tv_transaction_type);
            itemViewContainer = itemView.findViewById(R.id.itemView_container);
        }
    }
}
