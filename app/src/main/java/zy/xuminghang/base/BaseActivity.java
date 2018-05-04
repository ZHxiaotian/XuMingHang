package zy.xuminghang.base;

import android.app.Activity;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public abstract class BaseActivity extends Activity {
    // 管理运行的所有的activity
    public final static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
    public static BaseActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        synchronized (mActivities) {
            mActivities.add(this);
        }
        init();
    }

    private void init() {
//        initViews();

    }

    /**
     * 初始化Views
     */
//    public abstract void initViews();



    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
        activity = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        synchronized (mActivities) {
            mActivities.remove(this);
        }
    }

    /**
     * 一键退出的方法
     */
    public void killAll() {
        // 复制了一份mActivities 集合
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
