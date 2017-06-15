package win.yuanhongbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import win.yuanhongbao.activity.R;
import win.yuanhongbao.javabean.CateBean;
import win.yuanhongbao.utils.Data;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class CateAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<CateBean> mList;
    private LayoutInflater inflater;

    public CateAdapter(Context context, ArrayList<CateBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    final class ViewHolder {
        ImageView mCateImg;
        TextView mCateName;
        TextView mCateAddress;
        TextView mCatePrice;
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

        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_cate, null);
            holder = new ViewHolder();

            holder.mCateImg = (ImageView) convertView.findViewById(R.id.cate_item_iv_img);
            holder.mCateName = (TextView) convertView.findViewById(R.id.cate_item_tv_name);
            holder.mCateAddress = (TextView) convertView.findViewById(R.id.cate_item_tv_address);
            holder.mCatePrice = (TextView) convertView.findViewById(R.id.cate_item_tv_nowprice);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(Data.URL_PREFIX + mList.get(position).getmCateImagUrl())
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_define)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.mCateImg);
        holder.mCateName.setText(mList.get(position).getmCateName());
        holder.mCateAddress.setText(mList.get(position).getmCateAddress());
        holder.mCatePrice.setText(mList.get(position).getmCatePrice());

        return convertView;
    }
}