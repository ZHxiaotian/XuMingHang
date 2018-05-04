package zy.xuminghang.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.adapter.OrderDetialAadpter;
import zy.xuminghang.base.BaseActivity;
import zy.xuminghang.entity.AddressManageBean;
import zy.xuminghang.entity.ShopCar;
import zy.xuminghang.util.AddressDao;

public class OrderDetialActivity extends BaseActivity implements View.OnClickListener, AddressManageActivity.UpdateUI,
        OrderDetialAadpter.CheckInterface, OrderDetialAadpter.ModifyCountInterface {

    ListView lv;
    List<ShopCar> listcar = new ArrayList<ShopCar>();
    private LinearLayout ll_shippaddress, ll_lockaddmore;
    private TextView tv_findmore, tv_makesure, tv_uodateaddress,  tv_shrname, tv_shraddress,tv_shrcode;
    public TextView tv_total;//总额
    private ImageView iv_title;
    private EditText tv_shrphoneno;
    CheckBox cb_total;//全选购物车商品计算价格
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    AddressManageBean bean;
    OrderDetialAadpter adapter;
    private int listviewheight;
    private AddressDao dao = new AddressDao(OrderDetialActivity.this);
    Dialog dialog;
    int width,height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_detial);
         BaseActivity.mActivities.add(OrderDetialActivity.this);
        initView();
    }


    private void initView() {
        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);


//       = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        lv = (ListView) findViewById(R.id.lv_order_detial);
        tv_findmore = (TextView) findViewById(R.id.tv_findmore);
        tv_makesure = (TextView) findViewById(R.id.tv_makesure);
        tv_shrphoneno = (EditText) findViewById(R.id.tv_shrphoneno);
        tv_shrname = (TextView) findViewById(R.id.tv_shrname);
        tv_shraddress = (TextView) findViewById(R.id.tv_shraddress);
        tv_total = (TextView) findViewById(R.id.tv_orderdetial_total);
        tv_shrcode = (TextView) findViewById(R.id.tv_shrcode);
        tv_uodateaddress = (TextView) findViewById(R.id.tv_uodateaddress);
        iv_title=(ImageView) super.findViewById(R.id.iv_app_title);
        ll_shippaddress = (LinearLayout) findViewById(R.id.ll_shippaddress);
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = (int)height/3;
        ll_shippaddress.setLayoutParams(params);
        ll_lockaddmore = (LinearLayout) findViewById(R.id.ll_lockaddmore);
        cb_total = (CheckBox) findViewById(R.id.cb_oderdetial_toatal);
        tv_findmore.setOnClickListener(this);
        tv_makesure.setOnClickListener(this);
        tv_uodateaddress.setOnClickListener(this);
        cb_total.setOnClickListener(this);
        iv_title.setOnClickListener(this);
        bean = dao.findByIsdefault();
        if (null != bean) {
            tv_shrcode.setText(bean.getUsercode());
            tv_shrphoneno.setText(bean.getUsetphoneno());
            tv_shrname.setText(bean.getUsername());
            tv_shraddress.setText(bean.getSzdq() + bean.getDescaddress());
        }
        initData();
        adapter = new OrderDetialAadpter(listcar, OrderDetialActivity.this);
        adapter.setCheckInterface(this);
        adapter.setModifyCountInterface(this);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogs(position);
