package com.adutils.phone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 项目名称：UsbHostDemo
 * 类描述：
 * 创建人：Michael-hj
 * 创建时间：2016/5/10 0010 9:43
 * 修改人：Michael-hj
 * 修改时间：2016/5/10 0010 9:43
 * 修改备注：
 */
public class ABDimensUtils_create {
    /**
     * 源文件
     */
    static String oldFilePath = "/res/values-nodpi/";
    /**
     * 新生成文件路径
     */
    static String filePath720 = "/res/values-1280x720/";
    /**
     * 新生成文件路径
     */
    static String filePath672 = "/res/values-1280x672/";
    /**
     * 新生成文件路径
     */
    static String filePath1080 = "/res/values-1920x1080/";
    /**
     * 缩小倍数
     */
    static float changes = 1.5f;

    public static void main(String[] args) {
        String relativelyPath = System.getProperty("user.dir");
        //生成1-1920px
        String allPx = getAllPx();
        DeleteFolder(relativelyPath + "/app/src/main/" + oldFilePath);

        writeFile(relativelyPath + "/app/src/main/" + oldFilePath, "dimens.xml", allPx);
        String st = convertStreamToString(relativelyPath + "/app/src/main/" + oldFilePath + "dimens.xml", changes);

        DeleteFolder(relativelyPath + "/app/src/main/" + filePath720);
        writeFile(relativelyPath + "/app/src/main/" + filePath720, "dimens.xml", st);

        DeleteFolder(relativelyPath + "/app/src/main/" + filePath672);
        writeFile(relativelyPath + "/app/src/main/" + filePath672, "dimens.xml", st);

        String st1 = convertStreamToString(relativelyPath + "/app/src/main/" + oldFilePath + "dimens.xml", 1f);
        DeleteFolder(relativelyPath + "/app/src/main/" + filePath1080);
        writeFile(relativelyPath + "/app/src/main/" + filePath1080, "dimens.xml", st1);
    }

    /**
     * 读取文件 生成缩放后字符串
     */
    public static String convertStreamToString(String filepath, float f) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filepath));
            String line = null;
            System.out.println("q1");
            String endmark = "px</dimen>";
            String startmark = ">";
            while ((line = bf.readLine()) != null) {
                if (line.contains(endmark)) {
                    int end = line.lastIndexOf(endmark);
                    int start = line.indexOf(startmark);
                    String stpx = line.substring(start + 1, end);
                    int px = Integer.parseInt(stpx);
                    int newpx = (int) ((float) px / f);
                    String newline = line.replace(px + "px", newpx + "px");
                    sb.append(newline + "\r\n");
                } else {
                    sb.append(line + "\r\n");
                }
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 生成全px文件
     */
    public static String getAllPx() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("<resources>" + "\r\n");
            sb.append("<dimen name=\"screen_width\">1920px</dimen>" + "\r\n");
            sb.append("<dimen name=\"screen_height\">1080px</dimen>" + "\r\n");
            for (int i = 1; i <= 1920; i++) {
                System.out.println("i=" + i);
                sb.append("<dimen name=\"px" + i + "\">" + i + "px</dimen>"
                        + "\r\n");
            }
            sb.append("</resources>" + "\r\n");
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 存为新文件
     */
    public static void writeFile(String filepath, String fileName, String st) {
        File file = getFile(filepath, fileName);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(st);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(String filePath, String fileName) {
        //path表示你所创建文件的路径
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        // fileName表示你创建的文件名；为txt类型；
        File file = new File(f, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     *
     * @param sPath 要删除的目录或文件
     * @return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        File file = new File(sPath);
        // // 判断目录或文件是否存在
        if (!file.exists()) { // 不存在返回 false
            return true;
        } else {
            // 判断是否为文件
            if (file.isFile()) { // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else { // 为目录时调用删除目录方法
//                 return deleteDirectory(sPath);
            }
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

}
