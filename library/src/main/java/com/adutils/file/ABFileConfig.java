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
package com.adutils.file;

public class ABFileConfig {
    /**
     * 手机内部存储的数据库文件
     */
    private static String databases = "databases";
    /**
     * 手机内部SharedPreferences文件
     */
    private static String shared_prefs = "shared_prefs";

    /**
     * 默认下载文件存储目录.
     */
    private static String root_dir = "downloads";

    /**
     * 默认下载图片文件存储目录.
     */
    private static String image_dir = "images";

    /**
     * 默认下载文件存储目录.
     */
    private static String file_dir = "files";

    /**
     * APP缓存目录.
     */
    private static String cache_dir = "caches";
    /**
     * APP异常文件存储目录.
     */
    private static String log_dir = "logs";
    /**
     * APP异常文件存储目录.
     */
    private static String crach_dir = "crachs";
    /**
     * APP异常文件存储目录.
     */
    private static String normal_dir = "normals";
    /**
     * DB目录.
     */
    private static String DB_DIR = "db";
    /**
     * question目录.
     */
    private static String Question_dir = "question";
    /**
     * download目录.
     */
    private static String Download_dir = "download";

    /**
     * =================================================================================================
     */
    public static String getDatabases() {
        return databases;
    }

    public static void setDatabases(String databases) {
        ABFileConfig.databases = databases;
    }

    public static String getShared_prefs() {
        return shared_prefs;
    }

    public static void setShared_prefs(String shared_prefs) {
        ABFileConfig.shared_prefs = shared_prefs;
    }

    public static String getRoot_dir() {
        return root_dir;
    }

    public static void setRoot_dir(String root_dir) {
        ABFileConfig.root_dir = root_dir;
    }

    public static String getImage_dir() {
        return image_dir;
    }

    public static void setImage_dir(String image_dir) {
        ABFileConfig.image_dir = image_dir;
    }

    public static String getFile_dir() {
        return file_dir;
    }

    public static void setFile_dir(String file_dir) {
        ABFileConfig.file_dir = file_dir;
    }

    public static String getCache_dir() {
        return cache_dir;
    }

    public static void setCache_dir(String cache_dir) {
        ABFileConfig.cache_dir = cache_dir;
    }

    public static String getLog_dir() {
        return log_dir;
    }

    public static void setLog_dir(String log_dir) {
        ABFileConfig.log_dir = log_dir;
    }

    public static String getCrach_dir() {
        return crach_dir;
    }

    public static void setCrach_dir(String crach_dir) {
        ABFileConfig.crach_dir = crach_dir;
    }

    public static String getNormal_dir() {
        return normal_dir;
    }

    public static void setNormal_dir(String normal_dir) {
        ABFileConfig.normal_dir = normal_dir;
    }

    public static String getDbDir() {
        return DB_DIR;
    }

    public static void setDbDir(String dbDir) {
        DB_DIR = dbDir;
    }

    public static String getQuestion_dir() {
        return Question_dir;
    }

    public static void setQuestion_dir(String question_dir) {
        Question_dir = question_dir;
    }

    public static String getDownload_dir() {
        return Download_dir;
    }

    public static void setDownload_dir(String download_dir) {
        Download_dir = download_dir;
    }
}
