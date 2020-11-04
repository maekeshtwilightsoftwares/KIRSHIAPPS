package com.twilight.kirshiapps.view.fragment.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twilight.kirshiapps.R;

public class LoginFragment extends Fragment {

    private LoginFragmentViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LoginFragmentViewModel.class);

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

        fragmentLoginBinding.tvRegisterAccount.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.registerFragment);
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

    private void setCallback(View view){

        EditText et_phone_number = view.findViewById(R.id.et_phone_number);
        EditText loginButton = view.findViewById(R.id.btn_login);

        et_phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                viewModel.setPhoneNumber(et_phone_number.getText().toString());
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validation.isValidNumber(viewModel.getPhoneNumber())){


                }

            }
        });
    }
}