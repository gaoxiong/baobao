package com.baobao.premium.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.baobao.premium.R;
import com.baobao.premium.fragment.TabFragmentPagerAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Utility methods for Views.
 */
public class ViewUtils {

  private static final int MAX_SMOOTH_SCROLL_POSITION = 5;

	public static void setViewWidths(View view, View[] views) {
		int w = view.getWidth();
		int h = view.getHeight();
		for (int i = 0; i < views.length; i++) {
			View v = views[i];
			v.layout((i + 1) * w, 0, (i + 2) * w, h);
			printView("view[" + i + "]", v);
		}
	}

	public static void printView(String msg, View v) {
		System.out.println(msg + "=" + v);
		if (null == v) {
			return;
		}
		System.out.print("[" + v.getLeft());
		System.out.print(", " + v.getTop());
		System.out.print(", w=" + v.getWidth());
		System.out.println(", h=" + v.getHeight() + "]");
		System.out.println("mw=" + v.getMeasuredWidth() + ", mh="
				+ v.getMeasuredHeight());
		System.out.println("scroll [" + v.getScrollX() + "," + v.getScrollY()
				+ "]");
	}

	public static void initListView(Context context, ListView listView,
			String prefix, int numItems, int layout) {
		// By using setAdpater method in listview we an add string array in
		// list.
		String[] arr = new String[numItems];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = prefix + (i + 1);
		}
		listView.setAdapter(new ArrayAdapter<String>(context, layout, arr));
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Context context = view.getContext();
				String msg = "item[" + position + "]="
						+ parent.getItemAtPosition(position);
				Toast.makeText(context, msg, 1000).show();
				System.out.println(msg);
			}
		});
	}

  public static boolean scrollToTop(Context context) {
    if (!(context instanceof Activity)) {
      return false;
    }
    View decorView = ((Activity) context).getWindow().getDecorView();
    List<View> viewList = new LinkedList<View>();
    viewList.add(decorView);
    // travel through all the views in this window.
    while (!viewList.isEmpty()) {
      View view = viewList.remove(0);
      if (view instanceof AbsListView) {
        // if the view is AbsListView, then see if it is able to scroll to top.
        if (view.getVisibility() != View.VISIBLE) {
          // only take care of the visible list view
          continue;
        }
        Object tag = view.getTag(R.id.list_view_scroll_to_top);
        if (tag instanceof Boolean && (Boolean) tag) {
          // able to scroll to top, then do it, we only handle the first list view we found.
          return scrollToTop((AbsListView) view);
        }
        continue;
      }
      if (view instanceof ViewGroup) {
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup instanceof ViewPager) {
          // if it's ViewPager, only add the current child to view list
          View currentChild = ViewUtils.findCurrentChildView((ViewPager) viewGroup);
          if (currentChild != null) {
            viewList.add(currentChild);
          }
        } else {
          // add all child views into the list.
          for (int i = 0; i < viewGroup.getChildCount(); ++i) {
            View child = viewGroup.getChildAt(i);
            viewList.add(child);
          }
        }
      }
    }
    return false;
  }

  @TargetApi(Build.VERSION_CODES.FROYO)
  public static boolean scrollToTop(AbsListView listView) {
    if (listView.getFirstVisiblePosition() == 0) {
      View firstChild = listView.getChildAt(0);
      if (firstChild == null) {
        return false;
      }
      if (firstChild.getTop() == listView.getPaddingTop()) {
        return false;
      }
    }
    scrollToChildAt(listView, 0);
    return true;
  }

  @TargetApi(Build.VERSION_CODES.FROYO)
  public static void scrollToChildAt(AbsListView listView, int position) {
    if (SystemUtil.aboveApiLevel(Build.VERSION_CODES.FROYO)) {
      // This is used to stop the previous scroll runnable
      listView.smoothScrollBy(0, 0);
      if (Math.abs(listView.getFirstVisiblePosition() - position) <= MAX_SMOOTH_SCROLL_POSITION) {
        listView.smoothScrollToPosition(position);
      } else {
        listView.setSelection(position);
      }
    } else {
      listView.setSelection(position);
    }
  }

  public static View findCurrentChildView(ViewPager viewPager) {
    PagerAdapter adapter = viewPager.getAdapter();
    if (adapter != null && adapter instanceof TabFragmentPagerAdapter) {
      Fragment f = ((TabFragmentPagerAdapter) adapter).getCurrentFragment();
      return f == null ? null : f.getView();
    } else {
      int leftEdge = viewPager.getScrollX();
      int rightEdge = leftEdge + viewPager.getWidth();
      for (int i = 0; i < viewPager.getChildCount(); ++i) {
        View child = viewPager.getChildAt(i);
        // visible
        int childLeft = child.getLeft();
        int childRight = child.getRight();
        if (childLeft >= leftEdge && childRight <= rightEdge) {
          return child;
        }
      }
      return null;
    }
  }
}
