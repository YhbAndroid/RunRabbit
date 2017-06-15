package win.yuanhongbao.javabean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class ShopBean {

    public Bitmap shopImg;
    public String shopName;
    public String shopAddress;
    public String shopOpenTime;
    public String shopCloseTime;
    public String shopImagUrl;

    public String getShopImagUrl() {
        return shopImagUrl;
    }

    public void setShopImagUrl(String shopImagUrl) {
        this.shopImagUrl = shopImagUrl;
    }

    public Bitmap getShopImg() {
        return shopImg;
    }

    public void setShopImg(Bitmap shopImg) {
        this.shopImg = shopImg;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopOpenTime() {
        return shopOpenTime;
    }

    public void setShopOpenTime(String shopOpenTime) {
        this.shopOpenTime = shopOpenTime;
    }

    public String getShopCloseTime() {
        return shopCloseTime;
    }

    public void setShopCloseTime(String shopCloseTime) {
        this.shopCloseTime = shopCloseTime;
    }
}
