package zy.xuminghang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.entity.ShopCar;

/**
 * Created by Administrator on 2017/8/24 0024.
 */
public class OrderDetialAadpter extends BaseAdapter {


    List<ShopCar> list;
    Context context;
   private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;

    public OrderDetialAadpter(List<ShopCar> list, Context context) {
        this.list = list;
        this.context = context;
    }
    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ShopCar commodity = list.get(position);
     final   ViewHolder holder ;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_order_detial, null);
            holder.img = (ImageView) convertView.findViewById(R.id.iv_item_order_detil);
            holder.cb = (CheckBox) convertView.findViewById(R.id.cb_settelment);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_order_detia_desc);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_item_order_detil_price);
            holder.tv_discout = (TextView) convertView.findViewById(R.id.tv_item_order_detil_discout);
            holder.tv_discoutprice = (TextView) convertView.findViewById(R.id.tv_item_order_detil_newprice);
            holder.tv_countadd = (TextView) convertView.findViewById(R.id.iv_add);
            holder.tv_countsuv = (TextView) convertView.findViewById(R.id.iv_sub);
            holder.et_count = (EditText) convertView.findViewById(R.id.tv_commodity_show_num);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.cb.setChecked(commodity.ischose());
        holder.tv_title.setText(commodity.getTitle());
        holder.tv_price.setText(commodity.getPrice());
        holder.tv_discout.setText(commodity.getDiscount());
        holder.img.setImageResource(commodity.getImg());
        int discoutprice = Integer.parseInt(commodity.getPrice()) - Integer.parseInt(commodity.getDiscount());
        holder.tv_discoutprice.setText(discoutprice + "");


        holder.cb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commodity .setIschose(((CheckBox)v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );
        //增加按钮
        holder.tv_countadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(position, holder.et_count, holder.cb.isChecked());//暴露增加接口
            }
        });
        //删减按钮
        holder.tv_countsuv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(position, holder.et_count, holder.cb.isChecked());//暴露删减接口
            }
        });
//        final ViewHolder finalHolder = holder;
//        View.OnClickListener listener = new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                switch (v.getId()) {
//                    case R.id.iv_add:
//                      int i=  Integer.parseInt(finalHolder.et_count.getText().toString());
//                        i++;
//                        finalHolder.et_count.setText(i +"");
//                        break;
//                    case R.id.iv_sub:
//                        int k=  Integer.parseInt(finalHolder.et_count.getText().toString());
//                        k--;
//                        finalHolder.et_count.setText(k+"");
//
//                        break;
//                    default:
//                        break;
//
//
//                }
//            }
//        };
//        holder.tv_countadd.setOnClickListener(listener);
//        holder.tv_countsuv.setOnClickListener(listener);
        return convertView;
    }
//        public int total(){
//
//            return 0;
//        }
    class ViewHolder {

        private ImageView img;
        private TextView tv_title;
        private TextView tv_price;
        private TextView tv_discout;
        private TextView tv_discoutprice;
        private CheckBox cb;
        private TextView tv_countadd;
        private TextView tv_countsuv;
        private EditText et_count;

    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }

}
