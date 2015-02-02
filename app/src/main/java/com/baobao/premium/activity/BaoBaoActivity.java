package com.baobao.premium.activity;

import android.graphics.Canvas;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.baobao.premium.R;
import com.baobao.premium.fragment.BaoTabHostFragment;
import com.baobao.premium.fragment.MenuListFragment;
import com.baobao.premium.fragment.SampleListFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by gaoxiong on 15-1-30.
 */
public class BaoBaoActivity extends SlidingFragmentActivity {
  private SlidingMenu.CanvasTransformer mTransformer;
  private SlidingMenu sm;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("BaoBao");
    initAnimation();
    initSlidingMenu();
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }

//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//      case android.R.id.home:
//        toggle();
//        return true;
//    }
//    return super.onOptionsItemSelected(item);
//  }

  private void initAnimation() {
    mTransformer = new SlidingMenu.CanvasTransformer() {
      @Override
      public void transformCanvas(Canvas canvas, float percentOpen) {
        float scale = (float) (percentOpen * 0.25 + 0.75);
        canvas.scale(scale, scale, canvas.getWidth() / 2, canvas.getHeight() / 2);
      }
    };
  }

  private void initSlidingMenu() {
    setContentView(R.layout.aa_content_frame);
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    ft.replace(R.id.content_frame, new BaoTabHostFragment());
    ft.commit();

    ft = fm.beginTransaction();
    setBehindContentView(R.layout.aa_menu_frame);
    MenuListFragment menuFragment = new MenuListFragment();
    ft.replace(R.id.menu_frame, menuFragment);
    ft.commit();

    sm = getSlidingMenu();
    sm.setMode(SlidingMenu.LEFT_RIGHT);
    sm.setShadowWidthRes(R.dimen.shadow_width);
    sm.setShadowDrawable(R.drawable.shadow);
    sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
    sm.setFadeDegree(0.35f);
    sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
    sm.setBehindScrollScale(0.0f);
    sm.setBehindCanvasTransformer(mTransformer);

    ft = fm.beginTransaction();
    sm.setSecondaryMenu(R.layout.aa_menu_frame);
    sm.setSecondaryShadowDrawable(R.drawable.shadow);
    MenuListFragment menuListFragment2 = new MenuListFragment();
    ft.replace(R.id.menu_frame, menuListFragment2);
    ft.commit();

    setSlidingActionBarEnabled(true);
  }

  public void closeSlidingMenu() {
    sm.toggle();
  }
}
