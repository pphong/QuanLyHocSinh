package tdc.ltdd2.quanlyhocsinh;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.ltdd2.quanlyhocsinh.database.StudentDatabaseHandler;
import tdc.ltdd2.quanlyhocsinh.model.Student;

public class TeacherAddEdit extends AppCompatActivity {

    StudentDatabaseHandler studentDatabaseHandler;
    TextView tvStudentEditTitle, tvStudentEditId;
    EditText txStudentName,txStudentClass,txStudentGender,txStudentBirth;
    Button btnClear, btnSave, btnScore;
    Boolean actionEdit;
    Intent intent;
    Context context;
    ArrayList<Student> ds=new ArrayList<>();;
    Student student;
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(TeacherAddEdit.this);
                    builder.setTitle(R.string.app_name);
                    builder.setIcon(R.mipmap.ic_launcher);
                    builder.setMessage("Do you want to delete this student ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    id = Integer.parseInt(tvStudentEditId.getText().toString());
                                    deleteStudent(id);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
//                    int id = Integer.parseInt(tvStudentEditId.getText().toString());
//                    deleteStudent(id);
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
        Intent intent = new Intent(TeacherAddEdit.this, StudentList.class);
        startActivity(intent);
    }

    private void deleteStudent(int id) {
        studentDatabaseHandler.deleteStudent(id);
        Intent intent = new Intent(TeacherAddEdit.this, StudentList.class);
        startActivity(intent);
    }

    private void updateStudent(Student student) {
        studentDatabaseHandler.updateStudent(student);
        Intent intent = new Intent(TeacherAddEdit.this, StudentList.class);
        startActivity(intent);
    }
}