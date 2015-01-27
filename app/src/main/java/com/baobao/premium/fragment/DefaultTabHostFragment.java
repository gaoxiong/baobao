package com.baobao.premium.fragment;

/**
 * Created by gaoxiong on 15-1-27.
 */
public abstract class DefaultTabHostFragment extends TabHostFragment {
  private int defaultTabPosition = 0;

  @Override
  final protected int getDefaultTabPosition() {
    return defaultTabPosition;
  }

  public void setDefaultTabPosition(int position) {
    this.defaultTabPosition = position;
  }
}
