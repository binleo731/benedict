package com.jv.benedict.utils;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by wj on 2017/7/25 11:18
 */

public class NApplication extends Application {
    private static final boolean isLog = true;// 统一设置是否打日志（上线时为false）
    private static Context context;//Context第一步
    static{
        System.loadLibrary("PassGuard");
    }

    public static Context getContext() {//Context第三步(一共三步)
        return context;
    }
    static{
        System.loadLibrary("PassGuard");
    }

    //全局初始化SmartRefreshLayout刷新框架。也可以不设置，有默认的
    //    static {//static 代码段可以防止内存泄露
    //        //设置全局的Header构建器
    //        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
    //            @Override
    //            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
    //                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
    //                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
    //            }
    //        });
    //        //设置全局的Footer构建器
    //        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
    //            @Override
    //            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
    //                //指定为经典Footer，默认是 BallPulseFooter
    //                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
    //            }
    //        });
    //    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();//Context第二步（或者context=this）
        //初始化Utils工具
        Utils.init(this);
        //初始化okgo网络请求框架
        initOkGo();
        //异常处理类（错误日志会存储在手机crashlog文件夹里）
        //CrashHandler crashHandler = CrashHandler.getInstance();
        //        crashHandler.init(this);
        //初始化日志工具logger
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag("wj").build()) {//tag： Global tag for every log. Default PRETTY_LOGGER
            @Override
            public boolean isLoggable(int priority, String tag) {
                //上线时改为false
                return isLog;//false为不打印日志，true为打印日志（不重写isLoggable则默认打印，如Logger.addLogAdapter(new AndroidLogAdapter())）
            }
        });
        //        LogUtil.isDebug(isLog);// 版本更新日志（上线时为false）
    }

    /**
     * 初始化okgo网络请求框架
     */
    private void initOkGo() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        if (isLog) {
            builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志（上线时去掉）
        }
        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //方法二：自定义信任规则，校验服务端证书
        //        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //        try {
        //            HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("mobpieces.cer"));
        //            builder.sslSocketFactory(sslParams3.sSLSocketFactory, sslParams3.trustManager);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance()
            .init(this)                           //必须调用初始化
            .setOkHttpClient(builder.build());               //建议设置OkHttpClient，不设置会使用默认的
    }
}
