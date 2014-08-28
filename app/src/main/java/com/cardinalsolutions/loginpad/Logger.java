package com.cardinalsolutions.loginpad;

import android.util.Log;

/*
 * Logger
 *
 * Custom logger that allows you to disable logging across the entire app.
 *
 * @author sking
 *
 *         May 17, 2014 11:16:51 AM
 */
public class Logger {

	private static final boolean LOGGING = true;
	private static final String TAG = "LoginPadApp: ";

	public static void i(String className, String message) {
		if (LOGGING) {
			Log.i(TAG, className + " - " + message);
		}
	}

	public static void d(String className, String message) {
		if (LOGGING) {
			Log.d(TAG, className + " - " + message);
		}
	}

	public static void v(String className, String message) {
		if (LOGGING) {
			Log.v(TAG, className + " - " + message);
		}
	}

	public static void e(String className, String message, Exception e) {
		if (LOGGING) {
			Log.e(TAG, className + " - " + message, e);
		}
	}
}
