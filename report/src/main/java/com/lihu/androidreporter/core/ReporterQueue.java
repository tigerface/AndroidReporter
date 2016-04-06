package com.lihu.androidreporter.core;

import com.lihu.androidreporter.core.collector.FileLogCollector;
import com.lihu.androidreporter.core.entity.Report;
import com.lihu.androidreporter.core.sender.NetLogSender;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by lihu on 2016-4-5.
 * 忘记添加删除report
 */
public class ReporterQueue {
    private BlockingQueue<Report> mLogQueue;
    private BlockingQueue<Report> mHtmlQueue;
    private FileLogCollector mFileLog;
    private NetLogSender mNetLogSender;


    public void start() {
        mLogQueue = new LinkedBlockingQueue<>();
        mHtmlQueue= new LinkedBlockingQueue<>();
        mFileLog = new FileLogCollector(mLogQueue);
        mNetLogSender = new NetLogSender(mHtmlQueue);
        mFileLog.start();
        mNetLogSender.start();
    }

    public void stop() {
    }

    public void add(Report report) {
        if (report == null || report.getType() == 0) {
            return;
        }
        report.setTimeStamp(System.currentTimeMillis());
        mLogQueue.add(report);
        if(Report.Type.HTTP == report.getType()){
            mHtmlQueue.add(report);
        }
    }
}
