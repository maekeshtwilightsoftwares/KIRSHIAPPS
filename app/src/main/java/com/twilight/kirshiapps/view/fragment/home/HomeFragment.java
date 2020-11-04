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

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
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
        String phoneNumber = HomeFragmentArgs.fromBundle(getArguments()).getPhoneNumber();
        fragmentHomeBinding.tvPhoneNumber.setText(phoneNumber);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return fragmentHomeBinding.getRoot();
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
            fragmentHomeBinding.includeSendAmount.getRoot().setVisibility(View.VISIBLE);
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

}
