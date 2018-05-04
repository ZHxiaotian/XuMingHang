package zy.xuminghang.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.mode.NewCommodity;

/**
 * author：wangzihang
 * date： 2017/8/8 19:15
 * desctiption：
 * e-mail：wangzihang@xiaohongchun.com
 */

public class HomeItemAdapter extends BaseAdapter {

    private Context context;
    private List<NewCommodity> foodDatas;

    public HomeItemAdapter(Context context, List<NewCommodity> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
    }


    @Override
    public int getCount() {
        if (foodDatas != null) {
            return foodDatas.size();
        } else {
            return 10;
        }
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewCommodity subcategory = foodDatas.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_category, null);
            viewHold = new ViewHold();
            viewHold.tv_name = (TextView) convertView.findViewById(R.id.item_home_name);
          //  viewHold.iv_icon = (SimpleDraweeView) convertView.findViewById(R.id.item_album);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        
        viewHold.tv_name.setText(subcategory.getName());
//        Uri uri = Uri.parse(subcategory.getImgURL());
//        viewHold.iv_icon.setImageURI(uri);
        return convertView;


    }

    private static class ViewHold {
        private TextView tv_name;
       // private SimpleDraweeView iv_icon;
    }

}
