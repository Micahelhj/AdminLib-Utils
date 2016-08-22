/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.adutils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * 类描述：ABLogUtil 日志工具类
 * 创建人：Michael
 * 创建时间：2014/12/15 11:52
 * 修改人：Michael
 * 修改时间：2015/12/15 9:40
 * 修改备注：
 */
public class ABLogUtil {


    /**
     * debug开关.
     */
    private static boolean D = true;

    /**
     * info开关.
     */
    private static boolean I = true;

    /**
     * warm开关.
     */
    private static boolean W = true;
    /**
     * error开关.
     */
    private static boolean E = true;

    /**
     * 起始执行时间.
     */
    public static long startLogTimeInMillis = 0;

    /**
     * debug开关.
     *
     * @param d 是否开启
     */
    public static void setD(boolean d) {
        D = d;
    }

    /**
     * info开关
     *
     * @param i 是否开启
     */
    public static void setI(boolean i) {
        I = i;
    }

    /**
     * warm开关
     *
     * @param w 是否开启
     */
    public static void setW(boolean w) {
        W = w;
    }

    /**
     * error开关
     *
     * @param e 是否开启
     */
    public static void setE(boolean e) {
        E = e;
    }


    /**
     * 当前文件名 行号 函数名
     *
     * @return toStringBuffer TAG 当前文件名 方法行数 的字符串
     */
    public static String getFileLineMethod() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(traceElement.getFileName()).append(" | ").append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("()").append("]");
        return toStringBuffer.toString();
    }

    /**
     * 当前文件名
     *
     * @return FileName 文件名
     */
    public static String _FILE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getFileName();
    }

    /**
     * 当前方法名
     *
     * @return MethodName 方法名
     */
    public static String _FUNC_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getMethodName();
    }


    /**
     * 当前行
     *
     * @return LineNumber 行号
     */
    public static int _LINE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getLineNumber();
    }

    /**
     * 当前时间
     *
     * @return now time
     */
    public static String _TIME_() {
        Date now = new Date(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(now);
    }

    /**
     * 获取标签
     *
     * @return TAG 标签
     */
    private static String getTag(Exception exception) {
        StackTraceElement traceElement = ((exception).getStackTrace())[1];
        StringBuffer toStringBuffer = new StringBuffer("[").append(traceElement.getFileName()).append(" | ").append(traceElement.getLineNumber()).append(" | ").append(traceElement.getMethodName()).append("()").append("]");
        String TAG = toStringBuffer.toString();
        return TAG;
    }

    /**
     * debug日志
     *
     * @param message 打印的信息
     */
    public static void d(String message) {
        if (D) Log.d(getTag(new Exception()), message);
    }


    /**
     * debug日志
     *
     * @param format the format
     * @param args   the args
     */
    public static void d(String format, Object... args) {
        d(buildMessage(format, args));
    }

    /**
     * info日志
     *
     * @param message 打印的信息
     */
    public static void i(String message) {
        if (I)
            Log.i(getTag(new Exception()), message);
    }

    /**
     * info日志
     *
     * @param format the format
     * @param args   the args
     */
    public static void i(String format, Object... args) {
        i(buildMessage(format, args));
    }

    /**
     * error日志
     *
     * @param message 打印的信息
     */
    public static void w(String message) {
        if (W)
            Log.w(getTag(new Exception()), message);
    }


    /**
     * error日志
     *
     * @param format the format
     * @param args   the args
     */
    public static void w(String format, Object... args) {
        w(buildMessage(format, args));
    }

    /**
     * error日志
     *
     * @param message 打印的信息
     */
    public static void e(String message) {
        if (E)
            Log.e(getTag(new Exception()), message);
    }


    /**
     * error日志
     *
     * @param format the format
     * @param args   the args
     */
    public static void e(String format, Object... args) {
        e(buildMessage(format, args));
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog() {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        Log.d(getTag(new Exception()), "日志计时开始：" + startLogTimeInMillis);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param message     描述
     * @param isPrintTime 是否打印时间
     */
    public static void d(String message, boolean isPrintTime) {
        if (isPrintTime)
            Log.d(getTag(new Exception()), message + ":" + _TIME_() + "ms");
        else
            Log.d(getTag(new Exception()), message);
    }

    /**
     * debug日志的开关
     *
     * @param d 是否开启
     */
    public static void debug(boolean d) {
        D = d;
    }

    /**
     * info日志的开关
     *
     * @param i 是否开启
     */
    public static void info(boolean i) {
        I = i;
    }

    /**
     * error日志的开关
     *
     * @param e 是否开启
     */
    public static void error(boolean e) {
        E = e;
    }

    /**
     * 设置日志的开关
     *
     * @param d debug日志的开关
     * @param i info日志的开关
     * @param e error日志的开关
     */
    public static void setVerbose(boolean d, boolean i, boolean e) {
        D = d;
        I = i;
        E = e;
    }

    /**
     * 设置日志的开关
     *
     * @param d debug日志的开关
     * @param i info日志的开关
     * @param w info日志的开关
     * @param e error日志的开关
     */
    public static void setVerbose(boolean d, boolean i, boolean w, boolean e) {
        D = d;
        I = i;
        W = w;
        E = e;
    }

    /**
     * 打开所有日志，默认全打开
     */
    public static void openAll() {
        D = true;
        I = true;
        W = true;
        E = true;
    }

    /**
     * 关闭所有日志
     */
    public static void closeAll() {
        D = false;
        I = false;
        W = false;
        E = false;
    }

    /**
     * format日志
     *
     * @param format the format
     * @param args   the args
     * @return
     */
    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(ABLogUtil.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s",
                Thread.currentThread().getId(), caller, msg);
    }


    /* ========================下面的是本地存储相关的========================== */

    /**
     * 写日志对象
     */
    private LogWriter logWriter;

    /**
     * 写入本地日志线程
     */
    private class LogWriter extends Thread {
        /**
         * 文件路径
         */
        private String mFilePath;
        /**
         * 调用这个类的线程
         */
        private int mPid;
        /**
         * 线程运行标志
         */
        private boolean isRunning = true;

        /**
         * @param filePath 文件路径
         * @param pid
         */
        public LogWriter(String filePath, int pid) {
            this.mPid = pid;
            this.mFilePath = filePath;
        }

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);//日期格式化对象
            Process process = null;//进程
            BufferedReader reader = null;
            FileWriter writer = null;
            try {
                //执行命令行
                String cmd = "logcat *:e *:w | grep";
                process = Runtime.getRuntime().exec(cmd);
                //得到输入流
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
                //创建文件
                File file = new File(mFilePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                writer = new FileWriter(file, true);
                //循环写入文件
                String line = null;
                while (isRunning) {
                    line = reader.readLine();
                    if (line != null && line.length() > 0) {
                        writer.append("PID:" + this.mPid + "\t"
                                + sdf.format(new Date(System.currentTimeMillis())) + "\t" + line
                                + "\n");
                        writer.flush();
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null) {
                    process.destroy();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (writer != null) {
                    try {
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                process = null;
                reader = null;
                writer = null;
            }
        }

        public void end() {
            isRunning = false;
        }
    }

    /**
     * 整个应用只需要调用一次即可:开始本地记录
     *
     * @param filePath 要写入的目的文件路径
     * @param iswrite  是否需要写入sdk
     */
    public void startWriteLogToSdcard(String filePath, boolean iswrite) {

        if (iswrite) {
            if (logWriter == null) {
                try {
                    /** LogUtil这个类的pid,必须在类外面得到 */
                    logWriter = new LogWriter(filePath, android.os.Process.myPid());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            logWriter.start();
        }
    }

    /**
     * 整个应用只需要调用一次即可:结束本地记录
     */
    public void endWriteLogToSdcard() {
        if (logWriter != null) {
            logWriter.end();
        }
    }
    /* ========================下面的是需要上传的数据========================== */
    private LogUploader logUploader;

    /**
     * 日志上传线程
     */
    private class LogUploader extends Thread {
        /**
         * 当前线程是否正在运行
         */
        private boolean isRunning = true;
        /**
         * 上传所需要的url
         */
        private String mStrUrl;
        /**
         * 上传所需要的其他参数
         */
        private HashMap<String, String> mAllParams;
        /**
         * 上传所需要pid
         */
        private int mPid;

        /**
         * 构造方法
         *
         * @param strUrl    上传所需要的url
         * @param allParams 需要上传的额外的参数【除了日志以外】
         * @param pid       日志所在的pid
         */
        public LogUploader(String strUrl, HashMap<String, String> allParams, int pid) {
            this.mStrUrl = strUrl;
            this.mAllParams = allParams;
            this.mPid = pid;
        }

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);//日期格式化对象
            Process process = null;//进程
            BufferedReader reader = null;
            try {
                //执行命令行,得到输入流
                String cmd = "logcat *:e *:w | grep";
                process = Runtime.getRuntime().exec(cmd);
                reader = new BufferedReader(new InputStreamReader(process.getInputStream()), 1024);
                String line = null;
                while (isRunning) {
                    line = reader.readLine();
                    if (line != null && line.length() > 0) {
                        String log = "PID:" + this.mPid + "\t"
                                + sdf.format(new Date(System.currentTimeMillis())) + "\t" + line;
                        mAllParams.put("log", log);
                    } else {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (process != null) {
                    process.destroy();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                process = null;
                reader = null;
            }
        }

        public void end() {
            isRunning = false;
        }
    }

    /**
     * 整个应用调用一次即可：上传日志数据
     *
     * @param strUrl    上传所需要的url
     * @param allParams 需要上传的额外的参数【除了日志以外】
     * @param isUploadLog    是否需要上传
     */
    public void startUploadLog(String strUrl, HashMap<String, String>
            allParams,boolean isUploadLog) {

        if (isUploadLog) {
            if (logUploader == null) {
                logUploader = new LogUploader(strUrl, allParams, android.os.Process.myPid());
            }
            logUploader.start();
        }
    }

    /**
     * 整个应用调用一次即可：结束上传日志数据
     */
    public void endUploadLog() {
        if (logUploader != null) {
            logUploader.end();
        }
    }
}
