package com.baobao.premium.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.baobao.premium.extensions.PagerSlidingTabStrip;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class TabFragmentDelegate {
  private Bundle args;
  private PagerSlidingTabStrip.Tab tab;
  private Class<? extends Fragment> fragmentClaz;

  public Bundle getArgs() {
    return args;
  }
  public PagerSlidingTabStrip.Tab getTab() {
    return tab;
  }

  public TabFragmentDelegate(PagerSlidingTabStrip.Tab tab,
                             Class<? extends Fragment> fragmentClaz, Bundle args) {
    this.tab = tab;
    this.fragmentClaz = fragmentClaz;
    this.args = args;
  }

  public Class<? extends Fragment> getFragmentClaz() {
    return fragmentClaz;
  }
}
