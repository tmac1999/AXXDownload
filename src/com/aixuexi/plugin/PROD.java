package com.aixuexi.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.apache.http.util.TextUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class PROD {
    static String prodURL = "http://192.168.0.228:8080/mockserver/aixuexiapp/res/teacher/prod.zip";
    static String reactURL = "http://192.168.0.228:8080/mockserver/aixuexiapp/res/teacher/react.zip";
    static String pathProd = "/app/src/main/assets/prod.zip";
    static String pathReact = "/app/src/main/assets/react.zip";
    static StringBuffer sb = null;

    private static void printBeforeDownloadVersion(Project project, ArrayList<ResInfo> infos) {
        try {
            print("prod.zip======");
            readZipFileText(project.getBasePath() + pathProd);
            print("react.zip======Version");
            readZipFileText(project.getBasePath() + pathReact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getVersion(Project project, ArrayList<ResInfo> infos) {
        try {
            if (sb == null) sb = new StringBuffer();
            for (ResInfo info : infos) {
                if (isValidPath(info)) {
                    String[] split = info.resPath.split("/");
                    sb.append(split[split.length - 1]).append(":");
                    readZipFileText(project.getBasePath() + info.resPath);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getSB() {
        if (sb == null) return null;
        return sb.toString();
    }

    public static String downloadRes(ResInfo info) {
        if (sb == null) sb = new StringBuffer();
        try {
            if (isValidPath(info) && isValidUrl(info)) {
                sb.append("---------------下载后的版本-------------------\r\n");
                UrlHttpUtil.downloadRes(info.resUrl, Global.instance().project().getBasePath() +info.resPath);
                String[] split = info.resPath.split("/");
                sb.append(split[split.length - 1]).append(":");
                readZipFileText(Global.instance().project().getBasePath() + info.resPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static boolean isValidPath(ResInfo info) {
        return !TextUtils.isEmpty(info.resPath) && info.resPath.endsWith(".zip");
    }

    private static boolean isValidUrl(ResInfo info) {
        return !TextUtils.isEmpty(info.resPath) && info.resUrl.endsWith(".zip");
    }

    public static void print(String s) {
        System.out.print(s);
        sb.append(s);
    }

    public static void println(String s) {
        System.out.println(s);
        sb.append(s + "\r\n");
    }

    private static void printAfterDownloadVersion(Project project) {
        try {
            println("============更新了资源包之后============");
            print("prod.zip======");
            readZipFileText(project.getBasePath() + pathProd);
            print("react.zip======Version");
            readZipFileText(project.getBasePath() + pathReact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readZipFileText(String path) throws IOException {
//        path = "F:\\*******\\201707\\78641695079026649.zip";
        ZipFile zf = new ZipFile(path);
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        Charset gbk = Charset.forName("gbk");
        ZipInputStream zin = new ZipInputStream(in, gbk);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.toString().endsWith(".json")) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(zf.getInputStream(ze)));
                String line;
                while ((line = br.readLine()) != null) {
                    println(line);
                }
                br.close();
            }
        }
        zin.closeEntry();
    }

    public static void newSB() {
        sb = new StringBuffer();
    }
}
