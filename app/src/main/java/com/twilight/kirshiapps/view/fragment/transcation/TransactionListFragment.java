package com.twilight.kirshiapps.view.fragment.transcation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentTransactionListBinding;
import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.view.adapter.TransactionAdapter;
import com.twilight.kirshiapps.view.fragment.login.LoginFragmentViewModel;

import java.util.List;

public class TransactionListFragment extends Fragment {

    private FragmentTransactionListBinding fragmentTransactionListBinding;
    private TransactionAdapter adapter;
    private TranscationListFragmentViewModel viewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataObserver();
    }

    private void dataObserver() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(TranscationListFragmentViewModel.class);
        initViews();
        setActionListener();
        viewModel.getTransList();
    }

    private void setActionListener() {
    }

    private void initViews() {
        fragmentTransactionListBinding.includeToolbar.ivAction.setVisibility(View.GONE);
        fragmentTransactionListBinding.includeToolbar.title.setText(getString(R.string.transcation));
        createTranscationList();
    }

    private void createTranscationList() {

        viewModel.transList.observe(requireActivity(), new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> s) {

                adapter = new TransactionAdapter(s);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                fragmentTransactionListBinding.rvTransaction.setLayoutManager(mLayoutManager);
                fragmentTransactionListBinding.rvTransaction.setItemAnimator(new DefaultItemAnimator());
                fragmentTransactionListBinding.rvTransaction.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentTransactionListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_list, container, false);
        return fragmentTransactionListBinding.getRoot();
    }
}
