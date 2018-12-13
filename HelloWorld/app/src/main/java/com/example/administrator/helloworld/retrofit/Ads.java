package com.example.administrator.helloworld.retrofit;

/**
 * Created by Administrator on 2018/1/15.
 */

public class Ads {

    /**
     * Url : http://www.liudu.com/Home/JKInfo?jkuserid=312986
     * Description : 大洋机电
     * ImageUrl : http://t.968967.com:8021/TempFiles/AdverPicFile/201506020330333791.jpg
     * OrderBy : 16
     * ResType : 0
     */

    private String Url;
    private String Description;
    private String ImageUrl;
    private int OrderBy;
    private int ResType;

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public int getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(int OrderBy) {
        this.OrderBy = OrderBy;
    }

    public int getResType() {
        return ResType;
    }

    public void setResType(int ResType) {
        this.ResType = ResType;
    }
}
