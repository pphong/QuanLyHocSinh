package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.studentAdapter;
import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class StudentList extends AppCompatActivity {

    private StudentDatabaseHandler studentDatabaseHandler;
    Button btnAddStudent;
    ListView listView;
    List<Student> students;
    studentAdapter adapter;
    String key = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        setControl();
        setEvent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:{
                Intent intent = new Intent(StudentList.this,MainActivity.class);
                startActivity(intent);
                return true;}
            case R.id.add:{
                Intent intent = new Intent(StudentList.this,ClassAddEdit.class);
                startActivity(intent);
                return true;}
            case R.id.refresh:{
                finish();
                startActivity(getIntent());
                return true;}
        }

        return super.onOptionsItemSelected(item);
    }

    private void setEvent() {
//        Student s1 = new Student(0,"Nguyễn Xuân Quý", "1A", "Nam", "1/8/1999");
//        this.addStudent(s1);
        setCustomListView();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentList.this,StudentAddEdit.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        intent = getIntent();
        studentDatabaseHandler = new StudentDatabaseHandler(this);
        String sKey = this.intent.getStringExtra("key");
        if (sKey != "" && sKey != null){
            this.key = this.intent.getStringExtra("key");
            students = getListDataSearch(this.key);
        } else {
            students = getListData();
        }
        listView  = (ListView) findViewById(R.id.lsStudent);
        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
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
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
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

    private  List<Student> getListDataSearch(String key) {
        List<Student> list = new ArrayList<Student>();
        list.addAll(studentDatabaseHandler.getAllStudentsBySearch(key));
        return list;
    }
}