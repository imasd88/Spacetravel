package emazdoor.spacetime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import emazdoor.spacetime.Auth.LoginActivity;

/**
 * Created by sidhu on 10/18/2017.
 */

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        (( AppCompatTextView)findViewById(R.id.logoText)).setTypeface(Util.getFont(this, Constants.FONT_MUSEO_700));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, LoginActivity.class));
            }
        },3000);
    }

}
