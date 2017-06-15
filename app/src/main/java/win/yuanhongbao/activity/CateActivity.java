package win.yuanhongbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

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
import win.yuanhongbao.adapter.CateAdapter;
import win.yuanhongbao.application.AppClose;
import win.yuanhongbao.javabean.CateBean;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;

public class CateActivity extends BaseActivity {

    private ListView mListLv;
    private ArrayList<CateBean> mList;
    private String strCate;
    private String strShopId;

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);

        // 将该Activity添加到退出APP的数组
        AppClose.getClose().addActivity(this);

        bindID();
        initData();
        requestData();
    }

    private void bindID() {
        mListLv = (ListView) findViewById(R.id.cate_lv_list);
    }

    private void initData() {
        client = new OkHttpClient();
        mList = new ArrayList<CateBean>();

        Intent mIntent = getIntent();
        strShopId = mIntent.getStringExtra("getShopId");
    }

    private void requestData() {
        final Request request = new Request.Builder()
                .url(Data.URL_PREFIX + Data.URL_CATE + "{%22shop_id%22:" + strShopId + ",%22page%22:1,%22page_count%22:10}")
                .method(Data.REQUEST_METHOD, null)
                .build();
        Call mCall = client.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new RunToast().Tshort(CateActivity.this, "网络错误或无网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                strCate = response.body().string();
                parsedData();//解析数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CateAdapter adapter = new CateAdapter(CateActivity.this, mList);
                        mListLv.setAdapter(adapter);
                    }
                });
            }
        });
    }

    private void parsedData() {

        try {
            JSONObject obj_cate = new JSONObject(strCate);
            JSONArray array_cate = obj_cate.getJSONArray("datas");

            String[] cateNames = new String[array_cate.length()];
            String[] cateAddresses = new String[array_cate.length()];
            String[] catePrices = new String[array_cate.length()];
            String[] cateImgUrls = new String[array_cate.length()];

            for (int i = 0; i < array_cate.length(); i++) {

                JSONObject catename_obj = array_cate.getJSONObject(i);

                cateNames[i] = catename_obj.getString("product_name");
                cateAddresses[i] = catename_obj.getString("product_address");
                catePrices[i] = catename_obj.getString("nowprice");
                cateImgUrls[i] = catename_obj.getString("small_pic");

                CateBean bean = new CateBean();
                bean.setmCateName(cateNames[i]);
                bean.setmCateAddress(cateAddresses[i]);
                bean.setmCatePrice(catePrices[i]);
                bean.setmCateImagUrl(cateImgUrls[i]);
                mList.add(bean);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        CateActivity.this.finish();
    }
}
