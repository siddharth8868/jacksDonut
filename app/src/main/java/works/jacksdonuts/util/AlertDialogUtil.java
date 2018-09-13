package works.jacksdonuts.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import works.jacksdonuts.R;

/**
 * Created by siddharth on 6/19/18.
 */

public class AlertDialogUtil {

    private AlertDialogUtil() {}

    @NonNull
    public static ProgressDialog showGenericInstance(@NonNull Context context) {
        final String message = context.getString(R.string.default_progress_message);
        return ProgressDialog.show(context, "", message, true, false);
    }

    public static AlertDialog getAlertDialog(@NonNull Context context, int msg, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(context.getString(msg));

        return builder.create();
    }

    public static AlertDialog getAlertDialog(@NonNull Context context, int msg, String title, final Delegate delegate) {
        final AlertDialog alertDialog = getAlertDialog(context, msg, title);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                context.getText(R.string.button_label_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                        delegate.onPositiveButtonClicked();
                    }
                });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,
                context.getText(R.string.button_label_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                        delegate.onNegativeButtonClicked();
                    }
                });

        return alertDialog;
    }

    public interface Delegate {

        void onPositiveButtonClicked();

        void onNegativeButtonClicked();
    }
}
