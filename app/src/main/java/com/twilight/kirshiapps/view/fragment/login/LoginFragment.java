package com.twilight.kirshiapps.view.fragment.login;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentLoginBinding;
import com.twilight.kirshiapps.databinding.FragmentLoginBindingImpl;
import com.twilight.kirshiapps.utils.Validation;

public class LoginFragment extends Fragment {

    private LoginFragmentViewModel viewModel;
    private FragmentLoginBinding fragmentLoginBinding;
    private Validation validate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataObserver();
    }

    private void dataObserver() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        setActionListener();

    }

    private void setActionListener() {
        fragmentLoginBinding.btnLogin.setOnClickListener(v -> {
            if (checkValidation(fragmentLoginBinding.etPhoneNumber.getText().toString())) {

            } else {
                Toast.makeText(requireContext(), getString(R.string.enter_valid_phone), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Boolean checkValidation(String phoneNumber) {
        return validate.isEmptyOrNot(phoneNumber) || validate.isValidNumber(phoneNumber);
    }

    private void initViews() {
        validate = new Validation();
        setRegisteredText();

    }

    private void setRegisteredText() {
        Spannable registerText = new SpannableString(getString(R.string.register_account));
        registerText.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.light_grey_100)), 0, registerText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentLoginBinding.tvRegisterAccount.setText(registerText);

        Spannable createAccount = new SpannableString(getString(R.string.create_account));
        createAccount.setSpan(new ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.green)), 0, createAccount.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        fragmentLoginBinding.tvRegisterAccount.append(" ");
        fragmentLoginBinding.tvRegisterAccount.append(createAccount);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}