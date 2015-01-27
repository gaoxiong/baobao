package com.baobao.premium.activity;

import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

/**
 * Created by gaoxiong on 15-1-21.
 */
public abstract class BaseActivity extends SherlockFragmentActivity {

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return onMenuItemSelected(item);
  }

  public boolean onMenuItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        break;
    }
    return false;
  }
}
