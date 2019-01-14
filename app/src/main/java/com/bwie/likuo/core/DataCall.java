package com.bwie.likuo.core;

/**
 * date:2019/1/7
 * author:李阔(淡意衬优柔)
 * function:
 */
public interface DataCall<T> {
    void success(T data);
    void fail(Exception e);
}
