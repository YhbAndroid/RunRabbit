package win.yuanhongbao.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import win.yuanhongbao.adapter.GoodsAdapter;
import win.yuanhongbao.application.AppClose;
import win.yuanhongbao.javabean.GoodsBean;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;


public class GoodsActivity extends BaseActivity {

    private ListView mListLv;
    private ImageView mImgBack;
    private TextView mGoodsType;
    private ArrayList<GoodsBean> mList;
    private String strGoods;
    private String strTypeId;
    private String[] goodsTypes;

    private OkHttpClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        // 将该Activity添加到退出APP的数组
        AppClose.getClose().addActivity(this);

        bindID();//绑定控件
        initData();//初始化
        requestData();//请求数据
    }


    private void bindID() {
        mListLv = (ListView) findViewById(R.id.lv_goods);
        mImgBack = (ImageView) findViewById(R.id.goods_back);
        mGoodsType = (TextView) findViewById(R.id.goods_tv_type);
    }

    private void initData() {
        client = new OkHttpClient();
        mList = new ArrayList<GoodsBean>();

        Intent mIntent = getIntent();
        strTypeId = mIntent.getStringExtra("getTypeId");
    }


    private void requestData() {
        Request request = new Request.Builder()
                .url(Data.URL_PREFIX + Data.URL_GOODS + "{%22classify_id%22:" + strTypeId + ",%22page%22:1,%22page_count%22:10}")
                .method(Data.REQUEST_METHOD, null)
                .build();
        Call mCall = client.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new RunToast().Tshort(GoodsActivity.this, "网络错误或无网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                strGoods = response.body().string();
                parsedData();//解析数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mGoodsType.setText(goodsTypes[0]);
                        GoodsAdapter adapter = new GoodsAdapter(GoodsActivity.this, mList);
                        mListLv.setAdapter(adapter);
                    }
                });
            }
        });
    }

    private void parsedData() {

        try {
            JSONObject obj_goods = new JSONObject(strGoods);
            JSONArray array_goods = obj_goods.getJSONArray("datas");

            String[] goodsNames = new String[array_goods.length()];
            goodsTypes = new String[array_goods.length()];
            String[] goodsPrices = new String[array_goods.length()];
            String[] goodsImgUrls = new String[array_goods.length()];

            for (int i = 0; i < array_goods.length(); i++) {

                JSONObject goodsname_obj = array_goods.getJSONObject(i);

                goodsNames[i] = goodsname_obj.getString("product_name");
                goodsTypes[i] = goodsname_obj.getString("classify_name");
                goodsPrices[i] = goodsname_obj.getString("nowprice");
                goodsImgUrls[i] = goodsname_obj.getString("small_pic");

                GoodsBean bean = new GoodsBean();
                bean.setmGoodsName(goodsNames[i]);
                bean.setmGoodsType(goodsTypes[i]);
                bean.setmGoodsPrice(goodsPrices[i]);
                bean.setmGoodsImgUrl(goodsImgUrls[i]);
                mList.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        GoodsActivity.this.finish();
    }


}
