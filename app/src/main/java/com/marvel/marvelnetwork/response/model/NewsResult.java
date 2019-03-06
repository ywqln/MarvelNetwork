package com.marvel.marvelnetwork.response.model;

import java.util.List;

/**
 * 描述:新闻响应实体.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class NewsResult {
    private String stat;
    private List<News> data;

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }
}
