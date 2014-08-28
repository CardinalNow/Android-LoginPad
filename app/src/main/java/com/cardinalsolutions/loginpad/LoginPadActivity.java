package com.cardinalsolutions.loginpad;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cardinalsolutions.android.arch.autowire.AndroidLayout;
import com.cardinalsolutions.android.arch.autowire.AndroidView;

import cardinalsolutions.com.loginpad.R;

@AndroidLayout(cardinalsolutions.com.loginpad.R.layout.activity_login_pad)

public class LoginPadActivity extends BaseActivity implements View.OnClickListener,
        View.OnTouchListener {

	private static final String TAG = "LoginPadActivity";

	@AndroidView(cardinalsolutions.com.loginpad.R.id.activity_login_access_code_value)
    private EditText mUserAccessCode;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.activity_login_access_code_login)
    private TextView mLoginButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.login_button_progress)
    private ProgressBar mLoginProgress;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.one_button)
    private TextView mOneButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.two_button)
    private TextView mTwoButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.three_button)
    private TextView mThreeButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.four_button)
    private TextView mFourButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.five_button)
    private TextView mFiveButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.six_button)
    private TextView mSixButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.seven_button)
    private TextView mSevenButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.eight_button)
    private TextView mEightButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.nine_button)
    private TextView mNineButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.zero_button)
    private TextView mZeroButton;

    @AndroidView(cardinalsolutions.com.loginpad.R.id.activity_login_access_code_delete)
    private TextView mDeleteButton;

    // Animations
    private OvershootInterpolator mAnimationSlideInterpolator = new OvershootInterpolator(1.0f);
    private Animation mAnimSlideIn;
    private Animation mAnimSlideOut;

    private boolean mDeleteIsShowing = false;
    private boolean mFailedLogin = false;

	public static final String USER_PIN = "USER_PIN";
	public static final int USER_PIN_MAX_CHAR = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    // only show the login pad if the  user hasn't logged in
	    if (didLogIn()) {
		    closeActivity();
	    }

        configureViews();
        configureAnimations();
        setEditTextListener();

    }

    private void attemptLogin(String userPinIn) {
        /*
         Details left out for brevity and the numerous was one can attempt a login, but...
         attempt your login here and grab the response...

	    If your login is a success, pass the user pin to save to shared prefs and close your
	    activity

	    saveUserPinToSharedPrefs("success", userPinIn);
	    closeActivity();

	    else run animation and reset the text field...

	    Wrapping the failed response in a handler to pause execution so we can demo the animation.
	    You do not need the handler in real world use!!
	    */

	    Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
		    @Override
		    public void run() {
			    mFailedLogin = true;
			    animateLoginButtonInOut(false);
			    mUserAccessCode.setText("");
		    }
	    }, 3000);

    }

	private void saveUserPinToSharedPrefs(String loginResponse, String userPinIn) {
		Logger.d(TAG, "Login Response: " + loginResponse);
		this.mPreferencesEditor.putString(USER_PIN, userPinIn);
		this.mPreferencesEditor.commit();
	}

	private boolean didLogIn() {
		if (this.mSharedPreferences.getString(USER_PIN, null) != null) {
			return true;
		}
		return false;
	}

    private void closeActivity() {
        //close this activity and launch your "home" activity
    }

    private void configureViews() {
        this.mLoginProgress.setVisibility(View.GONE);
        this.mLoginButton.setOnClickListener(this);
        this.mLoginButton.setVisibility(View.GONE);
        this.mOneButton.setOnClickListener(this);
        this.mOneButton.setOnTouchListener(this);
        this.mTwoButton.setOnClickListener(this);
        this.mTwoButton.setOnTouchListener(this);
        this.mThreeButton.setOnClickListener(this);
        this.mThreeButton.setOnTouchListener(this);
        this.mFourButton.setOnClickListener(this);
        this.mFourButton.setOnTouchListener(this);
        this.mFiveButton.setOnClickListener(this);
        this.mFiveButton.setOnTouchListener(this);
        this.mSixButton.setOnClickListener(this);
        this.mSixButton.setOnTouchListener(this);
        this.mSevenButton.setOnClickListener(this);
        this.mSevenButton.setOnTouchListener(this);
        this.mEightButton.setOnClickListener(this);
        this.mEightButton.setOnTouchListener(this);
        this.mNineButton.setOnClickListener(this);
        this.mNineButton.setOnTouchListener(this);
        this.mZeroButton.setOnClickListener(this);
        this.mZeroButton.setOnTouchListener(this);
        this.mDeleteButton.setVisibility(View.INVISIBLE);
        this.mDeleteButton.setOnClickListener(this);
    }

    private void setEditTextListener() {
        this.mUserAccessCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (LoginPadActivity.this.mUserAccessCode.getText().length() == USER_PIN_MAX_CHAR) {
                    animateLoginButtonInOut(true);
                }
            }
        });
    }

    private void configureAnimations() {
        this.mAnimSlideIn = AnimationUtils
                .loadAnimation(getApplicationContext(),
                        cardinalsolutions.com.loginpad.R.anim.slide_in_from_left_small_bounce);
        this.mAnimSlideIn.setInterpolator(this.mAnimationSlideInterpolator);

        this.mAnimSlideOut = AnimationUtils.loadAnimation(
                getApplicationContext(), cardinalsolutions.com.loginpad.R.anim.slide_out_right);
        this.mAnimSlideOut.setInterpolator(this.mAnimationSlideInterpolator);
        this.mAnimSlideOut.setAnimationListener(new Animation.AnimationListener() {

	        @Override
	        public void onAnimationStart(Animation animationIn) {
	        }

	        @Override
	        public void onAnimationRepeat(Animation animationIn) {
	        }

	        @Override
	        public void onAnimationEnd(Animation animationIn) {
		        LoginPadActivity.this.mLoginButton.setVisibility(View.GONE);
		        if (LoginPadActivity.this.mFailedLogin) {
			        crossFade(getResources().getInteger(android.R.integer.config_mediumAnimTime),
			                  LoginPadActivity.this.mDeleteButton, null);
			        LoginPadActivity.this.mDeleteIsShowing = false;
			        LoginPadActivity.this.mFailedLogin = false;
		        }
	        }
        });
    }

    private void animateLoginButtonInOut(boolean animateIn) {
        if (animateIn) {
            this.mLoginButton.setVisibility(View.VISIBLE);
            this.mLoginButton.startAnimation(this.mAnimSlideIn);
        } else {
            this.mLoginProgress.setVisibility(View.GONE);
            this.mLoginButton.startAnimation(this.mAnimSlideOut);
            this.mLoginButton.setVisibility(View.GONE);
            this.mLoginButton.setText(getResources().getString(
                    cardinalsolutions.com.loginpad.R.string.activity_login));
        }
    }

    @Override
    public void onClick(View vIn) {
        if (!this.mDeleteIsShowing) {
            crossFade(
                    getResources().getInteger(
                            android.R.integer.config_mediumAnimTime),
                    this.mDeleteButton,
                    getResources().getString(cardinalsolutions.com.loginpad.R.string.activity_login_delete));
            this.mDeleteIsShowing = true;
        }
        switch (vIn.getId()) {
            case cardinalsolutions.com.loginpad.R.id.one_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mOneButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.two_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mTwoButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.three_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mThreeButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.four_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mFourButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.five_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mFiveButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.six_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mSixButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.seven_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mSevenButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.eight_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mEightButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.nine_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mNineButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.zero_button:
                if (this.mUserAccessCode.getText().length() < USER_PIN_MAX_CHAR) {
                    this.mUserAccessCode.append(this.mZeroButton.getText());
                }
                break;
            case cardinalsolutions.com.loginpad.R.id.activity_login_access_code_login:
                crossFade(
                        getResources().getInteger(
                                android.R.integer.config_longAnimTime),
                        this.mLoginButton,
                        getResources().getString(cardinalsolutions.com.loginpad.R.string.activity_login));
                this.mLoginButton.setText("");
                this.mLoginProgress.setVisibility(View.VISIBLE);
                attemptLogin(this.mUserAccessCode.getText().toString());
                break;
            case cardinalsolutions.com.loginpad.R.id.activity_login_access_code_delete:
                if (this.mLoginButton.getVisibility() == View.VISIBLE) {
                    animateLoginButtonInOut(false);
                }
                this.mUserAccessCode.dispatchKeyEvent(new KeyEvent(
                        KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                if (isEditTextEmpty(this.mUserAccessCode)) {
                    crossFade(
                            getResources().getInteger(
                                    android.R.integer.config_mediumAnimTime),
                            this.mDeleteButton, null);
                    this.mDeleteIsShowing = false;
                }
                break;
        }
    }

    private void crossFade(int animTimeIn, TextView textViewIn,
                          String valueStringIn) {

        textViewIn.setText(valueStringIn);
        textViewIn.setAlpha(0f);
        textViewIn.setVisibility(View.VISIBLE);

        textViewIn.animate().alpha(1f).setDuration(animTimeIn)
                .setListener(null);
    }

    private static boolean isEditTextEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    @Override
    public boolean onTouch(View vIn, MotionEvent eventIn) {
        switch (vIn.getId()) {
            case cardinalsolutions.com.loginpad.R.id.one_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.two_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.three_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.four_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.five_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.six_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.seven_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.eight_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.nine_button:
                toggleNumberColor(vIn, eventIn);
                break;
            case cardinalsolutions.com.loginpad.R.id.zero_button:
                toggleNumberColor(vIn, eventIn);
                break;
        }
        return false;
    }

    private void toggleNumberColor(View viewIn, MotionEvent eventIn) {
        if (eventIn.getAction() == MotionEvent.ACTION_DOWN) {
            ((TextView) viewIn).setTextColor(getResources().getColor(
                    R.color.blue));
        } else if (eventIn.getAction() == MotionEvent.ACTION_UP) {
            ((TextView) viewIn).setTextColor(getResources().getColor(
                    cardinalsolutions.com.loginpad.R.color.white));
        }
    }
}
