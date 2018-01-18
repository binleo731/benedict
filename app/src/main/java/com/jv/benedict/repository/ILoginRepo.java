package com.jv.benedict.repository;

import com.jv.benedict.repository.common.IBaseRepo;
import com.jv.benedict.repository.common.RepoCallBack;

import org.json.JSONObject;

/**
 * Login repo
 * Created by leo on 2018/1/2.
 */

public interface ILoginRepo extends IBaseRepo {
     void login(String url,JSONObject jsonObject, Class<?> t, RepoCallBack repoCallBack);
}
