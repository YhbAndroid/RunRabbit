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
import win.yuanhongbao.javabean.GoodsBean;
import win.yuanhongbao.utils.Data;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class GoodsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<GoodsBean> mList;
    private LayoutInflater inflater;

    public GoodsAdapter(Context context, ArrayList<GoodsBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    final class ViewHolder {
        ImageView mGoodsImg;
        TextView mGoodsName;
        TextView mGoodsType;
        TextView mGoodsPrice;
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
            convertView = inflater.inflate(R.layout.item_goods, null);
            holder = new ViewHolder();

            holder.mGoodsImg = (ImageView) convertView.findViewById(R.id.goods_item_iv_img);
            holder.mGoodsName = (TextView) convertView.findViewById(R.id.goods_item_tv_name);
            holder.mGoodsType = (TextView) convertView.findViewById(R.id.goods_item_tv_type);
            holder.mGoodsPrice = (TextView) convertView.findViewById(R.id.goods_item_tv_nowprice);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext)
                .load(Data.URL_PREFIX + mList.get(position).getmGoodsImgUrl())
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_define)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.mGoodsImg);
        holder.mGoodsName.setText(mList.get(position).getmGoodsName());
        holder.mGoodsType.setText(mList.get(position).getmGoodsType());
        holder.mGoodsPrice.setText(mList.get(position).getmGoodsPrice());

        return convertView;
    }
}
