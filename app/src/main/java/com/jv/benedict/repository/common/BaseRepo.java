package com.jv.benedict.repository.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jv.benedict.utils.Constant;
import com.jv.benedict.utils.NApplication;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

/**
 * repository基类
 * Created by leo on 2017/12/27.
 */

public class BaseRepo implements IBaseRepo {
  // cancel network
  private static final long CANCEL_TAG = System.currentTimeMillis();

  /**
   * 交易成功返回T，需要具体的javaBean接收
   *
   * @param url          服务器地址
   * @param jsonObject   json
   * @param clazz        实体类
   * @param baseRepoCallBack 回调
   */
  protected void post(String url, JSONObject jsonObject, Class<?> clazz, BaseRepoCallBack baseRepoCallBack) {
    if (isConnected()) {//是否有网络
      OkGo.<String>post(Constant.Ip+url)
          .tag(CANCEL_TAG)
          .upJson(jsonObject)//上传map的话可以map转jsonObject
          .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
              success(response, baseRepoCallBack, clazz, true);
            }

            @Override
            public void onError(Response<String> response) {
              error(response, baseRepoCallBack);
            }
          });
    } else {
      baseRepoCallBack.onError("网络连接失败");
    }
  }




  /**
   * 交易成功返回字符串
   *
   * @param url          服务器地址
   * @param jsonObject   json
   * @param baseRepoCallBack 回调
   */
  protected void post(String url, JSONObject jsonObject, BaseRepoCallBack baseRepoCallBack) {
    if (isConnected()) {//是否有网络
      OkGo.<String>post(url)
          .tag(CANCEL_TAG)
          .upJson(jsonObject)//上传map的话可以map转jsonObject
          .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
              success(response, baseRepoCallBack, null, false);
            }

            @Override
            public void onError(Response<String> response) {
              error(response, baseRepoCallBack);
            }
          });
    } else {
      baseRepoCallBack.onError("网络连接失败");
    }
  }

  /**
   * access to server successfully
   */
  private  void success(Response<String> response, BaseRepoCallBack baseRepoCallBack, Class<?> clazz, boolean isClass) {
    if (200 == response.code()) {
      String body = response.body();
      if (!TextUtils.isEmpty(body)) {//非空判断
        try {
          JSONObject jsonObject = new JSONObject(body);
          String returnCode = jsonObject.optString("ReturnCode");//根据自己业务取成功标识
          if ("000000".equals(returnCode)) {//根据自己业务取成功标识
            if (isClass) {
              baseRepoCallBack.onSuccess(new Gson().fromJson(body, clazz));
            } else {
              baseRepoCallBack.onSuccess(body);
            }
          } else {
            String returnMsg = jsonObject.optString("ReturnMsg");//根据自己业务取失败信息
            baseRepoCallBack.onFailed(returnMsg);
          }
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }
  }

  /**
   * access to server unsuccessfully
   */
  private  void error(Response<String> response, BaseRepoCallBack baseRepoCallBack) {
    Throwable r = response.getException();
    String msg;
    if (r instanceof HttpException) {
      msg = "网络异常，请检查您的网络状态";
    } else if (r instanceof SocketTimeoutException) {
      msg = "网络超时，请检查您的网络状态";
    } else if (r instanceof ConnectException) {
      msg = "网络中断，请检查您的网络状态";
    } else if (r instanceof UnknownError) {
      msg = "未知错误";
    } else {
      msg = r.getMessage();
    }
    baseRepoCallBack.onError(msg);
  }

  /**
   * 取消网络请求
   */
  @Override
  public void cancelHttp() {
    OkGo.getInstance().cancelTag(CANCEL_TAG);
  }

  protected interface BaseRepoCallBack {
    /**
     * 交易成功
     */
    <T> void onSuccess(T response);

    /**
     * 交易失败
     */
    void onFailed(String response);

    /**
     * 网络错误
     */
    void onError(String response);
  }

  /**
   * 判断网络是否连接
   * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
   *
   * @return {@code true}: 是<br>{@code false}: 否
   */
  private static boolean isConnected() {
    NetworkInfo info = getActiveNetworkInfo();
    return info != null && info.isConnected();
  }

  /**
   * 获取活动网络信息
   * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>}</p>
   *
   * @return NetworkInfo
   */
  private static NetworkInfo getActiveNetworkInfo() {
    return ((ConnectivityManager) NApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
  }
}
