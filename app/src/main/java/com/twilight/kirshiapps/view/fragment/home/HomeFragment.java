package com.twilight.kirshiapps.view.fragment.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.Navigation;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentHomeBinding;
import com.twilight.kirshiapps.db.entity.TransactionEntity;
import com.twilight.kirshiapps.view.fragment.login.LoginFragmentDirections;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private HomeFragmentViewModel viewModel;

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(HomeFragmentViewModel.class);
        dataObserver();
    }

    public void dataObserver(){

        viewModel.live.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                fragmentHomeBinding.tvWalletAmount.setText(s+" INR");
                viewModel.currAmount = s;
                viewModel.getTransList();
            }
        });

        viewModel.transList.observe(requireActivity(), new Observer<List<TransactionEntity>>() {
            @Override
            public void onChanged(List<TransactionEntity> s) {
                viewModel.currAmount = calculateAmount(s);
                fragmentHomeBinding.tvWalletAmount.setText(viewModel.currAmount+" INR");
            }
        });

        viewModel.error.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        initViews();
        setActionListener();
    }

    private void setActionListener() {
        fragmentHomeBinding.includeToolbar.ivAction.setOnClickListener(v-> {
            showDialog();
        });

        fragmentHomeBinding.homeContainer.setOnClickListener(v -> {
            fragmentHomeBinding.includeSendAmount.getRoot().setVisibility(View.GONE);
        });
    }

    private void initViews() {
        fragmentHomeBinding.includeToolbar.title.setText(getString(R.string.home));
        viewModel.setPhoneNumber(HomeFragmentArgs.fromBundle(getArguments()).getPhoneNumber());
        viewModel.getAccount();
        fragmentHomeBinding.tvPhoneNumber.setText(viewModel.getPhoneNumber());
    }

    private void sendMoney(){
        fragmentHomeBinding.includeSendAmount.getRoot().setVisibility(View.VISIBLE);

        fragmentHomeBinding.includeSendAmount.btnSend.setOnClickListener(view -> {
            validateSendMoney(fragmentHomeBinding.includeSendAmount.etAmount.getText().toString(),
                    fragmentHomeBinding.includeSendAmount.etPhoneNumber.getText().toString());

        });

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return fragmentHomeBinding.getRoot();
    }

    private boolean validateSendMoney(String amount, String phoneNumberTo){
        boolean sendamount = false;
        if(Integer.parseInt(amount) < Integer.parseInt(viewModel.currAmount)){
            sendamount = true;
        }

        if(sendamount){

            viewModel.checkNumber(phoneNumberTo, new HomeFragmentViewModel.SetOnPhoneNumberCheck() {
                @Override
                public void OnPhoneNumberCheck(boolean isValid) {
                    if(isValid){
                        viewModel.createTransactionDebit(amount,phoneNumberTo);
                    }else{
                        Toast.makeText(requireContext(), getString(R.string.enter_user_phone), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else {
            Toast.makeText(requireContext(), "Amount entered is not sufficient", Toast.LENGTH_SHORT).show();
        }

        return sendamount;
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_send_amount);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button sendBtn = (Button) dialog.findViewById(R.id.btn_send);
        sendBtn.setOnClickListener(v -> {
            sendMoney();
            dialog.dismiss();
        });

        Button transactionBtn = dialog.findViewById(R.id.btn_transcation);
        transactionBtn.setOnClickListener(v-> {
            dialog.dismiss();
            navigateToTranscationScreen();
        });
        dialog.show();
    }

    private void navigateToTranscationScreen() {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_transactionFragmentList);
    }

    public String calculateAmount(List<TransactionEntity> transListentity){

        int creditAmount = 0;
        int debitAmount = 0;
        for(TransactionEntity transactionEntity : transListentity){

            if(transactionEntity.creditType == 0){
                debitAmount = debitAmount + Integer.parseInt(transactionEntity.amount);
            }else{
                creditAmount = creditAmount + Integer.parseInt(transactionEntity.amount);
            }

        }

        int amount = Integer.parseInt(viewModel.currAmount) + creditAmount - debitAmount;

        return ""+amount;

    }

}
