package works.jacksdonuts.presenter.account;

import android.support.annotation.NonNull;

import com.parse.ParseUser;

import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.view.account.AccountSummeryView;

/**
 * Created by siddharth on 6/24/18.
 */

public class AccountSummeryPresenterImpl implements AccountSummeryPresenter {

    AccountSummeryView view;
    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (AccountSummeryView) view;
    }

    @Override
    public void unBind() {
        this.view = null;
    }

    @Override
    public void initializeWork() {
        if (view == null) {
            return;
        }
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            view.updateWelcomeMsg(currentUser.getUsername());

        } else {
            // show the signup or login screen
        }
    }

    @Override
    public void onLogoutClicked() {
        if (view == null) {
            return;
        }
        ParseUser.logOut();
        view.moveToAccountFragment();
    }
}
