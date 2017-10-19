package emazdoor.spacetime;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;

/**
 * Created by sidhu on 10/18/2017.
 */

public class Util {
    public static Typeface getFont(Context context,String name){
        AssetManager am = context.getApplicationContext().getAssets();

        return Typeface.createFromAsset(am,name);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
