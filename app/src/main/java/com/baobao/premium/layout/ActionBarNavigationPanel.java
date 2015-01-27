package com.baobao.premium.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.baobao.premium.R;

/**
 * Created by gaoxiong on 15-1-26.
 */
public class ActionBarNavigationPanel extends LinearLayout {
  private final LayoutInflater layoutInflater;

  public ActionBarNavigationPanel(Context context) {
    super(context);
    this.layoutInflater = LayoutInflater.from(context);
  }

  public ActionBarNavigationPanel(Context context, AttributeSet attrs, LayoutInflater layoutInflater) {
    super(context, attrs);
    this.layoutInflater = layoutInflater;
  }

  public ActionBarNavigationPanel(Context context, AttributeSet attrs, int defStyle, LayoutInflater layoutInflater) {
    super(context, attrs, defStyle);
    this.layoutInflater = layoutInflater;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    initSearchView();
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  private void initSearchView() {
    View searchView = layoutInflater.inflate(
      R.layout.bao_search_actionbar, this, false);
    addView(searchView);
  }
}
