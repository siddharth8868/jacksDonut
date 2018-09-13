package works.jacksdonuts.view.account;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import works.jacksdonuts.R;
import works.jacksdonuts.presenter.account.AccountPresenter;
import works.jacksdonuts.presenter.account.AccountPresenterImpl;
import works.jacksdonuts.view.template.TemplateActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountView{

    private AccountPresenter presenter;
    private AccountActivity activity;
    private View view;


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (AccountActivity) getActivity();
        presenter = new AccountPresenterImpl();
        presenter.bind(this);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button login = view.findViewById(R.id.fragment_account_login_button);
        Button signUp = view.findViewById(R.id.fragment_account_sign_up_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLoginButtonPressed();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSignUpButtonPressed();
            }
        });

        presenter.initializeWork();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public AccountActivity getSpecificActivity() {
        return activity;
    }
}
