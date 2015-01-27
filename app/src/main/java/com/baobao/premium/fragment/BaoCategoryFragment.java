package com.baobao.premium.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.baobao.premium.R;

/**
 * Created by gaoxiong on 15-1-27.
 */
public class BaoCategoryFragment extends SherlockFragment {
  private View contentView;

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    if (contentView == null) {
      contentView = inflater.inflate(getLayoutResId(), container, false);
    } else {
      ViewGroup parent = (ViewGroup) contentView.getParent();
      if (parent != null) {
        parent.removeView(contentView);
      }
    }
    return contentView;
  }

  protected int getLayoutResId() {
    return R.layout.aa_test_layout;
  }
}
