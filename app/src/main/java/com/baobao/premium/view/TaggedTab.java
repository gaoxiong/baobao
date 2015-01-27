package com.baobao.premium.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.baobao.premium.extensions.PagerSlidingTabStrip;
import com.baobao.premium.utils.ViewUtils;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class TaggedTab extends PagerSlidingTabStrip.Tab {
  public TaggedTab(CharSequence text) {
    super(text);
  }

  public TaggedTab(View customView) {
    super(customView);
  }

  @Override
  public View buildTabView(Context context, final int position, final ViewPager pager) {
    View view = super.buildTabView(context, position, pager);
    view.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (v.isSelected()) {
          ViewUtils.scrollToTop(v.getContext());
        } else {
          pager.setCurrentItem(position, false);
        }
      }
    });
    return view;
  }
}
