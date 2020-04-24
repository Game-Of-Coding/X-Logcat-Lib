package com.gameofcoding.xlogcat;

import android.app.Application;

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    @Override
    public void onCreate() {
	super.onCreate();
	XLog.start(getApplicationContext());
	// Handle all possible exceptions
	Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread thread, Throwable e) {
		    try {
			e.printStackTrace();
			handleUncaughtException(thread, e, defaultEH);
		    } finally {
			Intent intent = getPackageManager().getLaunchIntentForPackage("com.gameofcoding.debugger");
			if (intent != null) {
			    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			    intent.putExtra(AppConstants.KEY_ERROR, e);
			    startActivity(intent);
			} else {
			    Log.e(TAG, "Couldn't start debugger application.");
			}
		    }
		}
	    });
    }
}
