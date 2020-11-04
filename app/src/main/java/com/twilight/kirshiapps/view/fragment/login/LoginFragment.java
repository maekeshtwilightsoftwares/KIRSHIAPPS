package com.twilight.kirshiapps.view.fragment.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentLoginBinding;
import com.twilight.kirshiapps.db.DbConnection;
import com.twilight.kirshiapps.utils.Validation;

public class LoginFragment extends Fragment {

    private LoginFragmentViewModel viewModel;
    private FragmentLoginBinding fragmentLoginBinding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_login, container, false);

        fragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return fragmentLoginBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(LoginFragmentViewModel.class);

        initViews();
        setActionListener();
    }



    private void setActionListener() {
        fragmentLoginBinding.btnLogin.setOnClickListener(v -> {
            if (checkValidation(viewModel.getPhoneNumber())) {

            } else {
                Toast.makeText(requireContext(), getString(R.string.enter_valid_phone), Toast.LENGTH_SHORT).show();
            }
        });

        fragmentLoginBinding.tvRegisterAccount.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.registerFragment);
        });

        fragmentLoginBinding.etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                viewModel.setPhoneNumber(fragmentLoginBinding.etPhoneNumber.getText().toString());
            }
        });

    }

    private Boolean checkValidation(String phoneNumber) {
        return Validation.isEmptyOrNot(phoneNumber) || Validation.isValidNumber(phoneNumber);
    }

    private void initViews() {
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