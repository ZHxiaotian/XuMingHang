package zy.xuminghang.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import zy.xuminghang.R;
import zy.xuminghang.adapter.DetialAdapter;
import zy.xuminghang.base.BaseActivity;
import zy.xuminghang.entity.Commodity;
import zy.xuminghang.entity.GlobleData;
import zy.xuminghang.entity.ShopCar;
import zy.xuminghang.util.OrderDao;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private GridView gv;
    private ImageView iv;
    private List<Commodity> list;
    private Button tv_makesureorder;
    private List<ShopCar> shopcarlist=new ArrayList<ShopCar>();
    private OrderDao dao = new OrderDao(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        BaseActivity.mActivities.add(MainActivity.this);
        initView();
    }

    private void initView() {

        gv = (GridView) findViewById(R.id.gv_details);
        iv = (ImageView) findViewById(R.id.iv_sys);
        tv_makesureorder = (Button) findViewById(R.id.bt_detials);

        iv.setOnClickListener(this);
        tv_makesureorder.setOnClickListener(this);
        initData();
        DetialAdapter adapter = new DetialAdapter(MainActivity.this, list);
        gv.setAdapter(adapter);
    }

    /**
     * 初始化模拟商品的数据
     */
    private void initData() {

        list = new ArrayList<Commodity>();
        for (int i = 0; i < GlobleData.imgurl.length; i++) {
            list.add(new Commodity(GlobleData.imgurl[i], GlobleData.desc[i], 20, 0));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_sys:

                customScan();
                break;
            case R.id.bt_detials://确认生成订单按钮的监听
//                for(Commodity commodity:list){
////                    img, String title, String price, String discount, int count, boolean ischose
//                    dao.insertOrder(new ShopCar(commodity.getImgurl(),commodity.getDetial(),commodity.getPrice()+"","2",commodity.getCount(),false));
//
//;                }


                Intent intent = new Intent(MainActivity.this, OrderDetialActivity.class);
                startActivity(intent);

                break;
            default:
                break;
        }

    }

    // 你可以把这个方法作为一个点击事件
    public void customScan() {
        new IntentIntegrator(this)
                .setOrientationLocked(false)
                .setCaptureActivity(CustomScanActivity.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }

    @Override
// 通过 onActivityResult的方法获取 扫描回来的 值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "intentResult.getContents()", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
