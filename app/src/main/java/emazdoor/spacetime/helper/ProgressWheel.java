package emazdoor.spacetime.helper;

import android.app.Activity;
import android.app.ProgressDialog;


public class ProgressWheel {

    private static ProgressDialog mProgressDialog = null;
    private static int mProgressDialogReferencesCount = 0;

    public static void show(Activity activity,String msg) {

        if (mProgressDialog == null) {
            mProgressDialogReferencesCount = 0;
            mProgressDialog = ProgressDialog.show(activity, "", msg);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);

        }
        mProgressDialogReferencesCount++;
    }

    public static void hide() {
        mProgressDialogReferencesCount--;
        if (mProgressDialogReferencesCount <= 0 && mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
