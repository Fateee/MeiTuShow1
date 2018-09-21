package com.meitu.show.model.eventbus;

public class MessageEvent {
    private String message;
    private int msgCode;
    private Object obj;

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(int msgCode) {
        this.msgCode = msgCode;
    }

    public MessageEvent(int msgCode, Object obj) {
        this.msgCode = msgCode;
        this.obj = obj;
    }

    public MessageEvent(String message, int msgCode, Object obj) {
        this.message = message;
        this.msgCode = msgCode;
        this.obj = obj;
    }

    public int getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(int msgCode) {
        this.msgCode = msgCode;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
