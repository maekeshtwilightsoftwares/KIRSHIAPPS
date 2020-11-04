package com.twilight.kirshiapps.view.fragment.transcation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentTranscationDetailBinding;

public class TransactionDetailFragment extends Fragment {

    private FragmentTranscationDetailBinding fragmentTranscationDetailBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataObserver();
    }

    private void dataObserver() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setActionListerner();
    }

    private void setActionListerner() {
    }

    private void initViews() {
        fragmentTranscationDetailBinding.includeToolbar.ivAction.setVisibility(View.GONE);
        fragmentTranscationDetailBinding.includeToolbar.title.setText(getString(R.string.transaction_details));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentTranscationDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_transcation_detail, container, false);
        return fragmentTranscationDetailBinding.getRoot();
    }
}
