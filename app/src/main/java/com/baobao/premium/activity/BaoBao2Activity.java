package com.baobao.premium.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.baobao.premium.R;
import com.baobao.premium.fragment.MenuListAdapter;

/**
 * Created by gaoxiong on 15-1-28.
 */
public class BaoBao2Activity extends SherlockFragmentActivity {
  private CharSequence mTitle;
  private CharSequence mDrawerTitle;
  ActionBarDrawerToggle mDrawerToggle;
  DrawerLayout mDrawerLayout;
  ListView mDrawerList;
  MenuListAdapter mMentAdapter;
  String[] title;
  String[] subtitle;
  int[] icon;
//  Fragment fragment1 = new Fragment1();
//  Fragment fragment2 = new Fragment2();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_main);

    init(savedInstanceState);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add("Help")
      .setOnMenuItemClickListener(this.HelpButtonClickListener)
      .setIcon(R.drawable.btn_help)
      .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    menu.add("Like")
      .setOnMenuItemClickListener(this.LikeButtonClickListener)
      .setIcon(R.drawable.btn_like)
      .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    menu.add("Exit")
      .setOnMenuItemClickListener(this.ExitButtonClickListener)
      .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
        mDrawerLayout.closeDrawer(mDrawerList);
      } else {
        mDrawerLayout.openDrawer(mDrawerList);
      }
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void setTitle(CharSequence title) {
    mTitle = title;
    getSupportActionBar().setTitle(title);
  }

  @Override
  public void onBackPressed() {
    FragmentManager fm = getSupportFragmentManager();
    if (fm.getBackStackEntryCount() > 0) {
      fm.popBackStack();
    } else {
      super.onBackPressed();
    }
  }

  MenuItem.OnMenuItemClickListener HelpButtonClickListener = new MenuItem.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
      Toast.makeText(BaoBao2Activity.this, "Help Button", Toast.LENGTH_SHORT).show();
      return false;
    }
  };

  MenuItem.OnMenuItemClickListener LikeButtonClickListener = new MenuItem.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
      Toast.makeText(BaoBao2Activity.this, "Like Button", Toast.LENGTH_SHORT).show();
      return false;
    }
  };

  MenuItem.OnMenuItemClickListener ExitButtonClickListener = new MenuItem.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
      Toast.makeText(BaoBao2Activity.this, "Exit Button", Toast.LENGTH_SHORT).show();
      return false;
    }
  };

  private void init(Bundle savedInstanceState) {
    mTitle = mDrawerTitle = getTitle();
    title = new String[] { "Title Fragment 1", "Title Fragment 2" };
    subtitle = new String[] { "Subtitle Fragment 1", "Subtitle fragment 2" };
    icon = new int[] { R.drawable.ic_launcher, R.drawable.ic_launcher };

    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mDrawerList = (ListView) findViewById(R.id.listview_drawer_left);

    mDrawerLayout.setDrawerShadow(R.drawable.ic_launcher, GravityCompat.START);
    mMentAdapter = new MenuListAdapter(BaoBao2Activity.this, title, subtitle, icon);
    mDrawerList.setAdapter(mMentAdapter);
    mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
      R.drawable.ic_launcher, R.string.drawer_open, R.string.drawer_close) {
      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
      }

      public void onDrawerOpened(View drawerView) {
        getSupportActionBar().setTitle(mDrawerTitle);
        super.onDrawerOpened(drawerView);
      }
    };
    mDrawerLayout.setDrawerListener(mDrawerToggle);

    if (savedInstanceState == null) {
      selectItem(0);
    }
  }

  private void selectItem(int position) {
//    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//    switch (position) {
//      case 0:
//        ft.replace(R.id.content_frame, fragment1);
//        break;
//      case 1:
//        ft.replace(R.id.content_frame, fragment2);
//        break;
//    }
//    ft.commit();
    mDrawerList.setItemChecked(position, true);
    setTitle(title[position]);
    mDrawerLayout.closeDrawer(mDrawerList);
  }

  private class DrawerItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      selectItem(i);
    }
  }
}
