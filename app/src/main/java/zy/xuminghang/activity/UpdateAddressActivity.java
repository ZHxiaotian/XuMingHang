package zy.xuminghang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.utils.LogUtil;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import zy.xuminghang.R;
import zy.xuminghang.base.BaseActivity;
import zy.xuminghang.entity.AddressManageBean;
import zy.xuminghang.util.AddressDao;

public class UpdateAddressActivity extends BaseActivity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {

    private AddressManageBean bean;
    private EditText  et_usercode,et_username,et_userpnoneno,userdescaddress;
    private TextView tv_userszdq,tv_chose,tv_app_title;
    private Button bt_makesureupd;
    private CheckBox cb;
    private ImageView iv_app_back;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private int position;
    private boolean isOne;
    private AddressDao dao=new AddressDao(UpdateAddressActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_address);
        BaseActivity.mActivities.add(UpdateAddressActivity.this);
        initView();

    }

    /**
     *  初始化方法
     */
    private void initView() {
         if(getIntent().getSerializableExtra("addressbean")!=null){

             bean= (AddressManageBean) getIntent().getSerializableExtra("addressbean");
             position=getIntent().getIntExtra("position",0);
         }
        et_usercode= (EditText) findViewById(R.id.tv_updete_userno);
        et_username= (EditText) findViewById(R.id.et_update_receiptmanname);
        et_userpnoneno= (EditText) findViewById(R.id.tv_update_userphoneno);
        userdescaddress= (EditText) findViewById(R.id.et_updete_address);
        tv_chose= (TextView) findViewById(R.id.tv_update_addressselect);
        tv_userszdq= (TextView) findViewById(R.id.tv_update_areais);
        tv_app_title= (TextView) findViewById(R.id.tv_app_title);
        bt_makesureupd= (Button) findViewById(R.id.bt_update_saveaddress);
        cb= (CheckBox) findViewById (R.id.ck_update_newaddaddress);
        iv_app_back=(ImageView)super.findViewById(R.id.iv_app_title);
        iv_app_back.setOnClickListener(this);
        tv_chose.setOnClickListener( this);
        bt_makesureupd.setOnClickListener( this);
        et_usercode.setText(bean.getUsercode());
        et_username.setText(bean.getUsername());
        et_userpnoneno.setText(bean.getUsetphoneno());
        tv_userszdq.setText(bean.getSzdq());
        userdescaddress.setText(bean.getDescaddress());
        tv_app_title.setText("修改地址");
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    int i=dao.updateAlladdress();
                    if(i!=0){
                        Toast.makeText(UpdateAddressActivity.this,"设置默认",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_update_addressselect:
                if (dialog != null) {
                    if (isOne) {
                        //根据名称查询id,设置数据
                    }
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this);
                    dialog.setOnAddressSelectedListener(this);
                    dialog.setDialogDismisListener(this);
                    dialog.setTextSize(14);//设置字体的大小
                    dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
                    dialog.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
                    dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
                    dialog.show();
                }


                break;
            case R.id.bt_update_saveaddress:
                int isdefault=cb.isChecked()?1:0;


                AddressManageBean data=new AddressManageBean(et_usercode.getText().toString().trim(),
                        et_username.getText().toString(),
                        et_userpnoneno.getText().toString(),
                        tv_userszdq.getText().toString(),userdescaddress.getText().toString() ,isdefault);
                          data.setId(bean.getId());

                long succes=    dao.updateAddressbyid(data);

                if(succes!=0){

                    Toast.makeText(UpdateAddressActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(UpdateAddressActivity.this,AddressManageActivity.class);
                    intent.putExtra("address",data);
                    intent.putExtra("position",position);
                    setResult(0x04,intent);
                    finish();
                }
                break;
            case R.id.iv_app_title:
                 finish();
                break;
            default:
                break;

        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        tv_userszdq.setText(s);
        isOne = false;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
