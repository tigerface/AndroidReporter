package com.lihu.androidreporter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lihu on 2016-4-1.
 */
public class Utils {
    public static boolean debugMode = false;

    public static String getPackageFileDir(Context context) {
        return context.getFilesDir().getAbsolutePath();
    }

    public static void write(String content, File file) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            long begin = System.currentTimeMillis();
//            outputStream.write(("begin to write:" + begin + "\n").getBytes());
//            outputStream.write("contents:".getBytes());
            outputStream.write(content.getBytes());
            long end = System.currentTimeMillis();
//            outputStream.write(("end to write:" + end + "\n").getBytes());
            outputStream.write(("\n").getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPermission(Context context, String permission) {
        PackageManager pm = context.getPackageManager();
        boolean hasPermission = (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permission, context.getPackageName()));
        return hasPermission;
    }

    //TODO
    public static String getDumpFileName() {
        return "funshion.zip";
    }

    public static void debug(String tag, String log) {
        if (debugMode) {
            Log.d(tag, log);
        }
    }

    public static void error(String tag, String log) {
        if (debugMode) {
            Log.e(tag, log);
        }
    }

    public static void info(String tag, String log) {
        if (debugMode) {
            Log.i(tag, log);
        }
    }
}
