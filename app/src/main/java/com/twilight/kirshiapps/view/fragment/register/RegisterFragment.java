package com.twilight.kirshiapps.view.fragment.register;

import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.twilight.kirshiapps.R;
import com.twilight.kirshiapps.databinding.FragmentRegisterBinding;
import com.twilight.kirshiapps.utils.Validation;
import com.twilight.kirshiapps.view.fragment.login.LoginFragmentDirections;

import static com.twilight.kirshiapps.BaseActivity.sharedPreference;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding fragmentRegisterBinding;
    private RegisterFragmentViewModel viewModel;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void dataObserver() {

        viewModel.live.observe(requireActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
                sharedPreference.storePhoneNumber(fragmentRegisterBinding.etPhoneNumber.getText().toString());
                RegisterFragmentDirections.ActionRegisterFragmentToHomeFragment action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment(viewModel.getPhoneNumber());
                Navigation.findNavController(view).navigate(action);

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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        setActionListener();

        viewModel = new ViewModelProvider(getActivity()).get(RegisterFragmentViewModel.class);
        dataObserver();
    }

    private void setActionListener() {
        fragmentRegisterBinding.btnRegister.setOnClickListener(v -> {
            if (checkValidation(viewModel.getPhoneNumber())) {
                viewModel.registerAccount();
            } else {
                Toast.makeText(requireContext(), getString(R.string.enter_valid_phone), Toast.LENGTH_SHORT).show();
            }
        });

        fragmentRegisterBinding.etPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                viewModel.setPhoneNumber(fragmentRegisterBinding.etPhoneNumber.getText().toString());
            }
        });

        fragmentRegisterBinding.tvAlreadyAccount.setOnClickListener(v ->{
            Navigation.findNavController(v).navigate(R.id.action_registerFragment_pop);
        });

        fragmentRegisterBinding.ivBack.setOnClickListener(v->{
            requireActivity().onBackPressed();
        });
    }


    private void initViews() {

    }

    private Boolean checkValidation(String phoneNumber) {
        return Validation.isNotEmpty(phoneNumber) || Validation.isValidNumber(phoneNumber);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
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
