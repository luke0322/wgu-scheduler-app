package com.scheduler.wgu_scheduler_app.ui.utils;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Utils {

    /**
     * Generic activity switcher.
     * @param cls the activity to start
     * @param fa the current active fragment activity
     */
    public static void switchActivity(Class cls, FragmentActivity fa) {
        Intent intent = new Intent(fa, cls);
        fa.startActivity(intent);
    }

    /**
     * Generic fragment switcher.
     * @param fa the fragment activity
     * @param id the new id to replace with
     * @param fragment the new instance of the fragment
     */
    public static void switchFragment(FragmentActivity fa, int id, Fragment fragment) {
        fa.getSupportFragmentManager().beginTransaction()
                .replace(id, fragment)
                .commitNow();
    }
}
