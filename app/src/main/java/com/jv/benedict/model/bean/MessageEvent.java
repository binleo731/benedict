package com.jv.benedict.model.bean;

/**
 * Created by leo on 2018/1/17.
 */

public class MessageEvent {
  private String message;

  public MessageEvent(String message){
    this.message = message;
  }

  public String getMessage(){
    return message;
  }

}
