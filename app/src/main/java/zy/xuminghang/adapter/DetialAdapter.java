package zy.xuminghang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.entity.Commodity;
import zy.xuminghang.view.AmountView;

/**
 * Created by Administrator on 2017/8/23 0023.
 */
public class DetialAdapter extends BaseAdapter {

    private List<Commodity> list;
    private Context context;

    public DetialAdapter(Context context, List<Commodity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Commodity data = list.get(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_detial, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.iv_detial);
            holder.tv_detial = (TextView) convertView.findViewById(R.id.tv_detial);
            holder.  mAmountView = (AmountView)convertView. findViewById(R.id.av_count);
            holder.price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mAmountView.setGoods_storage(100);
        holder. mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {

            }
        });
        holder.img.setImageResource(data.getImgurl());
        holder.tv_detial.setText(data.getDetial());
        holder.price.setText(data.getPrice()+"");

        return convertView;
    }

    /**
     * 适配器的优化
     *
     */
    class ViewHolder {

        private ImageView img;
        private TextView tv_detial;
        private TextView price;
        private AmountView mAmountView;
        ;
    }


}
