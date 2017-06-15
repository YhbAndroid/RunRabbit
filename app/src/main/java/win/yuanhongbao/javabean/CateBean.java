package win.yuanhongbao.javabean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class CateBean {

    private Bitmap mCateImg;
    private String mCateName;
    private String mCateAddress;
    private String mCatePrice;
    private String mCateImagUrl;

    public String getmCateImagUrl() {
        return mCateImagUrl;
    }

    public void setmCateImagUrl(String mCateImagUrl) {
        this.mCateImagUrl = mCateImagUrl;
    }

    public Bitmap getmCateImg() {
        return mCateImg;
    }

    public void setmCateImg(Bitmap mCateImg) {
        this.mCateImg = mCateImg;
    }

    public String getmCateName() {
        return mCateName;
    }

    public void setmCateName(String mCateName) {
        this.mCateName = mCateName;
    }

    public String getmCateAddress() {
        return mCateAddress;
    }

    public void setmCateAddress(String mCateAddress) {
        this.mCateAddress = mCateAddress;
    }

    public String getmCatePrice() {
        return mCatePrice;
    }

    public void setmCatePrice(String mCatePrice) {
        this.mCatePrice = mCatePrice;
    }
}
