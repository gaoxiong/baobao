package com.baobao.premium.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.baobao.premium.R;
import com.baobao.premium.fragment.BaoTabHostFragment;
import com.baobao.premium.layout.ActionBarNavigationPanel;
import com.baobao.premium.utils.ViewUtils;
import com.baobao.premium.view.BaoHorizontalScrollView;


public class MainActivity extends BaseActivity {
  private ActionBarNavigationPanel navigationPanel;
  BaoHorizontalScrollView scrollView;
  View menu;
  View app;
  Button btnSlide;
  boolean menuOut = false;
  Handler handler = new Handler();
  int btnWidth;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    LayoutInflater inflater = LayoutInflater.from(this);
    setContentView(R.layout.activity_main);

    scrollView = (BaoHorizontalScrollView) findViewById(R.id.myScrollView);
    menu = findViewById(R.id.menu);
    app = inflater.inflate(R.layout.horz_scroll_app, null);
    ViewGroup tabBar = (ViewGroup) app.findViewById(R.id.tabBar);

    ListView listView = (ListView) app.findViewById(R.id.list);
    ViewUtils.initListView(this, listView, "Item ", 30,
      android.R.layout.simple_list_item_1);

    btnSlide = (Button) tabBar.findViewById(R.id.BtnSlide);
    btnSlide.setOnClickListener(new HorzScrollWithListMenu.ClickListenerForScrolling(
      scrollView, menu));

    // Create a transparent view that pushes the other views in the HSV to
    // the right.
    // This transparent view allows the menu to be shown when the HSV is
    // scrolled.
    View transparent = new TextView(this);
    transparent.setBackgroundColor(android.R.color.transparent);

    final View[] children = new View[] { transparent, app };

    // Scroll to app (view[1]) when layout finished.
    int scrollToViewIdx = 1;
    scrollView.initViews(children, scrollToViewIdx,
      new HorzScrollWithListMenu.SizeCallbackForMenu(btnSlide));

    ActionBar actionBar = getSupportActionBar();
    navigationPanel = new ActionBarNavigationPanel(this);
    ActionBar.LayoutParams params = new ActionBar.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
    );
    actionBar.setCustomView(navigationPanel);
    handleIntent(getIntent());
  }

  private void handleIntent(Intent intent) {
    if (intent == null) {
      return;
    }
    Bundle bundle = null;
    //bundle.putString();
    navigateToItemInternal(bundle);
  }

  private void navigateToItemInternal(Bundle bundle) {
    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();
    Fragment currentFragment = fm.findFragmentById(android.R.id.content);
    if (currentFragment instanceof BaoTabHostFragment) {
      return;
    } else {
      BaoTabHostFragment baoFragment = new BaoTabHostFragment();
      ft.replace(android.R.id.content, baoFragment);
    }
    ft.commitAllowingStateLoss();
  }
}
