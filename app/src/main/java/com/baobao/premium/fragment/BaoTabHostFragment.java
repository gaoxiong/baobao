package com.baobao.premium.fragment;

import com.baobao.premium.R;
import com.baobao.premium.application.BaoApplication;
import com.baobao.premium.view.TaggedTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoxiong on 15-1-26.
 */
public class BaoTabHostFragment extends DefaultTabHostFragment {
  private static final int DEFAULT_TAB_POSITION = 1;

  public static final String TAB_CATEGORY = "category";
  public static final String TAB_POPULAR = "popular";
  public static final String TAB_SPECIAL = "special";
  public static final String TAB_YOUTUBE = "youtube";
  public static final String TAB_TOPVIDEO = "topvideo";
  public static final String TAB_MUSICVIDEO = "musicvideo";
  List<String> default_tab_config = new ArrayList<String>();
  List<String> tab_config;
  List<String> tabs = new ArrayList<String>();

  public BaoTabHostFragment() {
    setDefaultTabPosition(DEFAULT_TAB_POSITION);
  }

  @Override
  public List<TabFragmentDelegate> getTabFragmentDelegates() {
    List<TabFragmentDelegate> delegates = new ArrayList<TabFragmentDelegate>();
    default_tab_config.add(TAB_CATEGORY);
    default_tab_config.add(TAB_CATEGORY);
    default_tab_config.add(TAB_CATEGORY);
    default_tab_config.add(TAB_CATEGORY);
    tab_config = default_tab_config;

    if (tab_config.contains(TAB_CATEGORY)) {
      // category
      TaggedTab categoryTab = new TaggedTab(BaoApplication.getAppContext()
        .getString(R.string.tab_category));
      TabFragmentDelegate categoryDelegate =
        new TabFragmentDelegate(categoryTab, BaoCategoryFragment.class, null);
      delegates.add(categoryDelegate);
      tabs.add(TAB_CATEGORY);
    }
    return delegates;
  }
}
