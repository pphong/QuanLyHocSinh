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
import tdc.ltdd2.quanlyhocsinh.model.Teacher;

public class teacherAdapter  extends BaseAdapter {

    private List<Teacher> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public teacherAdapter(Context aContext,  List<Teacher> listData) {
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
            convertView = layoutInflater.inflate(R.layout.teacher_item, null);
            holder = new ViewHolder();
            holder.tvTeacherId = (TextView) convertView.findViewById(R.id.tvTeacherId);
            holder.tvTeacherName = (TextView) convertView.findViewById(R.id.tvTeacherName);
            holder.tvTeacherClass = (TextView) convertView.findViewById(R.id.tvTeacherClass);
            holder.tvTeacherGender = (TextView) convertView.findViewById(R.id.tvTeacherGender);
            holder.tvTeacherBirth = (TextView) convertView.findViewById(R.id.tvTeacherBirth);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Teacher teacher = this.listData.get(position);
        holder.tvTeacherId.setText(teacher.getTeacherId()+"");
        holder.tvTeacherName.setText(teacher.getTeacherName());
        holder.tvTeacherClass.setText(teacher.getTeacherClass());
        holder.tvTeacherGender.setText(teacher.getTeacherGender());
        holder.tvTeacherBirth.setText(teacher.getTeacherBirth());
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
        ImageView imgTeacher;
        TextView tvTeacherId, tvTeacherName, tvTeacherClass, tvTeacherGender, tvTeacherBirth;
    }

}