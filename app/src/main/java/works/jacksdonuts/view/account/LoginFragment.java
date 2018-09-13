package works.jacksdonuts.view.account;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import works.jacksdonuts.R;
import works.jacksdonuts.presenter.account.LoginPresenter;
import works.jacksdonuts.presenter.account.LoginPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginView, TextWatcher {

    private AccountActivity activity;
    private LoginPresenter presenter;
    private View view;

    private Button login, signUp;
    private EditText username, password;
    private TextView errorMsg;


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (AccountActivity) getActivity();
        presenter = new LoginPresenterImpl();
        presenter.bind(this);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        login = view.findViewById(R.id.fragment_account_login_page_login_button);
        signUp = view.findViewById(R.id.fragment_account_login_page_sign_up_button);
        username = view.findViewById(R.id.fragment_login_username);
        password = view.findViewById(R.id.fragment_login_password);
        errorMsg = view.findViewById(R.id.fragment_login_error_message_text);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginClicked();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpClicked();
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.initializeWork();
    }

    @Override
    public void showProgressDialog() {
        activity.showProgressDialog();
    }

    @Override
    public void dismissProgressDialog() {
        activity.dismissProgressDialog();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!username.getText().toString().equals("") &&
                !password.getText().toString().equals("")) {
            login.setEnabled(true);
        } else {
            login.setEnabled(false);
        }
    }

    @Override
    public void moveToAccountSummeryPage() {
        AccountSummeryFragment fragment = new AccountSummeryFragment();
        activity.setFragment(fragment);
    }

    @Override
    public void updateErrorMsg(String msg) {
        errorMsg.setText(msg);
        errorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public void moveToSignUpPage() {
        SignUpFragment fragment = new SignUpFragment();
        activity.pushFragment(fragment);
    }

    @Override
    public void hideSoftKeyboard() {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
