package emazdoor.spacetime.Auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import emazdoor.spacetime.Constants;
import emazdoor.spacetime.R;
import emazdoor.spacetime.Util;
import emazdoor.spacetime.dashboard.DashboardActivity;
import emazdoor.spacetime.helper.ActivityUtils;
import emazdoor.spacetime.helper.ProgressWheel;
import emazdoor.spacetime.network.SpaceTravelRestClient;

public class LoginActivity extends AppCompatActivity implements
        SpaceTravelRestClient.User,
        SignUpFragment.OnFragmentInteractionListener,
        View.OnClickListener,Animation.AnimationListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText email, password;
    private Button loginBtn, signup;
    private AppCompatTextView logoText;
    private ImageView logoImage;
    private LinearLayout inputFieldsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(R.layout.activity_main);
        init();
        Animation animateLogo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_logo_up);

        animateLogo.setStartOffset(450);
        animateLogo.setFillAfter(true);
        animateLogo.setAnimationListener(this);
        logoImage.startAnimation(animateLogo);

    }

    public void init() {
        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signup = (Button) findViewById(R.id.signupBtn);
        logoImage = (ImageView) findViewById(R.id.spaceTimeLogo);
        logoText = (AppCompatTextView) findViewById(R.id.logoText);
        inputFieldsContainer = (LinearLayout) findViewById(R.id.input_fields);

        inputFieldsContainer.setVisibility(View.GONE);
        loginBtn.setVisibility(View.GONE);
        signup.setVisibility(View.GONE);
        logoText.setVisibility(View.GONE);
        setListeners();
        setFonts();
    }

    private void setListeners(){
        signup.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    private void setFonts(){
        logoText.setTypeface(Util.getFont(this, Constants.FONT_MUSEO_700));
        loginBtn.setTypeface(Util.getFont(this, Constants.FONT_MUSEO_700));
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        inputFieldsContainer.setAlpha(0f);
        inputFieldsContainer.setVisibility(View.VISIBLE);
        loginBtn.setAlpha(0f);
        loginBtn.setVisibility(View.VISIBLE);
        signup.setAlpha(0f);
        signup.setVisibility(View.VISIBLE);
        logoText.setAlpha(0f);
        logoText.setVisibility(View.VISIBLE);
        int mediumAnimateTime = getResources().getInteger(android.R.integer.config_mediumAnimTime);

        inputFieldsContainer.animate().alpha(1f).setDuration(mediumAnimateTime);
        logoText.animate().alpha(1f).setDuration(mediumAnimateTime);
        loginBtn.animate().alpha(1f).setDuration(mediumAnimateTime);
        signup.animate().alpha(1f).setDuration(mediumAnimateTime);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                try {
                    login();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.signupBtn:
                signup();
                break;
        }
    }

    private void signup() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), SignUpFragment.newInstance(), R.id.contentPanel, SignUpFragment.TAG, R.anim.slidein, R.anim.slideout);
    }

    private void login() throws JSONException {
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        if(!Util.isValidEmail(email)){
            Toast.makeText(this, "Please provide valid email", Toast.LENGTH_SHORT).show();
            this.email.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            Toast.makeText(this, "Please provide valid password", Toast.LENGTH_SHORT).show();
            this.password.requestFocus();
            return;
        }
        RequestParams params = new RequestParams();
        params.put("authkey","1a0f423312e24ef9246afed7d2a18a06");
        params.put("action","login");
        params.put("email",email);
        params.put("password",password);

        ProgressWheel.show(this,"Logging in...");
        LoginRequest.loginUser(params,this);


    }


    private void navigateToDashboard() {
        Intent i = new Intent();
        i.setClass(this, DashboardActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void loggedInSuccessfully() {
        ProgressWheel.hide();
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        navigateToDashboard();
    }

    @Override
    public void failedToLogin(String msg) {
        ProgressWheel.hide();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFragmentInteraction(RequestParams params) {
        ProgressWheel.show(this,"Creating your account...");
        LoginRequest.loginUser(params,this);
    }
}
