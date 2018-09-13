package works.jacksdonuts.view.account;

import works.jacksdonuts.core.BaseView;

/**
 * Created by siddharth on 6/24/18.
 */

public interface LoginView extends BaseView {
    void moveToAccountSummeryPage();

    void updateErrorMsg(String msg);

    String getPassword();

    String getUsername();

    void moveToSignUpPage();

    void hideSoftKeyboard();
}
