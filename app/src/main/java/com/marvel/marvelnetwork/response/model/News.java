package com.marvel.marvelnetwork.response.model;

import com.google.gson.annotations.SerializedName;

/**
 * 描述:新闻实体.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class News {
    private String uniquekey;
    // 新闻标题
    private String title;
    // 新闻日期
    private String date;
    // 分类
    private String category;
    // 新闻来源
    @SerializedName("author_name")
    private String author;
    // 新闻网页地址
    private String url;
    // 新闻缩略图
    @SerializedName("thumbnail_pic_s")
    private String thumbUrl;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
