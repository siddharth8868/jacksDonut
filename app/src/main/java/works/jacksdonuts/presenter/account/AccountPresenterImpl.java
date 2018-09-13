package works.jacksdonuts.presenter.account;

import android.support.annotation.NonNull;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.view.account.AccountView;
import works.jacksdonuts.view.account.LoginFragment;
import works.jacksdonuts.view.account.SignUpFragment;

/**
 * Created by siddharth on 6/23/18.
 */

public class AccountPresenterImpl implements AccountPresenter {

    AccountView view;

    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (AccountView) view;
    }

    @Override
    public void unBind() {
        this.view = null;
    }

    @Override
    public void initializeWork() {

    }

    @Override
    public void onLoginButtonPressed() {
        if (view == null) {
            return;
        }
        LoginFragment fragment = new LoginFragment();
        view.getSpecificActivity().pushFragment(fragment);
    }

    @Override
    public void onSignUpButtonPressed() {
        if (view == null) {
            return;
        }
        SignUpFragment fragment = new SignUpFragment();
        view.getSpecificActivity().pushFragment(fragment);
    }
}
