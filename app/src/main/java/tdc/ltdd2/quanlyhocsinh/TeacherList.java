package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.studentAdapter;
import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Student;
import tdc.ltdd2.quanlyhocsinh.model.Teachers;

public class TeacherList extends AppCompatActivity {
    private StudentDatabaseHandler studentDatabaseHandler;
    Button btnAddTeacher;
    SwipeMenuListView listView;
    List<Teachers> teachers;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        setControl();
        swipeDelele();
        setEvent();
    }

    private void setControl() {
        studentDatabaseHandler = new StudentDatabaseHandler(this);
        teachers = getListData();
        listView  = findViewById(R.id.lsStudent);
        btnAddTeacher = findViewById(R.id.btnAddStudent);
    }

    private void setCustomListView(){
        adapter = new teac(this, teachers);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object objStudent = listView.getItemAtPosition(i);
                Student student = (Student) objStudent;
                Intent intent = new Intent(TeacherList.this, TeacherAddEdit.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("edit",true);
                bundle.putString("studentId",student.getStudentId()+"");
                bundle.putString("studentName",student.getStudentName());
                bundle.putString("studentClass",student.getStudentClass());
                bundle.putString("studentGender",student.getStudentGender());
                bundle.putString("studentBirth",student.getStudentBirth());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private  List<Teachers> getListData() {
        List<Teachers> list = new ArrayList<Teachers>();
        list.addAll(studentDatabaseHandler.getAllTeachers());
        return list;
    }
}