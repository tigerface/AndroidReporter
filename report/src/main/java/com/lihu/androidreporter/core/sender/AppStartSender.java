package com.lihu.androidreporter.core.sender;

import com.lihu.androidreporter.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lihu on 2016-4-5.
 */
public class AppStartSender extends Thread {

    private static final String TAG = "AppStartSender";
    private final List<File> oldLogFiles;
    private final String fileName;

    public AppStartSender(List<File> oldLogFiles, String fileName) {
        this.oldLogFiles = oldLogFiles;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        generateZipFile(oldLogFiles, fileName);
        uploadZipFile(fileName);
    }


    private void generateZipFile(List<File> files, String fileName) {
        Utils.debug(TAG, "generateZipFile begin:" + fileName);
        if (files == null || files.size() == 0) return;
        byte[] buffer = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(fileName));
            for (File file : files) {
                FileInputStream fis = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                int len;
                //读入需要下载的文件的内容，打包到zip文件
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                fis.close();
                //delete
                file.delete();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Utils.debug(TAG, "generateZipFile end:" + fileName);
    }

    //TODO
    private void uploadZipFile(String fileName) {
        Utils.debug(TAG, "uploadZipFile begin:" + fileName);
    }
}
