package win.yuanhongbao.javabean;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2017/4/23 0023.
 * 商品列表和购物车列表共用的 JavaBean
 */

public class GoodsBean {
    private Bitmap mGoodsImg;
    private String mGoodsName;
    private String mGoodsType;
    private String mGoodsPrice;
    private String mGoodsImgUrl;

    public Bitmap getmGoodsImg() {
        return mGoodsImg;
    }

    public void setmGoodsImg(Bitmap mGoodsImg) {
        this.mGoodsImg = mGoodsImg;
    }

    public String getmGoodsName() {
        return mGoodsName;
    }

    public void setmGoodsName(String mGoodsName) {
        this.mGoodsName = mGoodsName;
    }

    public String getmGoodsType() {
        return mGoodsType;
    }

    public void setmGoodsType(String mGoodsType) {
        this.mGoodsType = mGoodsType;
    }

    public String getmGoodsPrice() {
        return mGoodsPrice;
    }

    public void setmGoodsPrice(String mGoodsPrice) {
        this.mGoodsPrice = mGoodsPrice;
    }

    public String getmGoodsImgUrl() {
        return mGoodsImgUrl;
    }

    public void setmGoodsImgUrl(String mGoodsImgUrl) {
        this.mGoodsImgUrl = mGoodsImgUrl;
    }
}
