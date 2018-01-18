package com.jv.benedict.ui.register.setPhoneNumber;

import com.jv.benedict.repository.RemoteRepo.IRemoteRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Leo on 2018/1/12.
 */
public class SetPhoneNumPresenterTest {
  @Mock IRemoteRepo remoteRepo;
  @Mock RegisterContract.SetPhoneView mView;
  private SetPhoneNumPresenter mPresenter;
  @Before public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    when(mView.isActive()).thenReturn(true);
    when(mView.checkAgree()).thenReturn(true);
    mPresenter = new SetPhoneNumPresenter(remoteRepo, mView);
  }

  @Test public void checkPhoneNum() throws Exception {
      mPresenter.checkPhoneNum("185 0037 4995");
  }


  @Test public void getRandomPhone() throws Exception {
  }

  @Test public void onStart() throws Exception {
    verify(mView).setPresenter(mPresenter);
  }

  @Test public void onDestory() throws Exception {

  }

  @Test public void remote() throws Exception {
    //测试发送手机
    mPresenter.remote("18500374995");
  }
}