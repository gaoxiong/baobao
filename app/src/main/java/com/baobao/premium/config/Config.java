package com.baobao.premium.config;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class Config {

  public enum ContentDir {
    APP, MUSIC, VIDEO, IMAGE, BOOK, BACKUP, DIAGNOSIS, EXPORT, CONFIG, MD5, DATA,
    CLIENT, CAPTURE, PHOTOSYNC, MISC, MARIO
  }
  private static final String[] CONTENT_DIRS = new String[]{"music",
    "video", "diagnosis", "export",
    ".config", ".md5", "data", ".client", "capture", "misc"};

  public static String ROOT_DIR = "baobao";

  public static String getRootDirectory() {
    try {
      if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
        return null;
      }
    } catch (Exception e) {
      // Catch exception is trying to fix a crash inside of Environment.getExternalStorageState().
      e.printStackTrace();
      return null;
    }
    String rootDir = Environment.getExternalStorageDirectory()
      .getAbsolutePath()
      + "/" + ROOT_DIR + "/";
    File file = new File(rootDir);
    if (!file.exists()) {
      if (!file.mkdirs()) {
        return null;
      }
    }
    return rootDir;
  }

  public static String getExternalContentDirectory(ContentDir contentDir) {
    String rootDir = getRootDirectory();
    if (!TextUtils.isEmpty(rootDir)) {
      String content = rootDir + CONTENT_DIRS[contentDir.ordinal()] + "/";

      File contentFile = new File(content);
      if (!contentFile.exists()) {
        if (!contentFile.mkdirs()) {
          return null;
        }
      }

      return content;
    } else {
      return null;
    }
  }
}
