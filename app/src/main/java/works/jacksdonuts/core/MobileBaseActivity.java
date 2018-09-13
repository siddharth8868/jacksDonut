package works.jacksdonuts.core;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import works.jacksdonuts.BuildConfig;
import works.jacksdonuts.R;
import works.jacksdonuts.core.rest.NetworkCallManager;
import works.jacksdonuts.util.AlertDialogUtil;

public class MobileBaseActivity extends AppCompatActivity implements OnClickListener {

    @SuppressWarnings("unused")
    private static final String TAG = "MobileBaseActivity.";

    protected static final int FLAGS_ACTIVITY_NEW = Intent.FLAG_ACTIVITY_NEW_TASK |
            Intent.FLAG_ACTIVITY_CLEAR_TASK |
            Intent.FLAG_ACTIVITY_SINGLE_TOP |
            Intent.FLAG_ACTIVITY_CLEAR_TOP;

    // Account modes/states
    private static final String TRUE = "true";
    public static final String HOME_LOGIN = "home_login";
    public static final String HOME_MODE = "home_mode";

    // encoding and mime-type constants:
    private static final String TEXT_HTML = "text/html";
    private static final String UTF_8 = "UTF-8";

    public static final String decimalFormatPattern = "#,###,###";

    /**
     * Enum used to specify a pre-defined type of Alert Dialog
     */
    public enum AlertDialogType {
        SIMPLE, POP_HOME, POP_LOGIN, FINISH, WALLET_ENROLLMENT
    }

    /**
     * Activity Helper Instance
     */
    protected ActivityHelper activityHelper;

    public NetworkCallManager networkCallManager;


    /**
     * Device Hardware Utility Instance
     */
    protected DeviceHardwareUtility deviceHardwareUtility;

    @Nullable
    private TextView appBarTitleTextView;

    @Nullable
    protected ProgressDialog progressDialog;

    @Nullable
    protected AlertDialog alertDialog;

    /**
     * Bundle for intents
     */
    protected Bundle extras = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.extras = getIntent().getExtras();

        // By default we will lock the screen to the portrait orientation, this can be overriden
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //establish instance of Activity Helper - Use this activityHelper thru out the app
        activityHelper = ActivityHelper.getInstance(getApplicationContext());

        deviceHardwareUtility = DeviceHardwareUtility.getInstance(getApplicationContext());

        networkCallManager = NetworkCallManager.getInstance();

        if (!BuildConfig.ALLOW_SCREENSHOTS) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initializeActionBar();
    }

    private void initializeActionBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            // Capture title text view
            final View view = (View) toolbar.getParent();
            appBarTitleTextView = (TextView) view.findViewById(R.id.text_view_app_bar_title);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Set session expiration listener to this Activity so that session events make callbacks
        // to the current Activity
        //SessionManager.newInstance().setListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Not being use in the app
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return false;
    }

    @Override
    public void onBackPressed() {
        finishWithTransition();
    }

    public void onClick(View view) {

    }

    @Override
    public final void setTitle(@StringRes int titleId) {
        if (appBarTitleTextView != null) {
            appBarTitleTextView.setText(titleId);
        }
    }

    @Override
    public final void setTitle(CharSequence title) {
        if (appBarTitleTextView != null) {
            appBarTitleTextView.setText(title);
        }

    }


    public void finishWithTransition() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void navigateToVirtualCardOrWallet() {
        activityHelper.navigateToVirtualCardOrWallet(this);
    }


    public void navigationToHome() {
        //activityHelper.startActivityForIntent(this, HomeActivity.class, Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        finishAffinity();
    }

    public ActivityHelper getActivityHelper() {
        return activityHelper;
    }


    //
    // Dialog management --------------------------------------------------------------------------
    //

    /**
     * Convenience method for calling {@link #showProgressDialog(String, String)} with default
     * parameters.
     */
    public final void showProgressDialog() {
        showProgressDialog(null, null);
    }

    /**
     * Show Progress Dialog with given title and message.  Null values will display nothing for that
     * parameter.
     */
    public final void showProgressDialog(String title, String message) {
        if (title == null) {
            title = "";
        }
        if (message == null) {
            message = getString(R.string.default_progress_message);
        }
        if (progressDialog == null) {
            progressDialog = AlertDialogUtil.showGenericInstance(this);
        } else {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
    }

    /**
     * Dismiss the Progress Dialog
     */
    public final void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }



    public void generateAlert(String title, String message, boolean cancelable,
                              String buttonText,
                              DialogInterface.OnClickListener buttonOnClickListener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(buttonText, buttonOnClickListener)
                .show();
    }

    @Deprecated
    public void generateAlert(String title, String message, boolean cancelable,
                              String buttonText,
                              DialogInterface.OnClickListener buttonOnClickListener,
                              String button2Text,
                              DialogInterface.OnClickListener button2OnClickListener) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancelable)
                .setPositiveButton(buttonText, buttonOnClickListener)
                .setNegativeButton(button2Text, button2OnClickListener)
                .show();
    }


    /**
     * Dismiss the common Alert Dialog
     */
    public void dismissAlert() {
        if (alertDialog != null) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}
