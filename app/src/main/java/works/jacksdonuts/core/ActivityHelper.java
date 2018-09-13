package works.jacksdonuts.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import works.jacksdonuts.BuildConfig;
import works.jacksdonuts.R;

/**
 * Created by siddharth on 6/19/18.
 */
public class ActivityHelper {
    private static final String UTF_8 = "UTF-8";

    /**
     * Singleton instance
     */
    protected static ActivityHelper instance = new ActivityHelper();

    ///public static constants
    public static int SMALL_WIDTH_SCREEN = 320;
    public static int MEDIUM_WIDTH_SCREEN = 480;
    public static int LARGE_WIDTH_SCREEN = 720;
    public static int XLARGE_WIDTH_SCREEN = 1080;
    public static int SMALL_HEIGHT_SCREEN = 480;
    public static int MEDIUM_HEIGHT_SCREEN = 800;
    public static int LARGE_HEIGHT_SCREEN = 1280;
    public static int XLARGE_HEIGHT_SCREEN = 1920;

    /**
     * Context
     */
    private Context context = null;

    /**
     * Constructor
     */
    private ActivityHelper() {
    }

    public static ActivityHelper getInstance(Context context) {
        instance.context = context;
        return instance;
    }

    public static boolean jellyBeanAndNewerSDK(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            return true;
        }
        return false;
    }

    public static boolean kitkatAndNewerSDK(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT)
        {
            return true;
        }
        return false;
    }

    /**
     * Read the content of a raw resource file into the provided StringBuilder
     *
     * @param stringBuilder
     * @param resourceId
     */
    public void getRawResourceFileContent(StringBuilder stringBuilder, int resourceId) {
        InputStream inputStream = context.getResources().openRawResource(resourceId);
        InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName(UTF_8));
        int ch = -1;
        try {
            do
            {
                ch = reader.read();
                if (ch > 0) stringBuilder.append((char)ch);
            } while (ch > 0);
            inputStream.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Append CSS styles to the StringBuilder with content from a raw resource file with the given id.  This is used to
     * inject CSS into a body of HTML content to be fed into Web View.
     *
     * @param stringBuilder
     * @param resourceId
     */
    public void appendStylesFromResourceFile(StringBuilder stringBuilder, int resourceId) {
        if (stringBuilder != null) {
            stringBuilder.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            stringBuilder.append("<style>");
            getRawResourceFileContent(stringBuilder, resourceId);
            stringBuilder.append("</style>");

        }
    }


    /**
     * Returns the base URL to the mobile platform service.  The URL to the service is stored as
     * in the String resources, but this method injects the test URL if the test mode is enabled,
     * so this method should be used in general to get the service URL in all Activities.
     *
     * @return base URL to the service
     */
    public String getBaseServiceUrl() {
        //return MobileApplication.newInstance().getNetwork().getBaseURL();
        return "";
    }

    /**
     * Parses the provided String (in the format of ##.##.##) into a Double, stripping off extra values after the second decimal point
     * and evaluating it as a ##.## double value.  Note that the value after the decimal is always interpreted as hundredths such that
     * 1.8 is converted to 1.08 so that minor versions can exceed 10.  For example, version 1.1 is numerically equivalent to 1.01 and
     * is less than version 1.10 (numerically 1.10).  So, versions in ascending sequence would look like:  1.0, 1.1, 1.2, ..., 1.9, 1.10, 1.11... 1.99
     *
     * @param string
     * @return Double value
     */
    public Double parseDoubleValueForComparison(final String string)
    {
        final StringTokenizer tokenizer = new StringTokenizer(string,".");
        if (tokenizer.countTokens() > 2)
        {
            return parseDoubleValueForComparison(string.substring(0,string.lastIndexOf(".")));
        }
        else if (tokenizer.countTokens() == 1) {
            return Double.parseDouble(string);
        }
        StringTokenizer tokenizer2 = new StringTokenizer(string,"-");
        if (tokenizer2.countTokens() > 1)
        {
            return parseDoubleValueForComparison(string.substring(0,string.indexOf("-")));
        }
        try
        {
            if (string != null && string.length() <= 5) {
                String firstPart = string.substring(0,string.indexOf("."));
                if (firstPart.length() > 2) {
                    return null;
                }
                String secondPart = string.substring(string.indexOf(".")+1);
                if (secondPart.length() == 1) {
                    secondPart = "0"+secondPart;
                } else if (secondPart.length() > 2) {
                    return null;
                }
                return Double.parseDouble(firstPart+"."+secondPart);
            } else {
                return null;
            }
        }
        catch (NumberFormatException e)
        {
            if (string != null && string.length() > 1 && string.length() <= 3)
                return parseDoubleValueForComparison(string.substring(0,string.length()-1));
            else
                return null;
        }
    }

    public Double parseDoubleValueForComparison(final Double doubleValue) {
        return parseDoubleValueForComparison(doubleValue.toString());
    }

    /**
     * Start an internal intent to another activity with FLAG_ACTIVITY_PREVIOUS_IS_TOP defaulted and
     * activityForResults defaulted to false and requestCode for activityForResults defaulted to 0
     *
     */
    public void startActivityForIntent(Activity currentActivity, Class<?> intentedClass) {
        startActivityForIntent(currentActivity,
                intentedClass,
                Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP,
                false,
                0,
                null);
    }

    public void startActivityForIntent(Activity currentActivity, Class<?> intentedClass, Bundle extras) {
        startActivityForIntent(currentActivity, intentedClass, Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP, false, 0, extras);
    }

    public void startActivityForIntentNoHistory(Activity currentActivity, Class<?> intentedClass) {
        startActivityForIntent(currentActivity,
                intentedClass,
                Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY,
                false,
                0,
                null);
    }

    public void startActivityForIntentNoHistory(Activity currentActivity, Class<?> intentedClass, Bundle extras) {
        startActivityForIntent(currentActivity, intentedClass, Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY, false, 0, extras);
    }

    /**
     * Start an internal intent to another activity with FLAG_ACTIVITY_PREVIOUS_IS_TOP defaulted
     *
     * @param activityForResults
     * @param requestCode
     */
    public void startActivityForIntent(Activity currentActivity, Class<?> intentedClass, boolean activityForResults, int requestCode) {
        startActivityForIntent(currentActivity,
                intentedClass,
                Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP,
                activityForResults,
                requestCode,
                null);
    }

    public void startActivityForIntent(Activity currentActivity, Class<?> intentedClass, boolean activityForResults, int requestCode, Bundle extras) {
        startActivityForIntent(currentActivity,
                intentedClass,
                Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP,
                activityForResults,
                requestCode,
                extras);
    }

    /**
     * Start an internal intent to another activity with activityForResult defaulted to false and
     * request Code defaulted to 0
     *
     * @param intentFlags
     */
    public void startActivityForIntent(Activity currentActivity, Class<?> intentedClass, int intentFlags) {
        startActivityForIntent(currentActivity, intentedClass, intentFlags, false, 0, null);
    }

    /**
     * Start an internal intent to another activity
     */
    public void startActivityForIntent(Activity currentActivity, Class<?> intentedClass, int intentFlags, boolean activityForResults, int requestCode, Bundle extras) {
        Intent intent = null;
        intent = new Intent(currentActivity, intentedClass);
        intent.setFlags(intentFlags);
        if (extras != null) {
            intent.putExtras(extras);
        }
        if (activityForResults) {
            currentActivity.startActivityForResult(intent, requestCode);
        } else {
            currentActivity.startActivity(intent);
        }
        currentActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


       /* if (intentedClass == EULA.class || intentedClass == TestSettingsActivity.class) {
            currentActivity.overridePendingTransition(0,0);
        } else {
            currentActivity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        }*/
    }

    /**
     * Determine if network is available
     *
     * @return boolean indicating the network is available
     */
    public boolean isNetworkAvailable(Context context) {
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debugOffline")) {
            return true;
        }

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiInfo.isConnected()) {
                return true;
            }
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileInfo.isConnected()) {
                return true;
            }
        } catch (Exception e) {
            Log.e("ActivityHelper", e.getClass().getName() + ":" + e.getMessage());
        }
        return false;
    }

    /**
     * Returns a description of the network connection type
     *
     * @param context
     * @return String description
     */
    public String getNetworkConnectionType(Context context) {
        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debugOffline")) {
            return "DEBUG_OFFLINE";
        } else if (!isNetworkAvailable(context)) {
            return "OFFLINE";
        } else {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiInfo.isConnected()) {
                return "WIFI";
            } else if (mobileInfo.isConnected()) {
                return "MOBILE";
            } else {
                return "";
            }

        }
    }

    public void dismissKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void displayToastForTest(Context theContext, String text, int toastLength) {
        if (BuildConfig.DEBUG) {
            Toast toast = Toast.makeText(theContext, null, toastLength);
            toast.setText(text);
            toast.show();
        }
    }

    // TODO: Refactor. This method duplicates MobileBaseActivity.
    public final boolean walletEnabled() {
        //return context.getResources().getBoolean(R.bool.enable_wallet);
        return true;
    }

    public void navigateToVirtualCardOrWallet(Activity activity) {
       // startActivityForIntent(activity, Wallet.class);
    }


    //
    // User Preferences ---------------------------------------------------------------------------
    //

    /**
     * Get the attribute value from the Shared Preferences with the given
     * key, and use the provided default value if no attribute was found
     *
     * @param key
     * @param defaultValue
     * @return attribute value
     */
    public String getPreferencesAttribute(String key, String defaultValue) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, defaultValue);
    }

    /**
     * Set attribute value in the Shared Preferences
     *
     * @param key
     * @param value
     */
    public void setPreferencesAttribute(String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

}
