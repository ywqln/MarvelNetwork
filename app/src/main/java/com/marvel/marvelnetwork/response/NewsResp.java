package com.marvel.marvelnetwork.response;

import com.google.gson.annotations.SerializedName;

/**
 * 描述:新闻响应对象.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class NewsResp<T> {
    @SerializedName("reason")
    private String reason;

    @SerializedName("result")
    private T result;

    @SerializedName("error_code")
    private int errorCode;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
