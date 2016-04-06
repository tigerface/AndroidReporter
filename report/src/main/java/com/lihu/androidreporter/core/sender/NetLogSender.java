package com.lihu.androidreporter.core.sender;

import android.os.Process;

import com.lihu.androidreporter.Utils;
import com.lihu.androidreporter.core.entity.Report;

import java.util.concurrent.BlockingQueue;

/**
 * Created by lihu on 2016-4-5.
 */
public class NetLogSender extends Thread {
    private static final String TAG = "NetLogSender";
    private final BlockingQueue<Report> mHtmlSenderQueue;

    public NetLogSender(BlockingQueue<Report> reportQueue) {
        this.mHtmlSenderQueue = reportQueue;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        while (true) {
            Utils.debug(TAG, "html sender begin to work");
            try {
                Report report = mHtmlSenderQueue.take();
                if (report == null) continue;
                Utils.debug(TAG, "html sender get report from queue:" + report.toString());
                //TODO send to net
                //
//                Reporter.get().getConfig().getPublicParams();
                mHtmlSenderQueue.remove();
                Utils.debug(TAG, "report sender finish :" + report.toString() + "\n ");
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }

        }
    }
}
