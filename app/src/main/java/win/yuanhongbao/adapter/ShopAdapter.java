package win.yuanhongbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
import win.yuanhongbao.activity.R;
import win.yuanhongbao.javabean.ShopBean;
import win.yuanhongbao.utils.Data;
import win.yuanhongbao.utils.RunToast;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class ShopAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ShopBean> mList;
    private LayoutInflater inflater;

    public ShopAdapter(Context context, ArrayList<ShopBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    final class ViewHolder {
        ImageView mShopImg;
        TextView mShopName;
        TextView mShopAddress;
        TextView mShopOpenTime;
        TextView mShopCloseTime;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shop, null);
            holder = new ViewHolder();

            holder.mShopImg = (ImageView) convertView.findViewById(R.id.shop_item_iv_image);
            holder.mShopName = (TextView) convertView.findViewById(R.id.shop_item_tv_name);
            holder.mShopAddress = (TextView) convertView.findViewById(R.id.shop_item_tv_address);
            holder.mShopOpenTime = (TextView) convertView.findViewById(R.id.shop_item_tv_opentime);
            holder.mShopCloseTime = (TextView) convertView.findViewById(R.id.shop_item_tv_closetime);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(Data.URL_PREFIX + mList.get(position).getShopImagUrl())
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_define)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.mShopImg);
        holder.mShopName.setText(mList.get(position).getShopName());
        holder.mShopAddress.setText(mList.get(position).getShopAddress());
        holder.mShopOpenTime.setText(mList.get(position).getShopOpenTime());
        holder.mShopCloseTime.setText(mList.get(position).getShopCloseTime());


        return convertView;
    }


}
