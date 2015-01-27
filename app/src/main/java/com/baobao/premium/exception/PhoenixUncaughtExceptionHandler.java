package com.baobao.premium.exception;

import android.content.Context;

import com.baobao.premium.config.Config;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class PhoenixUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

  private final Thread.UncaughtExceptionHandler defaultUEH;
  private final Context context;

  public PhoenixUncaughtExceptionHandler(Context context) {
    this.context = context;
    this.defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
  }

  @Override
  public void uncaughtException(Thread thread, Throwable ex) {
    saveCrashLog(ex);
    this.defaultUEH.uncaughtException(thread, ex);
  }

  private void saveCrashLog(Throwable ex) {
    if (ex != null) {
      try {
        String dir = Config.getExternalContentDirectory(Config.ContentDir.DIAGNOSIS);
        PrintStream stream = new PrintStream(dir + "last_crash_log.txt");
        ex.printStackTrace(stream);
        stream.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
