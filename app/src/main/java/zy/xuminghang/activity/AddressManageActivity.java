package zy.xuminghang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import zy.xuminghang.R;
import zy.xuminghang.adapter.AddressManageAdapter;
import zy.xuminghang.base.BaseActivity;
import zy.xuminghang.entity.AddressManageBean;
import zy.xuminghang.util.AddressDao;

public class AddressManageActivity extends BaseActivity implements View.OnClickListener {

    ListView lv;
    List<AddressManageBean> list;
    private Button bt_newadd;
    private ImageView back;
    private TextView tv_title;
    private AddressDao dao=new AddressDao(AddressManageActivity.this);
    private  AddressManageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_address_manage);
        BaseActivity.mActivities.add(AddressManageActivity.this);
        initView();
    }



    /**
     * 初始化方法初始化所有控件
     */
    private void initView() {


        lv= (ListView) findViewById(R.id.lv_addressmanager);
        bt_newadd= (Button) findViewById(R.id.bt_newadd);
         back=(ImageView) findViewById(R.id.iv_app_title);
         tv_title=(TextView)super.findViewById(R.id.tv_app_title);
        tv_title.setText("地址管理");
        initdata();
        if(null!=list&&list.size()>0) {
            adapter = new AddressManageAdapter(list, AddressManageActivity.this);
            lv.setAdapter(adapter);
        }else{

        }
        bt_newadd.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void initdata() {

        list=dao.selectAddress();


    }

    /**
     * 点击时间监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_newadd:
                   Intent intent=new Intent(AddressManageActivity.this,NewAddaddresActivity.class);
                startActivityForResult(intent,0x00);

                break;
            case R.id.iv_app_title:
//                 Intent intent1=new Intent(AddressManageActivity.this,OrderDetialActivity.class);
//
//                setResult(0x07,intent1);
                AddressManageBean bean=null;
                for(AddressManageBean data:list){
                    if(data.isdefault()==1){
                        bean=data;
                    }
                }
                updui.updateAddress(bean);
                finish();


            break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0x01) {
            if (requestCode == 0x00) {
                if (null != data.getSerializableExtra("address")) {
                   if(list!=null&&list.size()>0&&adapter!=null) {
                       if(((AddressManageBean) data.getSerializableExtra("address")).isdefault()==1){
                           for(AddressManageBean b:list){
                               b.setIsdefault(0);
                           }
                       }
                       list.add((AddressManageBean) data.getSerializableExtra("address"));
                       adapter.notifyDataSetChanged();
                   }else{
                       list=dao.selectAddress();
                       adapter = new AddressManageAdapter(list, AddressManageActivity.this);
                       lv.setAdapter(adapter);
                   }

                }
            } }
       else if(resultCode==0x04){


//            list=dao.selectAddress();
              list.set(data.getIntExtra("position",0), (AddressManageBean) data.getSerializableExtra("address"));
            adapter.notifyDataSetChanged();
//            adapter=new AddressManageAdapter(list,AddressManageActivity.this);
//            lv.setAdapter(adapter);

        }

    }

        public static  interface UpdateUI{

            public void  updateAddress(AddressManageBean bean);
        }
        public static UpdateUI updui;
        public static   void setCallBack(UpdateUI callBack){

            updui=callBack;
        }
}
