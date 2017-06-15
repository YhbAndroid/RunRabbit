package win.yuanhongbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import win.yuanhongbao.adapter.ShopAdapter;
import win.yuanhongbao.application.AppClose;
import win.yuanhongbao.javabean.ShopBean;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;

public class ShopActivity extends BaseActivity {

    private ListView mListLv;
    private ImageView mImgBack;
    private ArrayList<ShopBean> mList;
    private String strShop;
    private List<Integer> shopIds;

    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // 将该Activity添加到退出APP的数组
        AppClose.getClose().addActivity(this);

        bindId();//绑定控件ID

        initData();//初始化

        requestData();//okHttp进行网络请求


        intentActivity();//跳转
    }


    private void bindId() {
        mListLv = (ListView) findViewById(R.id.shop_lv_list);
        mImgBack = (ImageView) findViewById(R.id.shop_back);
    }

    private void initData() {
        mClient = new OkHttpClient();
        mList = new ArrayList<ShopBean>();
        shopIds = new ArrayList<Integer>();
    }

    private void requestData() {

        Request request = new Request.Builder()
                .url(Data.URL_PREFIX + Data.URL_SHOP + "{%22classify_id%22:0,%22counties_id%22:0,%22page%22:1,%22page_count%22:40}")
                .method(Data.REQUEST_METHOD, null)
                .build();
        Call mCall = mClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new RunToast().Tshort(ShopActivity.this, "网络错误或无网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                strShop = response.body().string();
                parsedData();//解析数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ShopAdapter adapter = new ShopAdapter(ShopActivity.this, mList);
                        mListLv.setAdapter(adapter);
                    }
                });

            }
        });
    }


    private void parsedData() {

        try {
            JSONObject obj_shop = new JSONObject(strShop);
            JSONArray array_shop = obj_shop.getJSONArray("datas");

            String[] shopNames = new String[array_shop.length()];
            String[] shopAddresses = new String[array_shop.length()];
            String[] shopOpenTimes = new String[array_shop.length()];
            String[] shopCloseTimes = new String[array_shop.length()];
            String[] shopImageUrls = new String[array_shop.length()];

            for (int i = 0; i < array_shop.length(); i++) {

                JSONObject shopname_obj = array_shop.getJSONObject(i);
                shopNames[i] = shopname_obj.getString("shop_name");
                shopAddresses[i] = shopname_obj.getString("shop_address");
                shopOpenTimes[i] = shopname_obj.getString("start_time_str");
                shopCloseTimes[i] = shopname_obj.getString("end_time_str");
                shopImageUrls[i] = shopname_obj.getString("small_pic");
                shopIds.add(shopname_obj.getInt("shop_id"));

                ShopBean bean = new ShopBean();
                bean.setShopName(shopNames[i]);
                bean.setShopAddress(shopAddresses[i]);
                bean.setShopOpenTime(shopOpenTimes[i]);
                bean.setShopCloseTime(shopCloseTimes[i]);
                bean.setShopImagUrl(shopImageUrls[i]);
                mList.add(bean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        ShopActivity.this.finish();
    }

    private void intentActivity() {
        mListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent();
                mIntent.putExtra("getShopId", shopIds.get(position).toString());
                mIntent.setClass(ShopActivity.this, CateActivity.class);
                startActivity(mIntent);
            }
        });
    }
}
