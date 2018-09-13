package works.jacksdonuts.view.account;

import works.jacksdonuts.core.BaseView;

/**
 * Created by siddharth on 6/24/18.
 */

public interface SignUpView extends BaseView {

    void enableSignUpButton(boolean enabled);

    String getUsername();

    String getPassword();

    String getEmail();

    void clearFields();

    void moveToAccountSummeryPage();

    void updateErrorMsg(String msg);

    void hideSoftKeyboard();
}
