package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.studentAdapter;
import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class StudentList extends AppCompatActivity {

    private StudentDatabaseHandler studentDatabaseHandler;
    ListView listView;
    List<Student> students;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        setControl();
        setEvent();
    }

    private void setEvent() {
//        Student s1 = new Student(0,"Nguyễn Xuân Quý", "1A", "Nam", "1/8/1999");
//        this.addStudent(s1);

        setCustomListView();
    }

    private void setControl() {
        studentDatabaseHandler = new StudentDatabaseHandler(this);
        students = getListData();
        listView  = (ListView) findViewById(R.id.lsStudent);
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

            }
        });
    }

    private  List<Student> getListData() {
        List<Student> list = new ArrayList<Student>();
        list.addAll(studentDatabaseHandler.getAllStudents());
        return list;
    }

    private void addStudent(Student student) {
        studentDatabaseHandler.addStudent(student);
    }

    private void deleteStudent(int id) {
        studentDatabaseHandler.deleteStudent(id);
    }

    private void updateStudent(Student student) {
        studentDatabaseHandler.updateStudent(student);
    }
}