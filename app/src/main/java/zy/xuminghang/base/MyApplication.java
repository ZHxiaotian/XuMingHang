package zy.xuminghang.base;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class MyApplication extends Application {


   @Override
   public void onCreate() {
      super.onCreate();

      OkHttpClient okHttpClient = new OkHttpClient.Builder()
              .connectTimeout(10000L, TimeUnit.MILLISECONDS)
              .readTimeout(10000L, TimeUnit.MILLISECONDS)
              .addInterceptor(new LoggerInterceptor("TAG"))

              .hostnameVerifier(new HostnameVerifier()
              {
                 @Override
                 public boolean verify(String hostname, SSLSession session)
                 {
                    return true;
                 }
              })

              .build();
      OkHttpUtils.initClient(okHttpClient);

   }
}
