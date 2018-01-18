package com.jv.benedict.repository.common;

/**
 * 所有Repo的回调接口
 * Created by leo on 2017/12/27.
 */

public interface RepoCallBack {
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
