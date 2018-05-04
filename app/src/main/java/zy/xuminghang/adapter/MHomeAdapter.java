package zy.xuminghang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.activity.MainActivity;
import zy.xuminghang.mode.MenuBean;
import zy.xuminghang.mode.NewCommodity;
import zy.xuminghang.view.GridViewForScrollView;

/**
 * 右侧主界面ListView的适配器
 *
 * @author Administrator
 */
public class MHomeAdapter extends BaseAdapter {

    private Context context;
    private List<MenuBean> foodDatas;
    public  DetialAdapter adapter;
    public MHomeAdapter(Context context, List<MenuBean> foodDatas) {
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
        MenuBean dataBean = foodDatas.get(position);
        List<NewCommodity> dataList = dataBean.getArtiList();
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_mhome, null);
            viewHold = new ViewHold();
            viewHold.gridView = (GridViewForScrollView) convertView.findViewById(R.id.gridView);
            viewHold.blank = (TextView) convertView.findViewById(R.id.blank);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }

//        HomeItemAdapter adapter = new HomeItemAdapter(context, dataList);
        adapter=new DetialAdapter(context,dataList,position);
     //   adapter.setModifyCountInterface((MainActivity)context);
        viewHold.blank.setText(dataBean.getName());
        viewHold.gridView.setAdapter(adapter);
        return convertView;
    }

    private static class ViewHold {
        private GridViewForScrollView gridView;
        private TextView blank;
    }






}
