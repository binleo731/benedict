package com.jv.benedict.ui.loginPwd;

import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Created by Leo on 2018/1/12.
 */
public class LoginPwdPresenterTest {
public  static final String   MSG = "msg";
  @Mock IRemoteRepo remoteRepo;
  @Mock LoginPwdContract.LoginPwdView mView;


  private LoginPwdPresenter mPresenter;
  private InOrder inOrder;

  @Before public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
    when(mView.isActive()).thenReturn(true);
    mPresenter = new LoginPwdPresenter(remoteRepo, mView);
  }

  @Test public void onStart() throws Exception {
    //测试presenter 传入 lognpwdfragmnet中
    verify(mView).setPresenter(mPresenter);
  }

  @Test public void onDestory() throws Exception {
  }

  @Test public void checkLoginPwd() throws Exception {
    Mockito.mock(LoginPwdFragment.class);

    inOrder = inOrder(mView);
    verify(mView).showToast(MSG);
  }

  @Test public void checkLoginPwdConfirm() throws Exception {
  }

  @Test public void checkEzayLoginPwd() throws Exception {
  }

  @Test public void remote() throws Exception {

  }
}