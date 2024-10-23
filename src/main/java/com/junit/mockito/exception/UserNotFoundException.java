package com.junit.mockito.exception;

public class UserNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserNotFoundException(String msg) {
        super();
        this.msg = msg;
    }
}
