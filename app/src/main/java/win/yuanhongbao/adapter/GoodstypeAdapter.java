package win.yuanhongbao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import win.yuanhongbao.activity.R;
import win.yuanhongbao.javabean.GoodstypeBean;

/**
 * Created by Administrator on 2017/4/23 0023.
 */

public class GoodstypeAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<GoodstypeBean> mList;
    private LayoutInflater inflater;

    public GoodstypeAdapter(Context context, ArrayList<GoodstypeBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    final class ViewHolder {
        TextView mGoodstypeName;
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
            convertView = inflater.inflate(R.layout.item_goodstype, null);

            holder = new ViewHolder();

            holder.mGoodstypeName = (TextView) convertView.findViewById(R.id.goodstype_item_tv_name);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mGoodstypeName.setText(mList.get(position).getGoodstypeName());

        return convertView;
    }
}
