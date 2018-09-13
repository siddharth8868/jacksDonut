package works.jacksdonuts.view.account;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import works.jacksdonuts.R;
import works.jacksdonuts.presenter.account.AccountSummeryPresenter;
import works.jacksdonuts.presenter.account.AccountSummeryPresenterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSummeryFragment extends Fragment implements AccountSummeryView{

    private AccountActivity activity;
    private AccountSummeryPresenter presenter;
    private View view;
    private TextView welcomeMessage;
    private Button logout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (AccountActivity) getActivity();
        presenter = new AccountSummeryPresenterImpl();
        presenter.bind(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account_summery, container, false);

        welcomeMessage = view.findViewById(R.id.fragment_account_summery_welcome_msg);
        logout = view.findViewById(R.id.fragment_account_summery_logout_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogoutClicked();
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
        activity.showProgressDialog();
    }

    @Override
    public void updateWelcomeMsg(String username) {
        String welcomeMsg = activity.getString(R.string.fragment_account_summery_welcome_msg);
        welcomeMsg = welcomeMsg.replace("[1]", username);
        welcomeMessage.setText(welcomeMsg);
    }

    @Override
    public void moveToAccountFragment() {
        AccountFragment fragment = new AccountFragment();
        activity.setFragment(fragment);
    }
}
