package com.note4j.account.model;

/**
 * Created by changwei on 15/4/18.
 */
public class ErrDTO<T> {
    private int errcode;
    private T obj;

    public ErrDTO() {
    }

    public ErrDTO(int errcode, T obj) {
        this.errcode = errcode;
        this.obj = obj;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
