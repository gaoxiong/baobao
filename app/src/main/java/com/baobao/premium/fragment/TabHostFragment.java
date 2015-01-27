package com.baobao.premium.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.baobao.premium.Interface.IReferenceAcceptor;
import com.baobao.premium.R;
import com.baobao.premium.extensions.PagerSlidingTabStrip;
import com.baobao.premium.view.CommonViewPager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gaoxiong on 15-1-27.
 */
public abstract class TabHostFragment extends SherlockFragment implements IReferenceAcceptor {
  private View contentView;
  private PagerSlidingTabStrip tabStrip;
  private CommonViewPager viewPager;
  private TabFragmentPagerAdapter pagerAdapter;
  private int currentFragmentIndex;
  private Set cachedReferenceSet = new HashSet();

  @Override
  public void onReferenceAccepted(Object target) {
    cachedReferenceSet.add(target);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    contentView = inflater.inflate(getLayoutResId(), container, false);
    return contentView;
  }

  @Override
  public void onDestroy() {
    cachedReferenceSet.clear();
    super.onDestroy();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    tabStrip = (PagerSlidingTabStrip) contentView.findViewById(R.id.tabs);
    viewPager = (CommonViewPager) contentView.findViewById(R.id.common_view_pager);
    pagerAdapter = new TabFragmentPagerAdapter(getActivity(), getChildFragmentManager());
    pagerAdapter.setFragments(getTabFragmentDelegates());
    viewPager.setAdapter(pagerAdapter);
    currentFragmentIndex = getDefaultTabPosition();
    viewPager.setCurrentItem(getDefaultTabPosition());
    tabStrip.setViewPager(viewPager);
  }

  public abstract List<TabFragmentDelegate> getTabFragmentDelegates();

  protected int getLayoutResId() {
    return R.layout.aa_common_tab_layout;
  }

  protected int getDefaultTabPosition() {
    return 0;
  }
}
