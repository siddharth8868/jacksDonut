package works.jacksdonuts.view.account;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
import works.jacksdonuts.presenter.account.SignUpPresenter;
import works.jacksdonuts.presenter.account.SignUpPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment implements SignUpView, TextWatcher {

    private AccountActivity activity;
    private SignUpPresenter presenter;
    private View view;
    private Button signUp;
    private EditText username, password, email;
    private TextView errorMsg;
    boolean showEnableSignUpButton;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (AccountActivity) getActivity();
        presenter = new SignUpPresenterImpl();
        presenter.bind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);

        signUp = view.findViewById(R.id.fragment_sign_up_page_sign_up_button);
        username = view.findViewById(R.id.fragment_sign_up_username);
        password = view.findViewById(R.id.fragment_sign_up_password);
        email = view.findViewById(R.id.fragment_sign_up_email);
        errorMsg = view.findViewById(R.id.fragment_sign_up_error_text);

        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        email.addTextChangedListener(this);

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
    public void enableSignUpButton(boolean enabled) {
    }

    @Override
    public String getUsername() {
        return username.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getEmail() {
        return email.getText().toString();
    }

    @Override
    public void clearFields() {
        username.setText("");
        password.setText("");
        email.setText("");
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
    public void hideSoftKeyboard() {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
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
                !password.getText().toString().equals("") &&
                !email.getText().toString().equals("")) {
            signUp.setEnabled(true);
        } else {
            signUp.setEnabled(false);
        }
    }
}
