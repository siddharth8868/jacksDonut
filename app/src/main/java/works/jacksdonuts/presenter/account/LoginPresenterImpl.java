package works.jacksdonuts.presenter.account;

import android.support.annotation.NonNull;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import works.jacksdonuts.R;
import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.view.account.LoginView;

/**
 * Created by siddharth on 6/24/18.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView view;
    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (LoginView) view;
    }

    @Override
    public void unBind() {
        this.view = null;
    }

    @Override
    public void initializeWork() {

    }

    @Override
    public void onLoginClicked() {
        if(view == null) {
            return;
        }
        view.hideSoftKeyboard();
        view.showProgressDialog();
        ParseUser.logInInBackground(view.getUsername(), view.getPassword(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                view.dismissProgressDialog();
                if (user != null) {
                    // Hooray! The user is logged in.
                    view.moveToAccountSummeryPage();

                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    switch (e.getCode()) {
                        case ParseException.ACCOUNT_ALREADY_LINKED:
                        case ParseException.USERNAME_TAKEN:
                            view.updateErrorMsg(view.getContext().getString(R.string.error_message_username_taken));
                            break;
                        default:
                            view.updateErrorMsg(view.getContext().getString(R.string.error_message_default_msg));
                    }
                }
            }
        });
    }

    @Override
    public void onSignUpClicked() {
        if (view == null) {
            return;
        }
        view.hideSoftKeyboard();
        view.moveToSignUpPage();
    }
}
