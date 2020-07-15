package cn.com.eju.deal.common.util;

import java.io.File;

/**
 * Created by tanlang on 2017-02-13.
 */
public class FileUtils {

    /***
     * 删掉目录下一天以前的文件
     */
    public static void deleteFile(File direct, long time) {
        File[] files = direct.listFiles();
        if (files != null) {
            for (File f : files) {
                try {
                    long oldtime = Long.parseLong(f.getName());
                    //一天毫秒24*3600*1000 = 86400000
                    if (time - oldtime > 86400000) {
                        if (f.isDirectory() && f.listFiles() != null) {
                            for (File file : f.listFiles()) {
                                file.delete();
                            }
                            f.delete();
                        }
                    }
                } catch (Exception e) {
                    if (f.isDirectory() && f.listFiles() != null) {
                        for (File file : f.listFiles()) {
                            file.delete();
                        }
                        f.delete();
                    }
                }
            }
        }
    }

}
