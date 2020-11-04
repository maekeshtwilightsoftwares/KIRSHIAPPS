package com.twilight.kirshiapps.view.fragment.register;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentRegisterBinding;
import com.twilight.kirshiapps.utils.Validation;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding fragmentRegisterBinding;
    private Validation validate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataObserver();
    }

    private void dataObserver() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setActionListener();
    }

    private void setActionListener() {
        fragmentRegisterBinding.btnRegister.setOnClickListener(v -> {
            if (checkValidation(fragmentRegisterBinding.etPhoneNumber.getText().toString())) {

            } else {
                Toast.makeText(requireContext(), getString(R.string.enter_valid_phone), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        validate = new Validation();
    }

    private Boolean checkValidation(String phoneNumber) {
        return validate.isNotEmpty(phoneNumber) || validate.isValidNumber(phoneNumber);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRegisteredText();
    }

    private void setRegisteredText() {
        Spannable alreadyHadAccount = new SpannableString(getString(R.string.had_account));
        alreadyHadAccount.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.light_grey_100)), 0, alreadyHadAccount.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentRegisterBinding.tvAlreadyAccount.setText(alreadyHadAccount);

        Spannable loginText = new SpannableString(getString(R.string.login));
        loginText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.green)), 0, loginText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentRegisterBinding.tvAlreadyAccount.append(" ");
        fragmentRegisterBinding.tvAlreadyAccount.append(loginText);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRegisterBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        return fragmentRegisterBinding.getRoot();
    }
}
