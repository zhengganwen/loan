package com.study.loan.core;

public class ResultMessage {
    public boolean  result;
    public String message;
    public Object  object;

    public ResultMessage(){}

    public ResultMessage(boolean result,String message) {
        this.result = result;
        this.message = message;
    }
    public ResultMessage(boolean result,String message, Object  object) {
        this.result = result;
        this.message = message;
        this.object=object;
    }


    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
