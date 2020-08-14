package tdc.ltdd2.quanlyhocsinh.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.R;
import tdc.ltdd2.quanlyhocsinh.model.Class;

public class classAdapter  extends BaseAdapter {

    private List<Class> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public classAdapter(Context aContext,  List<Class> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.class_item, null);
            holder = new ViewHolder();
            holder.tvClassItemId = (TextView) convertView.findViewById(R.id.tvClassItemId);
            holder.tvClassItemName = (TextView) convertView.findViewById(R.id.tvClassItemName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Class Class = this.listData.get(position);
        holder.tvClassItemName.setText(Class.getClassName());
        return convertView;
    }

    static class ViewHolder {
        TextView tvClassItemId, tvClassItemName;
    }

}