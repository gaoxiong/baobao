package com.baobao.premium.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class GeneralUtil {
  private static final String BAOBAO_PREFIX = "com.baobao.premium";

  public static String getProcessName(Context context) {
    int myPid = android.os.Process.myPid();
    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningAppProcessInfo> processes = manager.getRunningAppProcesses();
    for (ActivityManager.RunningAppProcessInfo process : processes) {
      if (process.pid == myPid) {
        return process.processName;
      }
    }
    return "";
  }

  public static boolean isBaoProcess(String packageName) {
    return (packageName != null && packageName.startsWith(BAOBAO_PREFIX));
  }
}
