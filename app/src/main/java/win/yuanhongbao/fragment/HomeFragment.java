package win.yuanhongbao.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import win.yuanhongbao.activity.CateActivity;
import win.yuanhongbao.activity.GoodstypeActivity;
import win.yuanhongbao.activity.R;
import win.yuanhongbao.activity.ShopActivity;
import win.yuanhongbao.activity.UserActivity;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private EditText mSearchEt;
    private ImageView mGoodsIv, mCateIv, mAgencyIv;
    private ImageView mVfImg1, mVfImg2, mVfImg3, mVfImg4,mUserImg;
    private ViewFlipper mVf;
    private OkHttpClient mClient;
    private String strImg;
    private String[] imgUrls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mSearchEt = (EditText) view.findViewById(R.id.home_et_search);
        mGoodsIv = (ImageView) view.findViewById(R.id.home_iv_goods);
        mCateIv = (ImageView) view.findViewById(R.id.home_iv_cate);
        mAgencyIv = (ImageView) view.findViewById(R.id.home_iv_agency);
        mVf = (ViewFlipper) view.findViewById(R.id.home_vf);
        mVfImg1 = (ImageView) view.findViewById(R.id.home_vf_img1);
        mVfImg2 = (ImageView) view.findViewById(R.id.home_vf_img2);
        mVfImg3 = (ImageView) view.findViewById(R.id.home_vf_img3);
        mVfImg4 = (ImageView) view.findViewById(R.id.home_vf_img4);
        mUserImg = (ImageView) view.findViewById(R.id.home_img_user);

        requestData();

        mVf.startFlipping();

        mSearchEt.setOnClickListener(this);
        mGoodsIv.setOnClickListener(this);
        mCateIv.setOnClickListener(this);
        mAgencyIv.setOnClickListener(this);
        mUserImg.setOnClickListener(this);

        return view;
    }

    private void requestData() {

        mClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Data.URL_PREFIX + Data.URL_HOME_IMG)
                .method(Data.REQUEST_METHOD, null)
                .build();
        Call mCall = mClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new RunToast().Tshort(getActivity(), "网络错误或无网络");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                strImg = response.body().string();
                parsedData();//解析数据
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(getActivity())
                                .load(Data.URL_PREFIX + imgUrls[0])
                                .into(mVfImg1);
                        Picasso.with(getActivity())
                                .load(Data.URL_PREFIX + imgUrls[1])
                                .into(mVfImg2);
                        Picasso.with(getActivity())
                                .load(Data.URL_PREFIX + imgUrls[2])
                                .into(mVfImg3);
                        Picasso.with(getActivity())
                                .load(Data.URL_PREFIX + imgUrls[3])
                                .into(mVfImg4);
                    }
                });
            }
        });

    }

    private void parsedData() {

        try {
            JSONObject obj_home = new JSONObject(strImg);
            JSONArray array_home = obj_home.getJSONArray("datas");

            imgUrls = new String[array_home.length()];

            for (int i = 0; i < array_home.length(); i++) {
                JSONObject obj_imgcount = array_home.getJSONObject(i);
                imgUrls[i] = obj_imgcount.getString("advert_image_url");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_et_search:
                mSearchEt.setCursorVisible(true);
                break;

            case R.id.home_iv_goods:
                startActivity(new Intent(getActivity(), GoodstypeActivity.class));
                break;

            case R.id.home_iv_cate:
                startActivity(new Intent(getActivity(), ShopActivity.class));
                break;

            case R.id.home_iv_agency:

                break;

            case R.id.home_img_user:
                startActivity(new Intent(getActivity(), UserActivity.class));
                break;
        }
    }
}
