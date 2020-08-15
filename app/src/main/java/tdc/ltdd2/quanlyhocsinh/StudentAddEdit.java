package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tdc.ltdd2.quanlyhocsinh.adapter.customSpinner;
import tdc.ltdd2.quanlyhocsinh.database.ClassDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Class;
import tdc.ltdd2.quanlyhocsinh.model.SpinnerItem;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class StudentAddEdit extends AppCompatActivity {

    StudentDatabaseHandler studentDatabaseHandler;
    ClassDatabaseHandler classDatabaseHandler;
    TextView tvStudentEditTitle, tvStudentEditId;
    EditText txStudentName,txStudentClass,txStudentGender,txStudentBirth;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;
    Spinner customSpinner;
    List<Class> classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_edit);
        actionEdit = false;
        checkAction();
        setControl();
        switchElement();
        setEvent();
    }

    private void setEvent() {
        if (actionEdit)
        {
            setValues();
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (actionEdit) {
                    //delete student
                    int id = Integer.parseInt(tvStudentEditId.getText().toString());
                    deleteStudent(id);
                } else {
                    txStudentName.setText("");
                    txStudentClass.setText("");
                    txStudentGender.setText("");
                    txStudentBirth.setText("");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txStudentName.getText().toString();
                String classs = txStudentClass.getText().toString();
                String gender = txStudentGender.getText().toString();
                String bod = txStudentBirth.getText().toString();

                if (actionEdit) {
                    //call update student
                    int id = Integer.parseInt(tvStudentEditId.getText().toString());
                    Student student = new Student(id,name,classs,gender,bod);
                    updateStudent(student);
                } else {
                    Student student = new Student(0,name,classs,gender,bod);
                    addStudent(student);
                }
            }
        });

        ArrayList<SpinnerItem> customList = new ArrayList<>();
        classes = getListClassData();
        for (Class classO : classes) {
            customList.add(new SpinnerItem(classO.getClassName(), R.drawable.ic_baseline_home_work_24));
        }

        customSpinner customAdapter = new customSpinner(this, customList);

        if (customSpinner != null) {
            customSpinner.setAdapter(customAdapter);
            customSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Object objClass = customSpinner.getItemAtPosition(i);
                    SpinnerItem spinnerO = (SpinnerItem) objClass;
                    txStudentClass.setText(spinnerO.getClassName());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private void switchElement() {
        if (actionEdit){
            tvStudentEditTitle.setText("Edit student");
            btnClear.setText("Delete");
        } else {
            tvStudentEditTitle.setText("Add new student");
            btnScore.setVisibility(View.INVISIBLE);
        }
    }

    private void checkAction() {
        intent = getIntent();
        boolean edit = intent.getBooleanExtra("edit",false);
        if (edit) {
            actionEdit = true;
            Bundle bundle = intent.getExtras();
        }
    }

    private void setControl() {
        studentDatabaseHandler = new StudentDatabaseHandler(this);
        tvStudentEditTitle = findViewById(R.id.tvStudentEditTitle);
        tvStudentEditId = findViewById(R.id.tvStudentEditId);
        txStudentName = findViewById(R.id.txStudentName);
        txStudentClass = findViewById(R.id.txStudentClass);
        txStudentGender = findViewById(R.id.txStudentGender);
        txStudentBirth = findViewById(R.id.txStudentBirth);
        btnClear = findViewById(R.id.btnAddStudent);
        btnSave = findViewById(R.id.btnStudentAddSave);
        btnClear = findViewById(R.id.btnStudentAddClear);
        btnScore = findViewById(R.id.btnStudentAddScore);
        customSpinner = findViewById(R.id.spClass);

    }

    private void setValues(){
        tvStudentEditId.setText(this.intent.getStringExtra("studentId"));
        txStudentName.setText(this.intent.getStringExtra("studentName"));
        txStudentClass.setText(this.intent.getStringExtra("studentClass"));
        txStudentGender.setText(this.intent.getStringExtra("studentGender"));
        txStudentBirth.setText(this.intent.getStringExtra("studentBirth"));
    }

    private List<Class> getListClassData() {
        classDatabaseHandler = new ClassDatabaseHandler(this);
        List<Class> list = new ArrayList<Class>();
        list.addAll(classDatabaseHandler.getAllClasses());
        return list;
    }

    private void addStudent(Student student) {
        studentDatabaseHandler.addStudent(student);
        this.reorderToFront();
    }

    private void deleteStudent(int id) {
        studentDatabaseHandler.deleteStudent(id);
        this.reorderToFront();
    }

    private void updateStudent(Student student) {
        studentDatabaseHandler.updateStudent(student);
        this.reorderToFront();
    }

    private void reorderToFront(){
//        Intent intent = getIntent().setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        intent.setClass(StudentAddEdit.this, StudentList.class);
        Intent intent = new Intent(this,StudentList.class);
        startActivity(intent);
    }
}