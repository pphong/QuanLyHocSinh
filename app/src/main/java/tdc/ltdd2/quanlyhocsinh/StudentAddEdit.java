package tdc.ltdd2.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class StudentAddEdit extends AppCompatActivity {

    StudentDatabaseHandler studentDatabaseHandler;
    TextView tvStudentEditTitle, tvStudentEditId;
    EditText txStudentName,txStudentClass,txStudentGender,txStudentBirth;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;

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
    }

    private void setValues(){
        tvStudentEditId.setText(this.intent.getStringExtra("studentId"));
        txStudentName.setText(this.intent.getStringExtra("studentName"));
        txStudentClass.setText(this.intent.getStringExtra("studentClass"));
        txStudentGender.setText(this.intent.getStringExtra("studentGender"));
        txStudentBirth.setText(this.intent.getStringExtra("studentBirth"));
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
        Intent intent = getIntent().setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setClass(StudentAddEdit.this, StudentList.class);
//        Intent intent = new Intent(this,StudentList.class);
        startActivity(intent);
    }
}