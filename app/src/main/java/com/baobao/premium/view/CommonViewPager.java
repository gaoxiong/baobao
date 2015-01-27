package com.baobao.premium.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class CommonViewPager extends ViewPager {
  private Context context;

  public CommonViewPager(Context context) {
    super(context);
    init(context);
  }

  public CommonViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    this.context = context;
  }
}
