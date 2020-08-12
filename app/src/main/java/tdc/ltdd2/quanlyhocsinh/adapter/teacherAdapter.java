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
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class teacherAdapter extends BaseAdapter {

    private List<Student> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public teacherAdapter(Context aContext, List<Student> listData) {
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
            convertView = layoutInflater.inflate(R.layout.student_item, null);
            holder = new ViewHolder();
            holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvClass = (TextView) convertView.findViewById(R.id.tvClass);
            holder.tvGender = (TextView) convertView.findViewById(R.id.tvGender);
            holder.tvBirth = (TextView) convertView.findViewById(R.id.tvBirth);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Student student = this.listData.get(position);
        holder.tvId.setText(student.getStudentId()+"");
        holder.tvName.setText(student.getStudentName());
        holder.tvClass.setText(student.getStudentClass());
        holder.tvGender.setText(student.getStudentGender());
        holder.tvBirth.setText(student.getStudentBirth());
        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView imgStudent;
        TextView tvId, tvName, tvClass, tvGender, tvBirth;
    }

}