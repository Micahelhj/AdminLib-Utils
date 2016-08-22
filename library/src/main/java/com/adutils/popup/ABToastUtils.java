package com.adutils.popup;

import android.content.Context;
import android.widget.Toast;
/**
 * 项目名称：AdminLibs
 * 类描述：ABToastUtils
 * 创建时间：2016/2/4 14:45
 * 修改人：Michael
 * 修改时间：2016/2/4 14:45
 * 修改备注：
 */
public class ABToastUtils {
    private static Toast toast = null; //Toast的对象！

    public static void showToast(Context mContext, String id) {
        if (toast == null) {
            toast = Toast.makeText(mContext, id, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(id);
        }
        toast.show();
    }
}
