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
import tdc.ltdd2.quanlyhocsinh.model.Subject;

public class subjectAdapter  extends BaseAdapter {

    private List<Subject> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public subjectAdapter(Context aContext,  List<Subject> listData) {
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
            convertView = layoutInflater.inflate(R.layout.subject_item, null);
            holder = new ViewHolder();
            holder.tvSubjectItemId = (TextView) convertView.findViewById(R.id.tvSubjectItemId);
            holder.tvSubjectItemName = (TextView) convertView.findViewById(R.id.tvSubjectItemName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Subject Subject = this.listData.get(position);
        holder.tvSubjectItemId.setText(Subject.getSubjectId()+"");
        holder.tvSubjectItemName.setText(Subject.getSubjectName());
        return convertView;
    }

    static class ViewHolder {
        TextView tvSubjectItemId, tvSubjectItemName;
    }

}