package com.adutils.file;

import android.support.annotation.NonNull;
import android.support.v4.util.LruCache;

import java.lang.ref.SoftReference;

public final class ABLruCache {
    private static ABLruCache abCachePoolUtil = new ABLruCache();

    public static ABLruCache getInstance() {
        return abCachePoolUtil;
    }

    private static LruCache<String, SoftReference> lruCache;

    public ABLruCache() {
        this((int) Runtime.getRuntime().maxMemory() / 1024 / 8);
    }

    //设置自定义大小的LruCache
    public ABLruCache(int maxSize) {
        lruCache = new LruCache<String, SoftReference>(maxSize * 1024) {
            @Override
            protected int sizeOf(String key, SoftReference value) {
                return super.sizeOf(key, value);
            }
        };
    }

    public void put(String key, Object value) {
        //noinspection unchecked
        lruCache.put(key, new SoftReference(value));
    }


    public Object get(String key) {
        SoftReference softReference = lruCache.get(key);
        return softReference == null ? null : lruCache.get(key).get();
    }

    public void deleteAllCache() {
        if (lruCache != null)
            lruCache.evictAll();
    }

    public void removeCache(@NonNull String... keys) {
        if (lruCache != null)
            for (String k : keys) {
                lruCache.remove(k);
            }
    }

    public int size() {
        int size = 0;
        if (lruCache != null)
            size += lruCache.size();
        return size;
    }
}
