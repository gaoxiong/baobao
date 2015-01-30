package com.baobao.premium.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baobao.premium.R;

/**
 * Created by gaoxiong on 15-1-28.
 */
public class MenuListAdapter extends BaseAdapter {
  Context context;
  String[] mTitle;
  String[] mSubTitle;
  int[] mIcon;

  public MenuListAdapter(Context context, String[] title, String[] subtitle, int[] icon) {
    this.context = context;
    this.mTitle = title;
    this.mSubTitle = subtitle;
    this.mIcon = icon;
  }

  @Override
  public int getCount() {
    return mTitle.length;
  }

  @Override
  public Object getItem(int i) {
    return mTitle[i];
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater =
      (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);
    TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
    TextView txtSubTitle = (TextView) itemView.findViewById(R.id.subtitle);
    ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

    txtTitle.setText(mTitle[position]);
    txtSubTitle.setText(mSubTitle[position]);
    imgIcon.setImageResource(mIcon[position]);

    return itemView;
  }
}
