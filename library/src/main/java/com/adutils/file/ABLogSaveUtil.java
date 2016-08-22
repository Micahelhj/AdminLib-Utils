package com.adutils.file;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 项目名称：wlkt-as
 * 类描述：自定义日志写入工具
 * 创建人：Michael_hj
 * 创建时间：2016/08/03 13:46
 * 修改人：Michael_hj
 * 修改时间：2016/08/03 13:46
 * 修改备注：
 */
public class ABLogSaveUtil {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault());//每条日志的时间
    private static final HashMap<String, File> logFileMap = new HashMap<>();//日志保存文件对象集合
    private static final HashMap<String, BufferedWriter> logFileBufferedWriterMap = new HashMap<>();//日志文件的写入流Map
    private static final ExecutorService pool = Executors.newCachedThreadPool();//日志写入的线程池
    private static final int SaveMaxTime = 7;//日志保存时间(天)
    private static ABLogSaveUtil instance;//日志写入工具单例对象

    public ABLogSaveUtil(final String[] logFileNames) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String name : logFileNames) {
                        String filePath = ABFileManager.getNormalLogDownloadDir() + File.separator + name;//日志文件路径
                        deleteAllOldFile(filePath);//删除当前文件夹下的过期及空日志文件
                        String fileName = name + "_" +  "_"
                                + System.currentTimeMillis() + ".log";//日志文件名
                        ABFileUtil.makeDirs(filePath + File.separator + fileName);//初始化对应地址的文件
                        File file = new File(filePath + File.separator + fileName);//新建一个空日志文件
                        logFileMap.put(name, file);//保存日志文件对象
                        logFileBufferedWriterMap.put(name, new BufferedWriter(new FileWriter(file, true)));//保存日志写入流BufferedWriter
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 初始化单例
     */
    public static void createInstance( String[] logFileNames) {
        instance = new ABLogSaveUtil(logFileNames);
    }

    /**
     * 获取日志记录管理器单例
     *
     * @return 日志记录管理器单例
     */
    public static ABLogSaveUtil getInstance() {
        return instance;
    }

    /**
     * 记录日志到本地
     *
     * @param logTag 文件夹名
     * @param data   数据
     */
    public void recordLog(String logTag, String data) {
        pool.execute(new PoolWorker(logTag, data));
    }

    /**
     * 工作队列实现
     */
    private class PoolWorker implements Runnable {
        private String logTag;
        private String data;

        public PoolWorker(String logTag, String data) {
            this.logTag = logTag;
            this.data = data;
        }

        public void run() {
            writeFile(logTag, data);
        }
    }

    /**
     * write file
     *
     * @param content 上下文
     */
    public void writeFile(String logTag, String content) {
        try {// 进行写操作
            BufferedWriter bufferedWriter = logFileBufferedWriterMap.get(logTag);//获取对应的文件写入流对象
            if (bufferedWriter != null) {
                //noinspection SynchronizationOnLocalVariableOrMethodParameter
                synchronized (bufferedWriter) {
                    File file = logFileMap.get(logTag);//获取文件对象  用于修改最新修改时间
                    bufferedWriter
                            .append(" <").append(timeFormat.format(Calendar.getInstance().getTime()))//可读时间
                            .append(" ").append(String.valueOf(Thread.currentThread().getId()))//线程id
                            .append(" ").append(String.valueOf(file.lastModified() == 0 ? 0 : System.currentTimeMillis() - file.lastModified()))//时间差（毫秒）
                            .append("> ").append(content)//日志内容
                            .append("\r\n").flush();
                    //noinspection ResultOfMethodCallIgnored
                    file.setLastModified(System.currentTimeMillis());//设置最新修改时间
                }
            } else {
                Log.i("LogSaveUtil", "logTag === " + logTag + "  file == null || bufferedWriter == null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有过期文件
     *
     * @param path 文件夹完整绝对路径
     */
    public static void deleteAllOldFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp;
        for (String aTempList : tempList) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + aTempList);
            } else {
                temp = new File(path + File.separator + aTempList);
            }
            if (temp.isFile()) {
                long updateTime = (System.currentTimeMillis() - temp.lastModified()) / 86400;//除以一天
                if (updateTime > SaveMaxTime || temp.length() == 0)//日志最大保存时间 空间大小为0的文件
                    //noinspection ResultOfMethodCallIgnored
                    temp.delete();
            }
        }
    }
}
