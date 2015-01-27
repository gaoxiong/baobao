package com.baobao.premium.application;

import android.app.Application;
import android.content.Context;

import com.baobao.premium.exception.PhoenixUncaughtExceptionHandler;
import com.baobao.premium.utils.GeneralUtil;

/**
 * Created by gaoxiong on 15-1-26.
 */
public class BaoApplication extends Application {
  private static Context context;
  private boolean isMainProcess = true;

  @Override
  public void onCreate() {
    super.onCreate();
    init();
  }

  private void init() {
    context = this;
    String processName = GeneralUtil.getProcessName(context);
    isMainProcess = false;
    if (GeneralUtil.isBaoProcess(processName)) {
      isMainProcess = true;
      initMainProcess();
    }
  }

  public static Context getAppContext() {
    return context;
  }

  private void initMainProcess() {
    Thread.setDefaultUncaughtExceptionHandler(new PhoenixUncaughtExceptionHandler(
      getApplicationContext()));
  }
}
