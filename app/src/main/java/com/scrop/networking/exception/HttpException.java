package com.scrop.networking.exception;

/**
 * Created by Scrop on 2017/7/17.
 */

public class HttpException extends Exception {
    private  static  final  long serialVersionUID = 1L;

    private  int ecode;
    private  Object emsg;

    public HttpException(int ecode, Object emsg){
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
