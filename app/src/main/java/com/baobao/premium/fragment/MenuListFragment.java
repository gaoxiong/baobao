package com.baobao.premium.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baobao.premium.R;
import com.baobao.premium.activity.BaoBaoActivity;

/**
 * Created by gaoxiong on 15-1-30.
 */
public class MenuListFragment extends ListFragment {

  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.aa_test_list, null);
  }

  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    SampleAdapter2 adapter = new SampleAdapter2(getActivity());
    for (int i = 0; i < 20; i++) {
      adapter.add(new SampleItem2("Sample List", android.R.drawable.ic_menu_search));
    }
    setListAdapter(adapter);
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
    selectItem(position);
    super.onListItemClick(l, v, position, id);
  }

  private void selectItem(int position) {
    Activity activity = getActivity();
    if (activity instanceof BaoBaoActivity) {
      BaoBaoActivity baobaoActivity = (BaoBaoActivity) activity;
      baobaoActivity.setTitle(String.valueOf(position));
      baobaoActivity.closeSlidingMenu();
    }
  }

  private class SampleItem2 {
    public String tag;
    public int iconRes;
    public SampleItem2(String tag, int iconRes) {
      this.tag = tag;
      this.iconRes = iconRes;
    }
  }

  public class SampleAdapter2 extends ArrayAdapter<SampleItem2> {

    public SampleAdapter2(Context context) {
      super(context, 0);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
      if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.aa_test_row, null);
      }
      ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
      icon.setImageResource(getItem(position).iconRes);
      TextView title = (TextView) convertView.findViewById(R.id.row_title);
      title.setText(getItem(position).tag);

      return convertView;
    }
  }
}
