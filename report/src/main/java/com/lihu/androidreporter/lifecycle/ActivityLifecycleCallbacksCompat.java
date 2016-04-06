package com.lihu.androidreporter.lifecycle;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by lihu on 2016-4-1.
 */
public interface ActivityLifecycleCallbacksCompat {
    void onActivityCreated(Activity activity, Bundle savedInstanceState);

    void onActivityStarted(Activity activity);

    void onActivityResumed(Activity activity);

    void onActivityPaused(Activity activity);

    void onActivityStopped(Activity activity);

    void onActivitySaveInstanceState(Activity activity, Bundle outState);

    void onActivityDestroyed(Activity activity);
}
