package com.baobao.premium.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.baobao.premium.extensions.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class TabFragmentPagerAdapter extends PagerAdapter implements
  PagerSlidingTabStrip.TabProvider {
  Context context;
  FragmentManager fragmentManager;
  private Fragment currentPrimaryItem = null;
  private FragmentTransaction curTransaction = null;
  private SparseArray<Fragment> fragments = new SparseArray<Fragment>();
  private final List<TabFragmentDelegate> tabFragmentDelegates = new ArrayList<TabFragmentDelegate>();
  private SparseArray<Bundle> fragmentArgs = new SparseArray<Bundle>();
  private SparseArray<Fragment.SavedState> savedStates = new SparseArray<Fragment.SavedState>();
  private boolean allowLoading = true;

  public TabFragmentPagerAdapter(SparseArray<Bundle> fragmentArgs) {
    this.fragmentArgs = fragmentArgs;
  }

  public TabFragmentPagerAdapter(Context context, FragmentManager fm) {
    this.fragmentManager = fm;
    this.context = context;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return ((Fragment) object).getView() == view;
  }

  @Override
  public PagerSlidingTabStrip.Tab getTab(int position) {
    if (tabFragmentDelegates == null || tabFragmentDelegates.isEmpty()) {
      return null;
    }
    if (position < 0 || position >= tabFragmentDelegates.size()) {
      return null;
    }
    return tabFragmentDelegates.get(position).getTab();
  }

  @Override
  public int getCount() {
    return tabFragmentDelegates.size();
  }

  public void setFragments(List<TabFragmentDelegate> delegates) {
    this.tabFragmentDelegates.clear();
    appendFragments(delegates);
  }

  @Override
  public void setPrimaryItem(ViewGroup container, int position, Object object) {
    Fragment fragment = (Fragment) object;
    if (fragment != currentPrimaryItem) {
      if (currentPrimaryItem != null) {
        currentPrimaryItem.setMenuVisibility(false);
        currentPrimaryItem.setUserVisibleHint(false);
      }
      if (fragment != null) {
        fragment.setMenuVisibility(true);
        fragment.setUserVisibleHint(true);
      }
      currentPrimaryItem = fragment;
    }
  }

  @Override
  public Fragment instantiateItem(ViewGroup container, int position) {
    // If we already have this item instantiated, there is nothing
    // to do. This can happen when we are restoring the entire pager
    // from its saved state, where the fragment manager has already
    // taken care of restoring the fragments we previously had instantiated.
    Fragment fragment = fragments.get(position);
    if (fragment != null) {
      return fragment;
    }

    if (curTransaction == null) {
      curTransaction = fragmentManager.beginTransaction();
    }

    fragment = newItem(position);
    Fragment.SavedState savedState = savedStates.get(position);
    if (savedState != null) {
      fragment.setInitialSavedState(savedState);
    }
    fragment.setMenuVisibility(false);
    fragment.setUserVisibleHint(false);
    fragments.put(position, fragment);
    curTransaction.add(container.getId(), fragment);

    return fragment;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    Fragment fragment = (Fragment) object;

    if (curTransaction == null) {
      curTransaction = fragmentManager.beginTransaction();
    }
    //savedStates.put(position, fragmentManager.saveFragmentInstanceState(fragment));
    fragments.remove(position);

    curTransaction.remove(fragment);
  }

  public void appendFragments(List<TabFragmentDelegate> delegates) {
    if (delegates == null) {
      throw new RuntimeException("delegates should not be null for setFragments()");
    }
    int oldSize = tabFragmentDelegates.size();
    int newSize = oldSize + delegates.size();
    for (int i = oldSize; i < newSize; ++i) {
      TabFragmentDelegate delegate = delegates.get(i - oldSize);
      fragmentArgs.put(i, delegate.getArgs());
    }
    tabFragmentDelegates.addAll(delegates);
    notifyDataSetChanged();
  }

  public Fragment getCurrentFragment() {
    return currentPrimaryItem;
  }

  private Fragment newItem(int position) {
    Fragment fragment =
      Fragment.instantiate(context,
        tabFragmentDelegates.get(position).getFragmentClaz().getName(),
        fragmentArgs.get(position));
    return fragment;
  }
}