//                listcar.remove(position);
//                adapter.notifyDataSetChanged();
//                statistics();
                return false;
            }
        });
    }

    public void showDialogs(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetialActivity.this);
        builder.setMessage("确认删除商品");
        builder.setTitle("提示");
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listcar.remove(position);
                adapter.notifyDataSetChanged();
                statistics();
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    /**
     * 初始化订单数据
     */
    private void initData() {
        listcar = new ArrayList<ShopCar>();
        for (int i = 0; i < 10; i++) {
//            int img, String title, String price, String discount, boolean ischose
            listcar.add(new ShopCar(R.mipmap.apple, "山东栖霞精品红富士2.1kg果径80-85mm 苹果", "10", "2", 1, false));
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_findmore:

                if(listcar.size()>=3) {
                    if (listviewheight == lv.getHeight()) {
                        listviewheight = 0;
                        setcutbackListViewHeight(lv);
                        tv_findmore.setText("查看更多>>");
                    } else {
                        setListViewHeight(lv);
                        tv_findmore.setText("收起查看更多>>");
                    }
                }

                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_makesure:
                break;
            case R.id.tv_uodateaddress://地址管理
                Intent intent = new Intent(OrderDetialActivity.this, AddressManageActivity.class);

                startActivityForResult(intent, 0x08);
                AddressManageActivity.setCallBack(this);
                break;
            case R.id.cb_oderdetial_toatal:
                if (listcar.size() != 0) {
                    if (cb_total.isChecked()) {
                        int total = 0;
                        for (int i = 0; i < listcar.size(); i++) {

                            listcar.get(i).setIschose(true);
                            int price = Integer.parseInt(listcar.get(i).getPrice());
                            int discuont = Integer.parseInt(listcar.get(i).getDiscount());
                            int count = listcar.get(i).getCount();
                            total = total + (price - discuont) * count;
                        }
                        adapter.notifyDataSetChanged();

//                         int total=adapter.total();
                        tv_total.setText("" + total);

                    } else {
                        for (int i = 0; i < listcar.size(); i++) {

                            listcar.get(i).setIschose(false);
                            int price = Integer.parseInt(listcar.get(i).getPrice());
                            int discuont = Integer.parseInt(listcar.get(i).getDiscount());
                            int count = listcar.get(i).getCount();

                        }
                        adapter.notifyDataSetChanged();
                    }

                }
                statistics();
                break;
            case R.id.iv_app_title:

                finish();
                break;
            default:
                break;
        }
    }

    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < listcar.size(); i++) {
            ShopCar shoppingCartBean = listcar.get(i);
            if (shoppingCartBean.ischose()) {
                totalCount++;
                totalPrice += Integer.parseInt(shoppingCartBean.getPrice()) * shoppingCartBean.getCount();
            }
        }
        tv_total.setText("" + totalPrice);
//         tvSettlement.setText("结算(" + totalCount + ")");
    }

    @Override
    public void updateAddress(AddressManageBean bean) {
        if (null != bean) {
            tv_shrcode.setText(bean.getUsercode());
            tv_shrphoneno.setText(bean.getUsetphoneno());
            tv_shrname.setText(bean.getUsername());
            tv_shraddress.setText(bean.getSzdq() + bean.getDescaddress());
        }
    }

    /**
     * 改变订单商品的选中
     *
     * @param position  元素位置
     * @param isChecked 元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {

        listcar.get(position).setIschose(isChecked);
        if (isAllCheck()) {
            cb_total.setChecked(true);
        } else {
            cb_total.setChecked(false);
        }
        adapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ShopCar group : listcar) {
            if (!group.ischose())
                return false;
        }
        return true;
    }

    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {

        ShopCar shoppingCartBean = listcar.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        statistics();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShopCar shoppingCartBean = listcar.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        adapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void childDelete(int position) {

    }

    public void setListViewHeight(ListView lv) {
        //获取listView的adapter
        ListAdapter listAdapter = lv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        //listAdapter.getCount()返回数据项的数目
        for (int i = 0,len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, lv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() *  (listAdapter .getCount() - 1));
        lv.setLayoutParams(params);
        listviewheight= params.height;

    }

    public void setcutbackListViewHeight(ListView lv) {
        //获取listView的adapter
        ListAdapter listAdapter = lv.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        //listAdapter.getCount()返回数据项的数目
        for (int i = 0,len = listAdapter.getCount(); i < 3; i++) {
            View listItem = listAdapter.getView(i, null, lv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() *  (listAdapter .getCount() - 1));
        lv.setLayoutParams(params);


    }


}
