package com.adutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * 项目名称：AdminLibs
 * 类描述：ABManifestUtil
 * 创建人：Michael
 * 创建时间：2014/12/15 11:52
 * 修改人：Michael
 * 修改时间：2015/12/15 9:40
 * 修改备注：
 */
@SuppressWarnings("ALL")
public class ABManifestUtil {
    /**
     * 从Meta中获取值
     *
     * @param context 上下文
     * @param metaKey 键
     * @return 值
     */
    public static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        String metaValue= null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                metaValue = metaData.getString(metaKey);
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return metaValue;
    }
}
