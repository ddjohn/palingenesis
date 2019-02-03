package com.ddjohn.androidlauncher;

import android.util.Log;

/**
 * Created by David Johansson on 2017-06-10.
 */

public class LOG {
    private static String ME = "notset";

    public static void APPLICATION(String ME) {
        LOG.ME = ME;
    }

    public static void METHOD(String msg) {
        Log.d(ME, msg);
    }

    public static void INFO(String msg) {
        Log.i(ME, msg);
    }

    public static void WARNING(String msg) {
        Log.w(ME, msg);
    }

    public static void ERROR(String msg) {
        Log.e(ME, msg);
    }
}
