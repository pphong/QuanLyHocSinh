package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.studentAdapter;
import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class StudentList extends AppCompatActivity {

    private StudentDatabaseHandler studentDatabaseHandler;
    Button btnAddStudent;
    SwipeMenuListView listView;
    List<Student> students;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        setControl();
        swipeDelele();
        setEvent();
    }

    private void setEvent() {
//        Student s1 = new Student(0,"Nguyễn Xuân Quý", "1A", "Nam", "1/8/1999");
//        this.addStudent(s1);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentList.this,StudentAddEdit.class);
                startActivity(intent);
            }
        });
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //delete student
                String value = (String) adapter.getItem(position);
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Action 1 for "+ value , Toast.LENGTH_SHORT).show();
                        break;
                }
                return false ;
            }
        });
    }
    private void swipeDelele()
    {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(R.drawable.button_negative_selector);
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_baseline_delete_sweep_24);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        setCustomListView();
        listView.setMenuCreator(creator);
    }

    private void setControl() {
        studentDatabaseHandler = new StudentDatabaseHandler(this);
        students = getListData();
        listView  = findViewById(R.id.lsStudent);
        btnAddStudent = findViewById(R.id.btnAddStudent);
    }

    private void setCustomListView(){
        adapter = new studentAdapter(this, students);
        listView.setAdapter(adapter);

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object objStudent = listView.getItemAtPosition(i);
                Student student = (Student) objStudent;
                Intent intent = new Intent(StudentList.this, StudentAddEdit.class);
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
    private  List<Student> getListData() {
        List<Student> list = new ArrayList<Student>();
        list.addAll(studentDatabaseHandler.getAllStudents());
        return list;
    }
}