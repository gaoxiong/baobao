package com.baobao.premium.activity;

import android.app.Activity;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.baobao.premium.R;
import com.baobao.premium.utils.ViewUtils;
import com.baobao.premium.view.BaoHorizontalScrollView;


public class MainActivity extends SherlockFragmentActivity {
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
    }
}
