package zy.xuminghang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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

public class NewAddaddresActivity extends BaseActivity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {

    private TextView tv_addressselect, tv_areais,tv_app_title;
    private EditText usercode, username, userponeno, userwherearea, address;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private boolean isOne;
    private Button bt_saveaddress;
    private CheckBox cb;
    private ImageView iv_title;
    private AddressDao dao = new AddressDao(NewAddaddresActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_addaddres);
        BaseActivity.mActivities.add(NewAddaddresActivity.this);
        initView();
    }

    private void initView() {

        tv_addressselect = (TextView) findViewById(R.id.tv_addressselect);
        tv_areais = (TextView) findViewById(R.id.tv_areais);
        tv_app_title = (TextView) findViewById(R.id.tv_app_title);
        usercode = (EditText) findViewById(R.id.tv_userno);
        username = (EditText) findViewById(R.id.et_receiptmanname);
        userponeno = (EditText) findViewById(R.id.tv_userphoneno);
        address = (EditText) findViewById(R.id.et_address);
        bt_saveaddress = (Button) findViewById(R.id.bt_saveaddress);
        cb = (CheckBox) findViewById(R.id.ck_newaddaddress);
        iv_title= (ImageView)super.findViewById(R.id.iv_app_title);
        iv_title.setOnClickListener(this);
        tv_addressselect.setOnClickListener(this);
        bt_saveaddress.setOnClickListener(this);
        tv_app_title.setText("新增地址");
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    int i=dao.updateAlladdress();
                    if(i!=0){
                        Toast.makeText(NewAddaddresActivity.this,"设置默认",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_addressselect:
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
            case R.id.bt_saveaddress:
                saveAddress();

                break;
            case R.id.iv_app_title:
                finish();
                break;
            default:
                break;

        }
    }

    /**
     * 保存添加的收货地址
     */
    private void saveAddress() {
        AddressManageBean bean=null;
        String code=usercode.getText().toString().trim();
        String name= username.getText().toString();
        String phoneno=userponeno.getText().toString();
        String suozaidq= tv_areais.getText().toString();
        String addr=address.getText().toString();

        if((!TextUtils.isEmpty(code))&&(!TextUtils.isEmpty(name))&&(!TextUtils.isEmpty(phoneno))&&(!TextUtils.isEmpty(suozaidq))&&(!TextUtils.isEmpty(addr))){
//                if()
        int isdefault=cb.isChecked()?1:0;
        bean=new AddressManageBean(usercode.getText().toString().trim(),
                username.getText().toString(),
                userponeno.getText().toString(),
                tv_areais.getText().toString(), address.getText().toString(),isdefault);

        long succes=    dao.addMessage(bean);
        if(succes!=0){
            Toast.makeText(NewAddaddresActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(NewAddaddresActivity.this,AddressManageActivity.class);
            intent.putExtra("address",bean) ;
            setResult(0x01,intent);
            finish();
        }
        }else{
            Toast.makeText(NewAddaddresActivity.this,"不能出现空内容",Toast.LENGTH_SHORT).show();
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
        tv_areais.setText(s);
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
