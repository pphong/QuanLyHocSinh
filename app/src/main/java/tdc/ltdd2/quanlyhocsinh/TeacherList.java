package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.teacherAdapter;
import tdc.ltdd2.quanlyhocsinh.database.TeacherDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Teacher;

public class TeacherList extends AppCompatActivity {

    private TeacherDatabaseHandler teacherDatabaseHandler;
    Button btnAddTeacher;
    ListView listView;
    List<Teacher> teachers;
    teacherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        setControl();
        setEvent();
    }

    private void setEvent() {
        setCustomListView();
        btnAddTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherList.this,TeacherAddEdit.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        teacherDatabaseHandler = new TeacherDatabaseHandler(this);
        teachers = getListData();
        listView  = (ListView) findViewById(R.id.lsTeacher);
        btnAddTeacher = (Button) findViewById(R.id.btnAddTeacher);
    }

    private void setCustomListView(){
        adapter = new teacherAdapter(this, teachers);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object objTeacher = listView.getItemAtPosition(i);
                Teacher teacher = (Teacher) objTeacher;
                Intent intent = new Intent(TeacherList.this, TeacherAddEdit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Bundle bundle = new Bundle();
                bundle.putBoolean("edit",true);
                bundle.putString("teacherId",teacher.getTeacherId()+"");
                bundle.putString("teacherName",teacher.getTeacherName());
                bundle.putString("teacherClass",teacher.getTeacherClass());
                bundle.putString("teacherGender",teacher.getTeacherGender());
                bundle.putString("teacherBirth",teacher.getTeacherBirth());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private  List<Teacher> getListData() {
        List<Teacher> list = new ArrayList<Teacher>();
        list.addAll(teacherDatabaseHandler.getAllTeachers());
        return list;
    }
}