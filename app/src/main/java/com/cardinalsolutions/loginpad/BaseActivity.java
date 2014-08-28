package com.cardinalsolutions.loginpad;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.cardinalsolutions.android.arch.autowire.AndroidAutowire;


public class BaseActivity extends Activity {

	private static String TAG = "BaseActivity";

	// Shared Preferences
	public SharedPreferences mSharedPreferences;
	public SharedPreferences.Editor mPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = AndroidAutowire.getLayoutResourceByAnnotation(this,
                this, BaseActivity.class);

        // If this activity is not annotated with AndroidLayout, do nothing
        if (layoutId == 0) {
            return;
        }
        setContentView(layoutId);
	    configureSharedPreferences();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        AndroidAutowire.autowire(this, BaseActivity.class);
    }

	private void configureSharedPreferences() {
		Logger.i(TAG, "Configuring shared preferences.");
		this.mSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		this.mPreferencesEditor = this.mSharedPreferences.edit();
	}
}
