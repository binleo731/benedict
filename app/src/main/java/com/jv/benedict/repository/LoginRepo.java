package com.jv.benedict.repository;


import com.jv.benedict.repository.common.BaseRepo;
import com.jv.benedict.repository.common.RepoCallBack;

import org.json.JSONObject;

/**
 * ILoginRepo
 * Created by leo on 2017/12/27.
 */

public class LoginRepo extends BaseRepo implements ILoginRepo {
    /**
     **
    */
    @Override
    public void login(String url, JSONObject jsonObject, Class<?> t, RepoCallBack repoCallBack) {
        post(url, jsonObject, t, new BaseRepoCallBack() {
            @Override
            public <T> void onSuccess(T response) {
                repoCallBack.onSuccess(response);
            }

            @Override
            public void onFailed(String response) {
                repoCallBack.onFailed(response);
            }

            @Override
            public void onError(String response) {
                repoCallBack.onError(response);
            }
        });
    }
}
