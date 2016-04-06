package com.lihu.androidreporter.core;

/**
 * Created by lihu on 2016-4-1.
 */

import android.content.Context;

import com.lihu.androidreporter.Utils;
import com.lihu.androidreporter.core.entity.Report;
import com.lihu.androidreporter.core.sender.AppStartSender;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO 是否需要使用新的进程
 */
public class Reporter {
    private static final long DEFAULT_EXPIRED_MILLS = 2 * 24 * 60 * 60 * 1000;//2 days
    private static final long NEED_UPLOAD_MILLS = 1 * 24 * 60 * 60 * 1000;//1 days
    private static final String TAG = "Reporter";
    private boolean inited;
    private Context mContext;
    private ReporterQueue mReporterQueue;
    private static Reporter reporter = new Reporter();
    private Config config;

    private Reporter() {
        mReporterQueue = new ReporterQueue();
    }

    public static Reporter get() {
        return reporter;
    }

    public void init(Context context, Config config) throws Exception {
        if (inited) {
            return;
        }
        mContext = context.getApplicationContext();
        if (config != null) {
            this.config = config;
        } else {
            this.config = getDefaultConfig();
        }

        prepare();
        inited = true;
    }

    private Config getDefaultConfig() {
        config = new Config();
        config.setLogDir(Utils.getPackageFileDir(mContext));
        config.setExpiredTimeMills(DEFAULT_EXPIRED_MILLS);
        config.setPublicParams(getDefaultParams());
        return config;
    }

    private Map getDefaultParams() {
        return new HashMap();
    }

    public Config getConfig() {
        return config;
    }

    public void setDebugMode(boolean debugMode) {
        Utils.debugMode = debugMode;
    }

    private void prepare() throws Exception {
        //1、check uses-permission
        boolean hasNetPermission = Utils.checkPermission(mContext, "android.permission.INTERNET");
        if (!hasNetPermission) {
            throw new Exception("report not grant internet permission");
        }
        //2、clean useless log files
        String logDir = config.getLogDir();
        File logFiles = new File(logDir);
        List<File> oldLogFiles = new ArrayList<>();
        long lastModifyMills = 0;
        for (File file : logFiles.listFiles()) {
            long lastModifiedMills = file.lastModified();
            if (System.currentTimeMillis() - lastModifiedMills > config.getExpiredTimeMills()) {
                file.delete();
            }
            if (System.currentTimeMillis() - lastModifiedMills > NEED_UPLOAD_MILLS) {
                //昨天之前的文件
                oldLogFiles.add(file);
                if (lastModifiedMills > lastModifyMills) {
                    lastModifyMills = lastModifiedMills;
                }
            }
        }
        //3、start
        mReporterQueue.start();
        //4、upload zip files
        String dumpFileName = String.valueOf(lastModifyMills) + ".zip";
        String dumpFullPath = getConfig().getLogDir() + File.separator + dumpFileName;
        new AppStartSender(oldLogFiles, String.valueOf(dumpFullPath)).start();
    }


    public static void report(Report report) {
        if (!reporter.inited) {
            try {
                throw new Exception("report inited must be invoked before use");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        reporter.mReporterQueue.add(report);
    }

    public static void log(String tag, String log) {
        if (!reporter.inited) {
            try {
                throw new Exception("report inited must be invoked before use");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        Report report = new Report();
        report.setTag(tag);
        report.setExtra(log);
        reporter.mReporterQueue.add(report);
    }

    //TODO stop all threads
    public void distroy() {
        //stop all threads
        mReporterQueue.stop();
    }



    public class Config {
        private String logDir;
        private long ExpiredTimeMills;
        private Map publicParams;

        public String getLogDir() {
            return logDir;
        }

        public void setLogDir(String logDir) {
            this.logDir = logDir;
        }

        public long getExpiredTimeMills() {
            return ExpiredTimeMills;
        }

        public void setExpiredTimeMills(long expiredTimeMills) {
            ExpiredTimeMills = expiredTimeMills;
        }

        public Map getPublicParams() {
            return publicParams;
        }

        public void setPublicParams(Map publicParams) {
            this.publicParams = publicParams;
        }
    }
}
