package com.adutils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

/**
 * 项目名称：AdminLibs
 * 类描述：Intent工具类（调用系统功能）
 * 创建人：Michael
 * 创建时间：2016/2/4 14:45
 * 修改人：Michael
 * 修改时间：2016/2/4 14:45
 * 修改备注：
 */
public final class ABIntentUtils {

    /**
     * @param context
     * @param cls
     * @description 启动Service
     * @date 2015年8月26日
     */
    public static void startService(Context context, Class<? extends Service> cls) {
        startService(context, cls, null);
    }

    /**
     * @param context
     * @param cls
     * @param bundle  参数
     * @description 启动Service
     * @date 2015年8月26日
     */
    public static void startService(Context context, Class<? extends Service> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startService(intent);
    }

    /**
     * @param context
     * @description
     * @date 2015年12月9日
     */
    public static void startAppMarket(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param cls
     * @description 启动Activity
     * @date 2015年8月26日
     */
    public static void startActivity(Context context, Class<? extends Activity> cls) {
        startActivity(context, cls, null);
    }

    /**
     * @throws
     * @Description:
     */
    public static void startActivity(Context context, Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * @param activity
     * @param cls         目标activity
     * @param bundle      参数
     * @param requestCode 请求码
     * @throws
     * @Description: 带返回值启动Activity
     */
    public static void startActivityForResult(Activity activity,
                                              Class<? extends Activity> cls,
                                              Bundle bundle,
                                              int requestCode) {
        Intent intent = new Intent(activity, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * @param activity
     * @param cls         目标activity
     * @param requestCode 请求码
     * @throws
     * @Description: 带返回值启动Activity
     */
    public static void startActivityForResult(Activity activity,
                                              Class<? extends Activity> cls,
                                              int requestCode) {
        startActivityForResult(activity, cls, null, requestCode);
    }

    /**
     * @param bundle 返回参数
     * @description startAtivityForResult返回结果
     * @date 2015年8月26日
     */
    public static void setResult(Activity activity, Bundle bundle) {
        Intent localIntent = new Intent();
        if (null != bundle) {
            localIntent.putExtras(bundle);
        }
        activity.setResult(Activity.RESULT_OK, localIntent);
        activity.finish();
    }

    /**
     * @param context  上下文
     * @param phoneNum 电话号码
     * @description 拨打电话
     * @date 2015年9月6日
     */
    public static void call(Context context, String phoneNum) {
        Uri uri = Uri.parse("tel:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @param context 上下文
     * @param message 消息内容
     * @description 进入发短信界面
     * @date 2015年9月6日
     */
    public static void sendSMS(Context context, String message) {
        Uri uri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", message);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * @param context 上下文
     * @description 跳转到网络设置
     * @date 2015年9月6日
     */
    public static void settingNetwork(Context context) {
        Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
        context.startActivity(intent);
    }

    /**
     * @param context 上下文
     * @description 打开GPS
     * @date 2015年9月6日
     */
    public static void openGPS(Context context) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
