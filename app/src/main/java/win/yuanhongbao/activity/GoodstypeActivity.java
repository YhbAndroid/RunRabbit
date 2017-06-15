package win.yuanhongbao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

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
import win.yuanhongbao.adapter.GoodstypeAdapter;
import win.yuanhongbao.application.AppClose;
import win.yuanhongbao.javabean.GoodstypeBean;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;

public class GoodstypeActivity extends BaseActivity {

    private ListView mListLv;
    private ImageView mImgBack;
    private ArrayList<GoodstypeBean> mList;
    private String strType;
    private List<Integer> typeIds;

    private OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodstype);

        // 将该Activity添加到退出APP的数组
        AppClose.getClose().addActivity(this);

        bindId();//绑定控件ID
        initData();//初始化
        requestData();//网络请求
        intentActivity();//跳转

    }


    private void bindId() {
        mListLv = (ListView) findViewById(R.id.lv_goodstype);
        mImgBack = (ImageView) findViewById(R.id.goodstype_back);
    }

    private void initData() {
        mClient = new OkHttpClient();
        mList = new ArrayList<GoodstypeBean>();
        typeIds = new ArrayList<Integer>();
    }

    private void requestData() {
        Request request = new Request.Builder()
                .url(Data.URL_PREFIX + Data.URL_GOODSTYPY + Data.URL_GOODSTYPE_SUFFIX)
                .method(Data.REQUEST_METHOD, null)
                .build();
        Call mCall = mClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new RunToast().Tshort(GoodstypeActivity.this, "网络错误或无网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                strType = response.body().string();
                parsedData();//解析数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        GoodstypeAdapter adapter = new GoodstypeAdapter(GoodstypeActivity.this, mList);
                        mListLv.setAdapter(adapter);
                    }
                });
            }


        });
    }

    private void parsedData() {

        try {
            JSONObject obj_type = new JSONObject(strType);
            JSONArray array_data = obj_type.getJSONArray("datas");

            String[] goodsTypes = new String[array_data.length()];

            for (int i = 0; i < array_data.length(); i++) {

                JSONObject obj_typecont = array_data.getJSONObject(i);
                goodsTypes[i] = obj_typecont.getString("classify_name");
                typeIds.add(obj_typecont.getInt("classify_id"));

                GoodstypeBean bean = new GoodstypeBean();
                bean.setGoodstypeName(goodsTypes[i]);
                mList.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void back(View view) {
        GoodstypeActivity.this.finish();
    }

    private void intentActivity() {

        mListLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent();
                mIntent.putExtra("getTypeId", typeIds.get(position).toString());
                mIntent.setClass(GoodstypeActivity.this, GoodsActivity.class);
                startActivity(mIntent);
            }
        });
    }

}
