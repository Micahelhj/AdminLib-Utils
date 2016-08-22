package com.adutils.file;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.adutils.phone.ABSDCardUtils;

import java.io.File;

/**
 * 项目名称：AdminLibs 类描述：本应用数据清除管理器 主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录 创建时间：2016/2/4 14:45 修改人：Michael 修改时间：2016/2/4 14:45 修改备注：
 */
public class ABFileManager {
    /**
     * 默认APP根目录.
     */
    private static String downloadRootDir = null;
    /**
     * 默认崩溃日志文件目录
     */
    private static String crachLoadRootDir = null;
    /**
     * 默认日常日志文件目录
     */
    private static String normalLoadRootDir = null;
    /**
     * 默认下载图片文件目录.
     */
    private static String imageDownloadDir = null;
    /**
     * 默认下载文件目录.
     */
    private static String fileDownloadDir = null;
    /**
     * 默认缓存目录.
     */
    private static String cacheDownloadDir = null;
    /**
     * 默认下载数据库文件的目录.
     */
    private static String dbDownloadDir = null;
    /**
     * 默认下载数据库文件的目录.
     */
    private static String questionDownloadDir = null;

    public interface OnLoadFinishedListener<Result, TAG> {
        void onFinish(Result result, TAG tag);
    }

    /**
     * 描述：初始化存储目录.
     *
     * @param context the context
     */
    public static void initFileDir(final Context context, final OnLoadFinishedListener<Boolean, Integer> onLoadFinishedListener) {
        new Thread(new Runnable() {
            @SuppressWarnings("ResultOfMethodCallIgnored")
            @Override
            public void run() {
                boolean flag = true;
                try {
                    //默认下载图片文件目录.
                    String imageDownloadPath = ABFileConfig.getImage_dir() + File.separator;
                    //默认下载文件目录.
                    String fileDownloadPath = ABFileConfig.getFile_dir() + File.separator + ABFileConfig.getDownload_dir() + File.separator;
                    //默认缓存目录.
                    //        String cacheDownloadPath = ABFileConfig.getCache_dir() + File.separator;
                    //默认CRACH LOG目录.
                    String crachLogDownloadPath = ABFileConfig.getLog_dir() + File.separator + ABFileConfig.getCrach_dir() + File.separator;
                    //默认NORMAL LOG目录.
                    String normalLogDownloadPath = ABFileConfig.getLog_dir() + File.separator + ABFileConfig.getNormal_dir() + File.separator;
                    //默认DB目录.
                    String dbDownloadPath = ABFileConfig.getDbDir() + File.separator;
                    //默认DB目录.
                    String questionDownloadPath = ABFileConfig.getFile_dir() + File.separator + ABFileConfig.getQuestion_dir() + File.separator;
                    boolean isHaveSDcard = ABSDCardUtils.isAvailable();
                    if (isHaveSDcard) {
                        //获取根目录文件
                        //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
                        //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
                        File externalFilesDir = context.getExternalFilesDir(null);
                        File externalCacheDir = context.getExternalCacheDir();
                        File rootFile = externalFilesDir == null ? context.getFilesDir() : externalFilesDir;
                        File rootCache = externalCacheDir == null ? context.getCacheDir() : externalCacheDir;
                        /**************************************** 文件缓存根目录地址 *******************************************/
                        File downloadDir = new File(rootFile.getAbsolutePath());
                        if (!downloadDir.exists()) {
                            downloadDir.mkdirs();
                        }
                        downloadRootDir = downloadDir.getPath();
                        /***************************************** 缓存文件存储地址 ******************************************/
                        File cacheDownloadDirFile = new File(rootCache.getAbsolutePath());
                        if (!cacheDownloadDirFile.exists()) {
                            cacheDownloadDirFile.mkdirs();
                        }
                        cacheDownloadDir = cacheDownloadDirFile.getPath();
                        /**************************************** 崩溃日志文件存储地址 *****************************************/
                        File crachDownloadDirFile = new File(rootFile.getAbsolutePath() + "/" + crachLogDownloadPath);
                        if (!crachDownloadDirFile.exists()) {
                            crachDownloadDirFile.mkdirs();
                        }
                        crachLoadRootDir = crachDownloadDirFile.getPath();
                        /**************************************** 日常日志文件存储地址 *****************************************/
                        File normalDownloadDirFile = new File(rootFile.getAbsolutePath() + "/" + normalLogDownloadPath);
                        if (!crachDownloadDirFile.exists()) {
                            crachDownloadDirFile.mkdirs();
                        }
                        normalLoadRootDir = normalDownloadDirFile.getPath();
                        /**************************************** 图片文件存储地址 *******************************************/
                        File imageDownloadDirFile = new File(rootFile.getAbsolutePath() + "/" + imageDownloadPath);
                        if (!imageDownloadDirFile.exists()) {
                            imageDownloadDirFile.mkdirs();
                        }
                        imageDownloadDir = imageDownloadDirFile.getPath();
                        /**************************************** 文件下载存储地址 *******************************************/
                        File fileDownloadDirFile = new File(rootFile.getAbsolutePath() + "/" + fileDownloadPath);
                        if (!fileDownloadDirFile.exists()) {
                            fileDownloadDirFile.mkdirs();
                        }
                        fileDownloadDir = fileDownloadDirFile.getPath();
                        /**************************************** 数据库存放地址 *******************************************/
                        File dbDownloadDirFile = new File(rootFile.getAbsolutePath() + "/" + dbDownloadPath);
                        if (!dbDownloadDirFile.exists()) {
                            dbDownloadDirFile.mkdirs();
                        }
                        dbDownloadDir = dbDownloadDirFile.getPath();
                        /***********************************************************************************/
                        File questionDownloadDirFile = new File(rootFile.getAbsolutePath() + "/" + questionDownloadPath);
                        if (!questionDownloadDirFile.exists()) {
                            questionDownloadDirFile.mkdirs();
                        }
                        questionDownloadDir = questionDownloadDirFile.getPath();
                        /***********************************************************************************/
                    }
                } catch (Exception e) {
                    flag = false;
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Gets the RootDir.
     *
     * @return the download root dir
     */
    public static String getDownloadRootDir() {
        return downloadRootDir;
    }

    /**
     * Gets the image download dir.
     *
     * @return the image download dir
     */
    public static String getImageDownloadDir() {
        return imageDownloadDir;
    }

    /**
     * Gets the file download dir.
     *
     * @return the file download dir
     */
    public static String getFileDownloadDir() {
        return fileDownloadDir;
    }

    /**
     * Gets the cache download dir.
     *
     * @return the cache download dir
     */
    public static String getCacheDownloadDir() {
        return cacheDownloadDir;
    }

    /**
     * Gets the crach download dir.
     *
     * @return the crach download dir
     */
    public static String getCrashLogDownloadDir() {
        return crachLoadRootDir;
    }

    /**
     * Gets the crach download dir.
     *
     * @return the crach download dir
     */
    public static String getNormalLogDownloadDir() {
        return normalLoadRootDir;
    }

    /**
     * Gets the db download dir.
     *
     * @return the db download dir
     */
    public static String getDbDownloadDir() {
        return dbDownloadDir;
    }

    /**
     * Gets the Question download dir.
     *
     * @return the Question download dir
     */
    public static String getQuestionDownloadDir() {
        return questionDownloadDir;
    }
/**
 * =========================================手机内部存储 数据清理==========================================================
 */
    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     *
     * @param context 上下文
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     *
     * @param context 上下文
     */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File(context.getFilesDir().getPath() + context.getPackageName() + "/" + ABFileConfig.getDatabases()));
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     */
    public static void cleanDB() {
        deleteFilesByDirectory(new File(ABFileConfig.getDbDir() + "/" + ABFileConfig.getDatabases()));
    }

    /**
     * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     *
     * @param context 上下文
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File(context.getFilesDir().getPath() + context.getPackageName() + "/" + ABFileConfig.getShared_prefs()));
    }

    /**
     * 按名字清除本应用数据库(/data/data/com.xxx.xxx/databases)
     *
     * @param context 上下文
     * @param dbName  数据库名称
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容
     *
     * @param context 上下文
     */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * ===================================================================================
     */
    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param directory 文件夹File对象
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     *
     * @param filePath 文件路径
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     *
     * @param filePath 文件路径
     */
    public static void cleanCustomCache(String... filePath) {
        for (String fp : filePath) {
            cleanCustomCache(fp);
        }
    }

    /**
     * 清除本应用所有的数据（手机内部存储）
     *
     * @param context  上下文
     * @param filePath 文件路径
     */
    public static void cleanApplicationData(Context context, String... filePath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String fp : filePath) {
            cleanCustomCache(fp);
        }
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     *
     * @param context 上下文
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * 清除image_dir路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     */
    public static void cleanExternalImages() {
        deleteFilesByDirectory(new File(ABFileConfig.getImage_dir()));
    }

    /**
     * 清除file_dir路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     */
    public static void cleanExternalFiles() {
        deleteFilesByDirectory(new File(ABFileConfig.getFile_dir()));
    }

    /**
     * 清除root_dir路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     */
    public static void cleanExternalRoot() {
        deleteFilesByDirectory(new File(ABFileConfig.getRoot_dir()));
    }

    /**
     * ================================手机外部存储文件删除 清理==================================================================
     */
    /**
     * 清除本应用所有外部存储的数据(即当前文件保存位置)
     *
     * @param filePath
     */
    public static void cleanExternalData(String... filePath) {
        cleanCustomCache(getCacheDownloadDir(), getDbDownloadDir(), getCrashLogDownloadDir(), getNormalLogDownloadDir(), getImageDownloadDir(), getFileDownloadDir());
        for (String fp : filePath) {
            cleanCustomCache(fp);
        }
    }

    /**
     * 删除DB文件
     *
     * @param dbFileName
     */
    public void deleteFileInDbs(String dbFileName) {
        ABFileUtil.deleteFile(getDbDownloadDir() + "/" + dbFileName);
    }

    /**
     * 删除Images文件
     *
     * @param imageFileName
     */
    public void deleteFileInImages(String imageFileName) {
        ABFileUtil.deleteFile(getImageDownloadDir() + "/" + imageFileName);
    }

    /**
     * 删除Files文件
     *
     * @param fileName
     */
    public void deleteFileInFiles(String fileName) {
        ABFileUtil.deleteFile(getFileDownloadDir() + "/" + fileName);
    }

    /**
     * 删除Crachs文件
     *
     * @param crashsFileName
     */
    public void deleteFileInCrachs(String crashsFileName) {
        ABFileUtil.deleteFile(getCrashLogDownloadDir() + "/" + crashsFileName);
    }

    /**
     * 删除Caches文件
     *
     * @param cachesFileName
     */
    public void deleteFileInCaches(String cachesFileName) {
        ABFileUtil.deleteFile(getCacheDownloadDir() + "/" + cachesFileName);
    }

    /**
     * ===========================================文件保存、创建 数据保存==================================================
     */

    /**
     * ==================================================文件大小 内存大小相关=============================================
     */
    /**
     * 获取手机指定位置可用空间大小
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableMemorySize(String filePath) {
        try {
            if (ABSDCardUtils.isAvailable()) {
                StatFs statFs = new StatFs(filePath);
                long blockSize = statFs.getBlockSize();
                long availableBlocks = statFs.getAvailableBlocks();
                return availableBlocks * blockSize;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return -1;
    }

    /**
     * 获取手机当前位置可用空间大小
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableMemorySize() {
        try {
            if (ABSDCardUtils.isAvailable()) {
                StatFs statFs = new StatFs(getDownloadRootDir());
                long blockSize = statFs.getBlockSize();
                long availableBlocks = statFs.getAvailableBlocks();
                return availableBlocks * blockSize;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return -1;
    }
}
