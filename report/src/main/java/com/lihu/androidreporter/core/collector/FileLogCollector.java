package com.lihu.androidreporter.core.collector;

import android.os.Process;

import com.lihu.androidreporter.Utils;
import com.lihu.androidreporter.core.Reporter;
import com.lihu.androidreporter.core.entity.Report;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by lihu on 2016-4-5.
 */
public class FileLogCollector extends Thread implements Collector{
    private static final String TAG = "FileLogCollector";
    private final BlockingQueue<Report> mReportQueue;

    public FileLogCollector(BlockingQueue<Report> reportQueue) {
        this.mReportQueue = reportQueue;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        while (true) {
            //TODO file.length not juge
            try {
                Utils.debug(TAG, "file sender begin to work");
                Report report = mReportQueue.take();
                if(report == null) continue;
                //save to file
                Utils.debug(TAG, "file sender get report from queue:" + report.toString());
                StringBuffer fileName = new StringBuffer();
                fileName.append(Reporter.get().getConfig().getLogDir());
                fileName.append(File.separator);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
                fileName.append(df.format(new Date()));
                fileName.append(".log");
                File file = new File(fileName.toString());
                Utils.write(report.toString(), file);
                mReportQueue.remove(report);
                Utils.debug(TAG, "file sender finish :" + report.toString() + "\n " + fileName.toString());
            } catch (InterruptedException e) {
                Utils.error(TAG, e.getMessage());
                continue;
            }

        }
    }

    @Override
    public void collect() {

    }
}
