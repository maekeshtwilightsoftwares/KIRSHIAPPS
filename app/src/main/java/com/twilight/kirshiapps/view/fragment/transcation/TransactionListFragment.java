package com.twilight.kirshiapps.view.fragment.transcation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentTransactionListBinding;
import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.view.adapter.TransactionAdapter;
import com.twilight.kirshiapps.view.interfaces.TransactionInterface;

public class TransactionListFragment extends Fragment implements TransactionInterface {

    private FragmentTransactionListBinding fragmentTransactionListBinding;
    private TransactionAdapter adapter;
    private TranscationListFragmentViewModel viewModel;
    private View view;
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
        this.view = view;
        viewModel = new ViewModelProvider(requireActivity()).get(TranscationListFragmentViewModel.class);
        initViews();
        setActionListener();
    }

    private void setActionListener() {
    }

    private void initViews() {
        fragmentTransactionListBinding.includeToolbar.ivAction.setVisibility(View.GONE);
        fragmentTransactionListBinding.includeToolbar.title.setText(getString(R.string.transcation));
        createTranscationList();
    }

    private void createTranscationList() {
        adapter = new TransactionAdapter(viewModel.getTranscationList(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        fragmentTransactionListBinding.rvTransaction.setLayoutManager(mLayoutManager);
        fragmentTransactionListBinding.rvTransaction.setItemAnimator(new DefaultItemAnimator());
        fragmentTransactionListBinding.rvTransaction.setAdapter(adapter);
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

    @Override
    public void onItemClick(TransactionEntity data) {
        Navigation.findNavController(view).navigate(R.id.action_transactionFragmentList_to_transactionDetailFragment);
    }
}
