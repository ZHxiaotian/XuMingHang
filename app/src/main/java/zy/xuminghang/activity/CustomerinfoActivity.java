package zy.xuminghang.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zy.xuminghang.R;
import zy.xuminghang.base.BaseActivity;

public class CustomerinfoActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_app_back;
    TextView tv_app_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinfo);
        BaseActivity.mActivities.add(CustomerinfoActivity.this);
        init();
    }

    private void init(){

        iv_app_back= (ImageView) super.findViewById(R.id.iv_app_title);
        tv_app_title= (TextView) super.findViewById(R.id.tv_app_title);
        tv_app_title.setText("客户资料");
        iv_app_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_app_title:
                 finish();
                break;
            default:
                break;
        }
    }
}
