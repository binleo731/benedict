package com.jv.benedict.repository.RemoteRepo;

import com.jv.benedict.repository.common.RepoCallBack;

import org.json.JSONObject;

/**
 * Created by Leo on 2018/1/4.
 */

public interface IRemoteRepo {
  void remotePost(String url,JSONObject jsonObject, Class<?> t, RepoCallBack repoCallBack);
  void cancelHttp();
}
