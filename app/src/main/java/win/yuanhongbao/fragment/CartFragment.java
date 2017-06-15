package win.yuanhongbao.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import win.yuanhongbao.activity.GoodsActivity;
import win.yuanhongbao.activity.R;
import win.yuanhongbao.adapter.CartAdapter;
import win.yuanhongbao.adapter.GoodsAdapter;
import win.yuanhongbao.javabean.GoodsBean;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class CartFragment extends Fragment {

    private ListView mListLv;
    private ArrayList<GoodsBean> mList;
    private String strGoods;
    private String[] goodsTypes;

    private OkHttpClient client;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, null);

        mListLv = (ListView) view.findViewById(R.id.cart_lv);
        
        initData();//初始化数据
        requestData();//请求数据
        
        return view;
    }

    private void initData() {
        client = new OkHttpClient();
        mList = new ArrayList<GoodsBean>();
    }

    private void requestData() {
        Request request = new Request.Builder()
                    .url(Data.URL_PREFIX+Data.URL_CART+"{\"user_id\":\"072911\"}")
                .method(Data.REQUEST_METHOD,null)
                .build();
        Call mCall = client.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                new RunToast().Tshort(getActivity(), "网络错误或无网络");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                strGoods = response.body().string();
                parsedData();//解析数据
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        CartAdapter adapter = new CartAdapter(getActivity(), mList);
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
}
