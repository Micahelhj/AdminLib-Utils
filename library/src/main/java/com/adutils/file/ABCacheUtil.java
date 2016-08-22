package com.adutils.file;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class ABCacheUtil {
    private static final String TAG = "ABCacheUtil";
    public static final int STRING = 0;//字符串
    public static final int BYTE_ARRAY = 1;//字节数组
    public static final int SERIALIZABLE = 2;//序列化对象
    public static final int JSON_OBJECT = 3;//json对象
    public static final int JSON_ARRAY = 4;//json数组
    public static final int DRAWABLE = 5;//drawable
    public static final int BITMAP = 6;//bitmap
    /**
     * 只使用内存缓存(LruCache)
     */
    public static final int ONLY_LRU = 1;
    /**
     * 只使用硬盘缓存(DiskLruCache)
     */
    public static final int ONLY_DISKLRU = 2;
    /**
     * 同时使用内存缓存(LruCache)与硬盘缓存(DiskLruCache)
     */
    public static final int ALL_ALLOW = 0;

    /**
     * 设置类型为硬盘缓存——用于取硬盘缓存大小
     */
    public static final int DISKSIZE = 0;
    /**
     * 设置类型为内存缓存——用于取内存缓存大小
     */
    public static final int MEMORYSIZE = 1;

    //设置硬盘缓存的最大值，单位为M
    private static int maxSizeForDiskLruCache = 0;
    //设置内存缓存的最大值，单位为M
    private static int maxMemoryForLruCache = 0;
    //设置自定义的硬盘缓存文件夹名称
    private static String dirNameForDiskLruCache = "";
    //记录硬盘缓存与内存缓存起效标志
    private static int model = 0;
    //硬盘缓存管理类
    private static ABCache abCache;
    //内存缓存管理类
    private static ABLruCache abLruCache;
    private static Context ct;

    /**
     * 初始化缓存管理
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        ct = context;
        init_();
    }

    //根据传入的标志，初始化内存缓存以及硬盘缓存，默认开启是同时使用
    private static void init_() {
        switch (model) {
            case ALL_ALLOW:
                initDiskLruCacheManager();
                initLruCacheManager();
                break;
            case ONLY_LRU:
                initLruCacheManager();
                break;
            case ONLY_DISKLRU:
                initDiskLruCacheManager();
                break;
            default:
                break;
        }
    }

    //初始化内存缓存管理
    private static void initLruCacheManager() {
        if (maxMemoryForLruCache > 0) {
            abLruCache = new ABLruCache(maxMemoryForLruCache);
        } else {
            abLruCache = new ABLruCache();
        }
    }

    //初始化硬盘缓存管理
    private static void initDiskLruCacheManager() {
        if (maxSizeForDiskLruCache > 0 && !TextUtils.isEmpty(dirNameForDiskLruCache)) {
            abCache = ABCache.get(ct, dirNameForDiskLruCache, maxSizeForDiskLruCache, maxSizeForDiskLruCache * 1024 * 1024);
        } else if (maxSizeForDiskLruCache > 0) {
            abCache = ABCache.get(ct, maxSizeForDiskLruCache * 1024 * 1024);
        } else if (!TextUtils.isEmpty(dirNameForDiskLruCache)) {
            abCache = ABCache.get(ct, dirNameForDiskLruCache);
        } else {
            abCache = ABCache.get(ct);
        }
    }

    /**
     * 设置硬盘缓存的最大值，单位为兆（M）.
     *
     * @param maxSizeForDisk 硬盘缓存最大值，单位为兆（M）
     */
    public static void setMaxSize(int maxSizeForDisk) {
        maxSizeForDiskLruCache = maxSizeForDisk;
    }

    /**
     * 设置内存缓存的最大值，单位为兆（M）.
     *
     * @param maxMemory 内存缓存最大值，单位为兆（M）
     */
    public static void setMaxMemory(int maxMemory) {
        maxMemoryForLruCache = maxMemory;
    }

    /**
     * 设置硬盘缓存自定义的文件名
     *
     * @param dirName 自定义文件名
     */
    public static void setDirName(String dirName) {
        dirNameForDiskLruCache = dirName;
    }

    /**
     * 保存 String数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的String数据
     */
    public static void put(String key, String value) {
        put(key, value, STRING);

    }

    /**
     * 保存 JSONObject数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSON数据
     */
    public static void put(String key, JSONObject value) {
        put(key, value, JSON_OBJECT);
    }

    /**
     * 保存 JSONArray数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的JSONArray数据
     */
    public static void put(String key, JSONArray value) {
        put(key, value, JSON_ARRAY);
    }

    /**
     * 保存 byte数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的数据
     */
    public static void put(String key, byte[] value) {
        put(key, value, BYTE_ARRAY);
    }

    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的value
     */
    public static void put(String key, Serializable value) {
        put(key, value, SERIALIZABLE);
    }

    /**
     * 保存 Integer数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的value
     */
    public static void put(String key, Integer value) {
        put(key, value, SERIALIZABLE);
    }

    /**
     * 保存 Integer数据 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的value
     */
    public static void put(String key, Long value) {
        put(key, value, SERIALIZABLE);
    }

    /**
     * 保存 drawable 到 缓存中
     *
     * @param key   保存的key
     * @param value 保存的drawable数据
     */
    public static void put(String key, Drawable value) {
        put(key, value, DRAWABLE);
    }

    /**
     * 索引key对应的bitmap写入缓存
     *
     * @param key   缓存索引
     * @param value bitmap格式数据
     * @param type  数据类型
     */
    public static void put(String key, Object value, int type) {
        switch (model) {
            case ALL_ALLOW:
                if (abLruCache != null && abCache != null) {
                    putDisk(key, value, type);
                    abLruCache.put(key, value);
                }
                break;
            case ONLY_LRU:
                if (abLruCache != null) {
                    abLruCache.put(key, value);
                }
                break;
            case ONLY_DISKLRU:
                if (abCache != null) {
                    putDisk(key, value, type);
                }
                break;
            default:
                break;
        }
    }

    //设置硬盘缓存成功后，再设置内存缓存
    private static void putDisk(String key, Object value, int type) {
        switch (type) {
            case STRING:
                abCache.put(key, (String) value);
                break;
            case BYTE_ARRAY:
                abCache.put(key, (byte[]) value);
                break;
            case SERIALIZABLE:
                abCache.put(key, (Serializable) value);
                break;
            case JSON_OBJECT:
                abCache.put(key, (JSONObject) value);
                break;
            case JSON_ARRAY:
                abCache.put(key, (JSONArray) value);
                break;
            case DRAWABLE:
                abCache.put(key, (Drawable) value);
                break;
            case BITMAP:
                abCache.put(key, (Bitmap) value);
                break;
            default:
                break;
        }
    }

    //设置硬盘缓存成功后，再设置内存缓存
    private static Object getDisk(String key, int type) {
        switch (type) {
            case STRING:
                return abCache.getAsString(key);
            case BYTE_ARRAY:
                return abCache.getAsBinary(key);
            case SERIALIZABLE:
                return abCache.getAsObject(key);
            case JSON_OBJECT:
                return abCache.getAsObject(key);
            case JSON_ARRAY:
                return abCache.getAsJSONArray(key);
            case DRAWABLE:
                return abCache.getAsDrawable(key);
            case BITMAP:
                return abCache.getAsBitmap(key);
            default:
                return null;
        }
    }


    /**
     * 读取 bitmap 数据
     *
     * @param key 键值
     * @return bitmap 数据
     */
    public static Bitmap getBitmap(String key) {
        return get(key, BITMAP);
    }

    /**
     * 读取 Serializable数据
     *
     * @param key 键值
     * @return Serializable 数据
     */
    public static Object getSerializable(String key) {
        return get(key, SERIALIZABLE);
    }

    /**
     * 读取 Integer数据
     *
     * @param key 键值
     * @return Serializable 数据
     */
    public static Integer getInteger(String key, int defaultValue) {
        Object object = get(key, SERIALIZABLE);
        return object == null ? defaultValue : (Integer) object;
    }

    /**
     * 读取 Integer数据
     *
     * @param key 键值
     * @return Serializable 数据
     */
    public static Long getLong(String key, int defaultValue) {
        Object object = get(key, SERIALIZABLE);
        return object == null ? defaultValue : (Long) object;
    }

    /**
     * 读取JSONArray数据
     *
     * @param key 键值
     * @return JSONArray数据
     */
    public static JSONArray getJSONArray(String key) {
        return get(key, JSON_ARRAY);
    }

    /**
     * 获取 byte 数据
     *
     * @param key 键值
     * @return byte 数据
     */
    public static byte[] getByteArry(String key) {
        return get(key, BYTE_ARRAY);
    }

    /**
     * 读取 String数据
     *
     * @param key 键值
     * @return String 数据
     */
    public static String getString(String key) {
        return get(key, STRING);
    }

    /**
     * 读取 Drawable 数据
     *
     * @param key 键值
     * @return Drawable 数据
     */
    public static Drawable getDrawable(String key) {
        return get(key, DRAWABLE);
    }

    /**
     * 获取索引key对应的缓存内容
     *
     * @param key 缓存索引key
     * @return key索引对应的Bitmap数据
     */
    private static <M> M get(String key, int type) {
        Object result = null;
        switch (model) {
            case ALL_ALLOW:
                if (abLruCache != null && abCache != null) {
                    result = abLruCache.get(key);
                    if (result == null) {
                        //如果硬盘缓存内容存在，内存缓存不存在。则在获取硬盘缓存后，将内容写入内存缓存
                        result = getDisk(key, type);
                        if (result != null) {
                            Log.w(TAG, "如果硬盘缓存内容存在，内存缓存不存在。则在获取硬盘缓存后，将内容写入内存缓存");
                            abLruCache.put(key, result);
                        }
                    }
                }
                break;
            case ONLY_LRU:
                if (abLruCache != null) {
                    result = abLruCache.get(key);
                }
                break;
            case ONLY_DISKLRU:
                if (abCache != null) {
                    result = getDisk(key, type);
                }
                break;

            default:
                break;
        }
        //noinspection unchecked
        return (M) result;
    }

    /**
     * 删除所有缓存
     */
    public static void delete() {
        switch (model) {
            case ALL_ALLOW:
                if (abLruCache != null && abCache != null) {
                    abLruCache.deleteAllCache();
                    abCache.clear();
                }
                break;
            case ONLY_LRU:
                if (abLruCache != null) {
                    abLruCache.deleteAllCache();
                }
                break;
            case ONLY_DISKLRU:
                if (abCache != null) {
                    abCache.clear();
                }
                break;

            default:
                break;
        }
    }

    /**
     * 移除一条索引key对应的缓存
     *
     * @param keys 索引
     */
    public static void remove(String... keys) {
        for (String key : keys)
            switch (model) {
                case ALL_ALLOW:
                    if (abLruCache != null && abCache != null) {
                        abLruCache.removeCache(key);
                        abCache.remove(key);
                    }
                    break;
                case ONLY_LRU:
                    if (abLruCache != null) {
                        abLruCache.removeCache(key);
                    }
                    break;
                case ONLY_DISKLRU:
                    if (abCache != null) {
                        abCache.remove(key);
                    }
                    break;

                default:
                    break;
            }
    }

    /**
     * 设置缓存模式
     *
     * @param modelSet ONLY_LRU、ONLY_DISK、ALL_ALLOW
     */
    public static void setCacheModel(int modelSet) {
        model = modelSet;
    }


    /**
     * 获取缓存大小——内存缓存+硬盘缓存
     *
     * @return
     */
    public static int size() {
        int size = 0;
//        if (abCache != null) {
//            size += abCache.size();
//        }
        if (abLruCache != null) {
            size += abLruCache.size();
        }
        return size;
    }

    /**
     * 获取缓存大小
     *
     * @param type 硬盘缓存类型：DISKSIZE、内存缓存类型：MEMORYSIZE
     * @return 对应类型的缓存大小
     */
    public static int size(int type) {
        int size = 0;
        switch (type) {
//            case DISKSIZE:
//                if (abCache != null) {
//                    size += abCache.size();
//                }
//                break;
            case MEMORYSIZE:
                if (abLruCache != null) {
                    size += abLruCache.size();
                }
                break;
            default:
                break;
        }
        return size;
    }
}
