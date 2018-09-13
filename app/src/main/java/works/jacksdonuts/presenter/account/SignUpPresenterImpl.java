package works.jacksdonuts.presenter.account;

import android.support.annotation.NonNull;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import works.jacksdonuts.R;
import works.jacksdonuts.core.BaseView;
import works.jacksdonuts.view.account.SignUpView;

/**
 * Created by siddharth on 6/24/18.
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private SignUpView view;

    @Override
    public void bind(@NonNull BaseView view) {
        this.view = (SignUpView) view;
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
        view.enableSignUpButton(false);

    }

    @Override
    public void onSignUpClicked() {
        if (view == null) {
            return;
        }

        ParseUser user = new ParseUser();
        user.setUsername(view.getUsername());
        user.setPassword(view.getPassword());
        user.setEmail(view.getEmail());

        // other fields can be set just like with ParseObject
        //user.put("phone", "650-253-0000");
        view.hideSoftKeyboard();
        view.showProgressDialog();
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                view.dismissProgressDialog();
                if (e == null) {
                    //SignUp successful
                    view.moveToAccountSummeryPage();
                } else {
                    switch (e.getCode()) {
                        case ParseException.ACCOUNT_ALREADY_LINKED:
                        case ParseException.USERNAME_TAKEN:
                            view.updateErrorMsg(view.getContext().getString(R.string.error_message_username_taken));
                            break;
                        case ParseException.EMAIL_TAKEN:
                            view.updateErrorMsg(view.getContext().getString(R.string.error_message_email_taken));
                            break;
                        default:
                            view.updateErrorMsg(view.getContext().getString(R.string.error_message_default_msg));
                    }
                }
            }
        });
    }
}
