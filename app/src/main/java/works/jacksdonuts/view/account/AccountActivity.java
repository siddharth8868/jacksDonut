package works.jacksdonuts.view.account;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseUser;

import works.jacksdonuts.R;
import works.jacksdonuts.core.NavigationDrawerActivity;

public class AccountActivity extends NavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // move user to account summery screen
            AccountSummeryFragment fragment = new AccountSummeryFragment();
            setFragment(fragment);
        } else {
            // show the signup or login screen
            AccountFragment fragment = new AccountFragment();
            setFragment(fragment);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle(getString(R.string.account));
    }
}
