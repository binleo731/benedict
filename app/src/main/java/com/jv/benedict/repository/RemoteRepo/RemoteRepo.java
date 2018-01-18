package com.jv.benedict.repository.RemoteRepo;

import com.jv.benedict.repository.common.BaseRepo;
import com.jv.benedict.repository.common.RepoCallBack;

import org.json.JSONObject;

/**
 * Created by Leo on 2018/1/4.
 */

public class RemoteRepo extends BaseRepo implements IRemoteRepo {

  @Override public void remotePost(String url,JSONObject jsonObject, Class<?> t, RepoCallBack repoCallBack) {
    post(url, jsonObject, t, new BaseRepo.BaseRepoCallBack() {
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
