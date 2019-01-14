package com.bwie.likuo.bean;

/**
 * date:2019/1/7
 * author:李阔(淡意衬优柔)
 * function:
 */
public class Result<T>{
    private String message;
    private String status;
    private T result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
