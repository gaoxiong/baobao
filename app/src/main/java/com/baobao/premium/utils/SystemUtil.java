package com.baobao.premium.utils;

import android.os.Build;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class SystemUtil {
  public static boolean aboveApiLevel(int sdkInt) {
    return getApiLevel() >= sdkInt;
  }

  public static int getApiLevel() {
    return Build.VERSION.SDK_INT;
  }
}
